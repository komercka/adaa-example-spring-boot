package cz.kb.openbanking.adaa.example.springboot.web.controller;

import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import org.glassfish.jersey.client.oauth2.TokenResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This resource serves to receive authorization code (after user grants access to this application)
 * and then call KB OAuth2 API to get access token.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @see OAuth2FlowProvider
 * @since 1.0
 */
@Controller
public class AuthorizationController {

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * New instance.
     *
     * @param oAuth2FlowProvider {@link OAuth2FlowProvider}
     */
    public AuthorizationController(OAuth2FlowProvider oAuth2FlowProvider) {
        Assert.notNull(oAuth2FlowProvider, "oAuth2FlowProvider must not be null");

        this.oAuth2FlowProvider = oAuth2FlowProvider;
    }

    /**
     * Receives the authorization code, call KB OAuth2 API to get access token
     * and set it to the {@link OAuth2FlowProvider}.
     *
     * @param authCode OAuth2 authorization code
     * @param state described by OAuth2 specification and serves to prevent possible CSRF attack
     *
     * @return redirect user to transaction's resource
     */
    @GetMapping(EndpointUris.AUTHORIZATION)
    public RedirectView authorize(@RequestParam("code") String authCode,
                                  @RequestParam("state") String state) {
        Assert.hasText(authCode, "authCode must not be empty");
        Assert.hasText(state, "state must not be empty");

        // call KB OAuth2 API to get access token
        TokenResult tokenResult = oAuth2FlowProvider.getFlow().finish(authCode, state);
        oAuth2FlowProvider.setAccessToken(tokenResult.getAccessToken());

        // authorization is finished -> now redirecting back to the 'transactions' resource
        String transactionUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(EndpointUris.TRANSACTIONS)
                .build().toUriString();
        return new RedirectView(transactionUri);
    }
}
