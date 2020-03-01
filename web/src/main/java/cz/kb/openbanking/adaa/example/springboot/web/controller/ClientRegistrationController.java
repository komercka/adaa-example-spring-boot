package cz.kb.openbanking.adaa.example.springboot.web.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.Collections;

import cz.kb.openbanking.adaa.example.springboot.core.configuration.AdaaProperties;
import cz.kb.openbanking.adaa.example.springboot.core.decryption.Aes256DecryptionService;
import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.dto.ClientIdDto;
import cz.kb.openbanking.adaa.example.springboot.web.dto.ClientRegistrationRequest;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import cz.kb.openbanking.clientregistration.client.api.SoftwareStatementsApi;
import cz.kb.openbanking.clientregistration.client.api.model.GrantTypesEnum;
import cz.kb.openbanking.clientregistration.client.api.model.Jwt;
import cz.kb.openbanking.clientregistration.client.api.model.ResponseTypesEnum;
import cz.kb.openbanking.clientregistration.client.api.model.SoftwareStatement;
import cz.kb.openbanking.clientregistration.client.api.model.TokenEndpointAuthMethodEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This resource serves for registration this application against KB OAuth2 server
 * and get {@link ClientIdentifier} to be able to call KB ADAA API.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @see ClientIdentifier
 * @see OAuth2FlowProvider
 * @since 1.0
 */
@Controller
public class ClientRegistrationController {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final OAuth2FlowProvider oAuth2FlowProvider;

    private final Aes256DecryptionService decryptionService;

    private final AdaaProperties adaaProperties;

    private final SoftwareStatementsApi softwareStatementsApi;

    /**
     * New instance.
     *
     * @param oAuth2FlowProvider    {@link OAuth2FlowProvider}
     * @param decryptionService     {@link Aes256DecryptionService}
     * @param adaaProperties        {@link AdaaProperties}
     * @param softwareStatementsApi {@link SoftwareStatementsApi}
     */
    public ClientRegistrationController(OAuth2FlowProvider oAuth2FlowProvider,
                                        Aes256DecryptionService decryptionService, AdaaProperties adaaProperties,
                                        SoftwareStatementsApi softwareStatementsApi)
    {
        Assert.notNull(oAuth2FlowProvider, "oAuth2FlowProvider must not be null");
        Assert.notNull(decryptionService, "decryptionService must not be null");
        Assert.notNull(adaaProperties, "adaaProperties must not be null");
        Assert.notNull(softwareStatementsApi, "softwareStatementsApi must not be null");

        this.oAuth2FlowProvider = oAuth2FlowProvider;
        this.decryptionService = decryptionService;
        this.adaaProperties = adaaProperties;
        this.softwareStatementsApi = softwareStatementsApi;
    }

    /**
     * Returns a registration page with a form. After completion of this form
     * user will be redirected to KB Login page, where should grant access to application registration.
     *
     * @return registration page
     */
    @GetMapping(EndpointUris.CLIENT_REGISTRATION_FORM)
    public String registrationForm() {
        return "register";
    }

    /**
     * Gets software statement by calling Client Registration API, after that
     * user will be redirected to KB Login page, where should grant access to application registration.
     *
     * @return redirect to KB Login page
     */
    @GetMapping(EndpointUris.SOFTWARE_STATEMENT_REGISTRATION)
    public RedirectView software(@RequestParam("softwareName") String softwareName) {
        Assert.hasText(softwareName, "softwareName must not be empty");

        SoftwareStatement request = getSoftwareStatement(softwareName);
        Jwt jwt = softwareStatementsApi.softwareStatement(request);
        ClientRegistrationRequest clientRegistrationRequest = getClientRegistrationRequest(softwareName, jwt);

        byte[] registrationRequest;
        try {
            registrationRequest = mapper.writeValueAsBytes(clientRegistrationRequest);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Cannot construct JSON from software statement request.", e);
        }

        URI clientRegistrationUri = URI.create(adaaProperties.getClientRegistrationUri().endsWith("/") ?
                adaaProperties.getClientRegistrationUri() : adaaProperties.getClientRegistrationUri() + "/");
        String registrationUri = ServletUriComponentsBuilder.fromUri(clientRegistrationUri)
                                                            .path(EndpointUris.SAML_REGISTRATION)
                                                            .queryParam("registrationRequest",
                                                                    Base64.getEncoder().encodeToString(registrationRequest))
                                                            .build().toUriString();

        return new RedirectView(registrationUri);
    }

    /**
     * Retrieves AES-256 encrypted client's identifiers, decrypts it and set to the {@link OAuth2FlowProvider}.
     *
     * @param salt          salt that was used during encryption
     * @param encryptedData AES-256 encrypted client's identifiers
     * @return redirect user to continue OAuth2 authorization process
     */
    @GetMapping(EndpointUris.CLIENT_REGISTRATION)
    public RedirectView retrieveClientIdentifier(@RequestParam("salt") String salt,
                                                 @RequestParam("encryptedData") String encryptedData)
    {
        Assert.hasText(salt, "salt must not be empty");
        Assert.hasText(encryptedData, "encryptedData must not be empty");

        String clientIdentifierJson = decryptionService.decrypt(encryptedData, salt, adaaProperties.getSecret());
        ClientIdDto clientIdDto;
        try {
            clientIdDto = mapper.readValue(clientIdentifierJson, ClientIdDto.class);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot parse client registration's data.", e);
        }
        ClientIdentifier clientIdentifier =
                new ClientIdentifier(clientIdDto.getClientId(), clientIdDto.getClientSecret());
        oAuth2FlowProvider.setClientIdentifier(clientIdentifier);

        return oAuth2FlowProvider.authorizationRedirect(getOauthRedirectUri());
    }

    /**
     * Gets {@link ClientRegistrationRequest}.
     *
     * @param softwareName name of application
     * @param jwt          JWT token that contain software statement
     * @return {@link ClientRegistrationRequest}
     */
    private ClientRegistrationRequest getClientRegistrationRequest(String softwareName, Jwt jwt) {
        Assert.hasText(softwareName, "softwareName must not be empty");
        Assert.notNull(jwt, "jwt must not be null");

        return new ClientRegistrationRequest(softwareName, null,
                "web", Collections.singletonList(getOauthRedirectUri()), Collections.singletonList("adaa"),
                jwt.getToken(), adaaProperties.getSecret());
    }

    /**
     * Gets {@link SoftwareStatement}.
     *
     * @param softwareName name of application
     * @return {@link SoftwareStatement}
     */
    private SoftwareStatement getSoftwareStatement(String softwareName) {
        Assert.hasText(softwareName, "softwareName must not be empty");

        SoftwareStatement request = new SoftwareStatement(softwareName, "softwareId", "1.0",
                Collections.singletonList(getOauthRedirectUri()),
                ServletUriComponentsBuilder.fromCurrentContextPath().pathSegment(EndpointUris.CLIENT_REGISTRATION).build().toString());
        request.setSoftwareUri(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString());
        request.setTokenEndpointAuthMethod(TokenEndpointAuthMethodEnum.CLIENT_SECRET_POST);
        request.setGrantTypes(Collections.singletonList(GrantTypesEnum.AUTHORIZATION_CODE));
        request.setResponseTypes(Collections.singletonList(ResponseTypesEnum.CODE));

        return request;
    }

    /**
     * Gets redirect URI into OAuth2 (for getting authorization code).
     * This URI must be fill in software statement and client registration.
     *
     * @return redirect URI
     */
    private String getOauthRedirectUri() {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                                          .path(EndpointUris.AUTHORIZATION)
                                          .build().toUriString();
    }
}
