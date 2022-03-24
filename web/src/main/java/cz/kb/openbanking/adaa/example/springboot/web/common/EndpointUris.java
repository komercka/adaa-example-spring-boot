package cz.kb.openbanking.adaa.example.springboot.web.common;

import cz.kb.openbanking.adaa.example.springboot.web.controller.AccountController;
import cz.kb.openbanking.adaa.example.springboot.web.controller.ClientRegistrationController;
import cz.kb.openbanking.adaa.example.springboot.web.controller.TransactionHistoryController;

/**
 * Contains application endpoints' URIs.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public final class EndpointUris {

    /**
     * Authorization back URI to Oauth2 for getting OAuth2 authorization code.
     */
    public static final String AUTHORIZATION = "oauth2/authorize";

    /**
     * Base URI for all registration URI in {@link ClientRegistrationController}.
     */
    private static final String REGISTRATION = "register";

    /**
     * Client registration back URI for getting client id and client secret.
     */
    public static final String CLIENT_REGISTRATION = REGISTRATION + "/client";

    /**
     * URI to show application registration form.
     */
    public static final String CLIENT_REGISTRATION_FORM = REGISTRATION + "/form";

    /**
     * URI to get software statement by calling Client Registration API.
     */
    public static final String SOFTWARE_STATEMENT_REGISTRATION = REGISTRATION + "/software-statement";

    /**
     * URI to get transaction history in {@link TransactionHistoryController}.
     */
    public static final String TRANSACTIONS = "transactions";

    /**
     * URI to get accounts in {@link AccountController}.
     */
    public static final String ACCOUNTS = "accounts";

    /**
     * URI to call KB login page for register application instance.
     */
    public static final String SAML_REGISTRATION = "saml/register";

    /**
     * No instance.
     */
    private EndpointUris() {
    }
}
