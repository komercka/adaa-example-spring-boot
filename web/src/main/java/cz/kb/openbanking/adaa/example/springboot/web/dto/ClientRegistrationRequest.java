package cz.kb.openbanking.adaa.example.springboot.web.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

/**
 * Contains indicators of transaction type: credit or debit.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@EqualsAndHashCode
@ToString
public class ClientRegistrationRequest {

    /**
     * Default value for {@link #encryptionAlg}.
     */
    private static final String ENCRYPTION_ALGORITHM_DEFAULT_VALUE = "AES-256";

    /**
     * Name of the client application.
     */
    @Getter
    private final String clientName;

    /**
     * Name of the client application in English.
     */
    @Getter
    private final String clientNameEn;

    /**
     * Client application type.
     */
    @Getter
    private final String applicationType;

    /**
     * List of URIs to which authorization server will send redirect after finish client registration process.
     */
    private final List<String> redirectUris;

    /**
     * List of authorization scopes (see details: https://tools.ietf.org/html/rfc6749#section-3.3).
     */
    private final List<String> scope;

    /**
     * Base64 encoded software statement.
     */
    @Getter
    private final String softwareStatement;

    /**
     * Encryption algorithm.
     */
    @Getter
    private final String encryptionAlg;

    /**
     * Encryption key.
     */
    @Getter
    private final String encryptionKey;

    /**
     * New instance.
     * Default value {@value #ENCRYPTION_ALGORITHM_DEFAULT_VALUE} for attribute {@link #encryptionAlg} will be used.
     *
     * @param clientName        client application name
     * @param clientNameEn      client application name in English
     * @param applicationType   type of the client application
     * @param redirectUris      redirect URIs to which client registration data will be returned
     * @param scope             list of needed OAuth2 scopes
     * @param softwareStatement Base64 encoded software statement
     * @param encryptionKey     Base64 encoded 256-bit encryption key
     */
    public ClientRegistrationRequest(String clientName, @Nullable String clientNameEn, String applicationType,
                                     Collection<String> redirectUris, Collection<String> scope,
                                     String softwareStatement, String encryptionKey)
    {
        this(clientName, clientNameEn, applicationType, redirectUris, scope, softwareStatement,
                encryptionKey, ENCRYPTION_ALGORITHM_DEFAULT_VALUE);
    }

    /**
     * New instance.
     *
     * @param clientName        client application name
     * @param clientNameEn      client application name in English
     * @param applicationType   type of the client application
     * @param redirectUris      redirect URIs to which client registration data will be returned
     * @param scope             list of needed OAuth2 scopes
     * @param softwareStatement Base64 encoded software statement
     * @param encryptionKey     Base64 encoded 256-bit encryption key
     * @param encryptionAlg     encryption algorithm
     */
    public ClientRegistrationRequest(String clientName, @Nullable String clientNameEn, String applicationType,
                                     Collection<String> redirectUris, Collection<String> scope,
                                     String softwareStatement, String encryptionKey, String encryptionAlg)
    {
        Assert.hasText(clientName, "clientName must not be empty");
        Assert.hasText(applicationType, "applicationType must not be empty");
        Assert.notEmpty(redirectUris, "redirectUris must not be empty");
        Assert.notEmpty(scope, "scope must not be empty");
        Assert.hasText(softwareStatement, "softwareStatement must not be empty");
        Assert.hasText(encryptionKey, "encryptionKey must not be empty");
        Assert.hasText(encryptionAlg, "encryptionAlg must not be empty");

        this.clientName = clientName;
        this.clientNameEn = clientNameEn;
        this.applicationType = applicationType;
        this.redirectUris = new ArrayList<>(redirectUris);
        this.scope = new ArrayList<>(scope);
        this.softwareStatement = softwareStatement;
        this.encryptionKey = encryptionKey;
        this.encryptionAlg = encryptionAlg;
    }

    /**
     * Gets list of URIs to which authorization server will send redirect after finish client registration process.
     *
     * @return list of URIs to which authorization server will send redirect after finish client registration process
     */
    public List<String> getRedirectUris() {
        return Collections.unmodifiableList(redirectUris);
    }

    /**
     * Gets list of authorization scopes (see details: https://tools.ietf.org/html/rfc6749#section-3.3).
     *
     * @return list of authorization scopes
     */
    public List<String> getScope() {
        return Collections.unmodifiableList(scope);
    }
}
