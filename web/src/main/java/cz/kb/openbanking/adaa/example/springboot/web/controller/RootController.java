package cz.kb.openbanking.adaa.example.springboot.web.controller;

import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
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
@AllArgsConstructor
public class RootController {

    /**
     * OAuth2 authorization provider.
     */
    private final OAuth2FlowProvider oAuth2FlowProvider;

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
