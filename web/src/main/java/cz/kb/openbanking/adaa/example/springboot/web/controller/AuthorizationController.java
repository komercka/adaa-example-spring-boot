package cz.kb.openbanking.adaa.example.springboot.web.controller;

import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import lombok.AllArgsConstructor;
import org.glassfish.jersey.client.oauth2.TokenResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Resource to receive authorization code (after user grants access to this application) and to call KB OAuth2 API to get access token
 * afterwards.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @see OAuth2FlowProvider
 * @since 1.0
 */
@Controller
@AllArgsConstructor
public class AuthorizationController {

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * Receives the authorization code, calls KB OAuth2 API to get access token and sets it to the {@link OAuth2FlowProvider}.
     *
     * @param authCode OAuth2 authorization code
     * @param state    described by OAuth2 specification and serves to prevent possible CSRF attack
     * @return redirect user to account's resource
     */
    @GetMapping(EndpointUris.AUTHORIZATION)
    public RedirectView authorize(@RequestParam("code") String authCode,
                                  @RequestParam("state") String state)
    {
        Assert.hasText(authCode, "authCode must not be empty");
        Assert.hasText(state, "state must not be empty");

        // call KB OAuth2 API to get access token
        TokenResult tokenResult = oAuth2FlowProvider.getFlow().finish(authCode, state);
        oAuth2FlowProvider.setAccessToken(tokenResult.getAccessToken());

        // authorization is finished -> now redirecting back to the 'accounts' resource
        String transactionUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                           .path(EndpointUris.ACCOUNTS)
                                                           .build().toUriString();
        return new RedirectView(transactionUri);
    }
}
