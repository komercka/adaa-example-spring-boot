package cz.kb.openbanking.adaa.example.springboot.web.oauth2;

import javax.annotation.Nullable;

import cz.kb.openbanking.adaa.example.springboot.core.configuration.AdaaProperties;
import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Store of OAuth2 credentials and authorization flow. This is very simple one user store for credentials.
 * In real usage tokens should be stored per user.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @see ClientIdentifier
 * @see OAuth2CodeGrantFlow
 * @since 1.0
 */
@Component
public class OAuth2FlowProvider {

    /**
     * ADAA authorization scope.
     */
    private static final String ADAA_SCOPE = "adaa";

    /**
     * Represents authorization flow.
     */
    private OAuth2CodeGrantFlow flow;

    /**
     * Contains client's identity.
     */
    private ClientIdentifier clientIdentifier;

    /**
     * OAuth2 access token.
     */
    private String accessToken;

    /**
     * OAuth2 authorization redirect URI.
     */
    private String oauthRedirectUri;

    /**
     * ADAA configuration properties.
     */
    private final AdaaProperties adaaProperties;

    /**
     * New instance.
     *
     * @param adaaProperties ADAA example properties
     */
    public OAuth2FlowProvider(AdaaProperties adaaProperties) {
        Assert.notNull(adaaProperties, "adaaProperties must not be null");

        this.adaaProperties = adaaProperties;
        this.oauthRedirectUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                           .path(EndpointUris.AUTHORIZATION)
                                                           .build().toUriString();
    }

    /**
     * Prepare redirect response to KB OAuth2 authorization consent request.
     *
     * @return redirect response to KB OAuth2 authorization consent request
     */
    public RedirectView authorizationRedirect() {
        OAuth2CodeGrantFlow flow = OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(
                getClientIdentifier(),
                adaaProperties.getAuthorizationUri(),
                adaaProperties.getAccessTokenUri())
                .redirectUri(this.oauthRedirectUri)
                .scope(ADAA_SCOPE)
                .build();

        setFlow(flow);

        // start the flow
        String kbAuthURI = flow.start();

        // redirect user to KB OAuth2 authorization server
        return new RedirectView(kbAuthURI);
    }

    /**
     * Gets redirect URI into OAuth2 (for getting authorization code).
     * This URI must be fill in software statement and client registration.
     *
     * @return redirect URI
     */
    public String getOauthRedirectUri() {
        return this.oauthRedirectUri;
    }

    /**
     * Gets access token.
     *
     * @return access token
     */
    @Nullable
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets access token.
     *
     * @param accessToken access token
     */
    public void setAccessToken(String accessToken) {
        Assert.hasText(accessToken, "accessToken must not be empty");

        this.accessToken = accessToken;
    }

    /**
     * Gets {@link OAuth2CodeGrantFlow}.
     *
     * @return {@link OAuth2CodeGrantFlow}
     */
    @Nullable
    public OAuth2CodeGrantFlow getFlow() {
        return flow;
    }

    /**
     * Sets {@link OAuth2CodeGrantFlow}.
     *
     * @param flow {@link OAuth2CodeGrantFlow}
     */
    public void setFlow(OAuth2CodeGrantFlow flow) {
        Assert.notNull(flow, "flow must not be null");

        this.flow = flow;
    }

    /**
     * Gets {@link ClientIdentifier}.
     *
     * @return {@link ClientIdentifier}
     */
    @Nullable
    public ClientIdentifier getClientIdentifier() {
        return clientIdentifier;
    }

    /**
     * Sets {@link ClientIdentifier}.
     *
     * @param clientIdentifier {@link ClientIdentifier}
     */
    public void setClientIdentifier(ClientIdentifier clientIdentifier) {
        Assert.notNull(clientIdentifier, "clientIdentifier must not be null");

        this.clientIdentifier = clientIdentifier;
    }
}
