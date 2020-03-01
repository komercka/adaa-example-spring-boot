package cz.kb.openbanking.adaa.example.springboot.web.controller;

import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Root resource that redirect to authorization URI (if authorization was not successful), or transaction URI
 * (if authorization was successful).
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Controller
@RequestMapping("/")
public class RootController {

    /**
     * OAuth2 authorization provider.
     */
    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * New instance.
     *
     * @param oAuth2FlowProvider OAuth2 authorization provider
     */
    public RootController(OAuth2FlowProvider oAuth2FlowProvider) {
        Assert.notNull(oAuth2FlowProvider, "oAuth2FlowProvider must not be null");

        this.oAuth2FlowProvider = oAuth2FlowProvider;
    }

    /**
     * Resolves application's entry point.
     *
     * @return redirect response depends on previous actions
     */
    @GetMapping
    public RedirectView entryPoint() {
        if (oAuth2FlowProvider.getClientIdentifier() == null
                || oAuth2FlowProvider.getClientIdentifier().getClientId() == null
                || oAuth2FlowProvider.getClientIdentifier().getClientSecret() == null) {
            return new RedirectView(EndpointUris.CLIENT_REGISTRATION_FORM);
        } else {
            return new RedirectView(EndpointUris.TRANSACTIONS);
        }
    }
}
