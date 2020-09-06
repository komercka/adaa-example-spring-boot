package cz.kb.openbanking.adaa.example.springboot.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import org.springframework.util.Assert;

/**
 * Represents JSON object with client's identifiers.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientIdDto {

    /**
     * Client id from client registration.
     */
    private final String clientId;

    /**
     * Client secret from client registration.
     */
    private final String clientSecret;

    /**
     * New instance.
     *
     * @param clientId     client id
     * @param clientSecret client secret
     */
    @JsonCreator
    public ClientIdDto(@JsonProperty("client_id") String clientId, @JsonProperty("client_secret") String clientSecret) {
        Assert.hasText(clientId, "clientId must not be empty");
        Assert.hasText(clientSecret, "clientSecret must not be empty");

        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}
