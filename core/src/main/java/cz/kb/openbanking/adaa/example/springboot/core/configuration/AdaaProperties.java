package cz.kb.openbanking.adaa.example.springboot.core.configuration;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * ADAA example application properties.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Data
@Validated
@ConfigurationProperties(prefix = AdaaProperties.ADAA_PROPERTY_PREFIX, ignoreUnknownFields = false)
public class AdaaProperties {

    /**
     * Prefix for all properties configuration for adaa example application.
     */
    public static final String ADAA_PROPERTY_PREFIX = "adaa-example";

    /**
     * Client's IBAN.
     */
    @NotBlank
    private String iban;

    /**
     * Client's account currency.
     */
    @NotBlank
    private String currency;

    /**
     * URI of the page of the KB Client Registration page.
     */
    @NotBlank
    private String clientRegistrationUri;

    /**
     * URI of the page of the KB Authorization server for getting OAuth2 authorization code.
     */
    @NotBlank
    private String authorizationUri;

    /**
     * URI of the endpoint of the KB OAuth2 API for getting access token.
     */
    @NotBlank
    private String accessTokenUri;

    /**
     * Base64 encoded 256-bit key that used during getting client registration process.
     */
    @NotBlank
    private String secret;

    /**
     * Path to the keystore with client certificate.
     */
    @NotBlank
    private String keystoreLocation;

    /**
     * Password of the keystore with client certificate.
     */
    @NotBlank
    private String keystorePassword;

    /**
     * Password of the client certificate.
     */
    @NotBlank
    private String clientCertPassword;

}
