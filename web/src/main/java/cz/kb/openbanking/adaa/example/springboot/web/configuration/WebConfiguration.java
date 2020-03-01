package cz.kb.openbanking.adaa.example.springboot.web.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import cz.kb.openbanking.adaa.example.springboot.core.configuration.AdaaProperties;
import org.glassfish.jersey.SslConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.Assert;

/**
 * Configuration for the web module.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Configuration
@PropertySource(value = "classpath:web.properties")
public class WebConfiguration {

    /**
     * Provides {@link Client} with set client certificate.
     *
     * @param adaaProperties application properties
     * @return {@link Client} with set client certificate
     */
    @Bean
    public Client clientWithClientCertificate(AdaaProperties adaaProperties) {
        Assert.notNull(adaaProperties, "adaaProperties must not be null");

        KeyStore clientKeyStore;
        try {
            clientKeyStore = KeyStore.getInstance("JKS");
            clientKeyStore.load(new FileInputStream(adaaProperties.getKeystoreLocation()),
                    adaaProperties.getKeystorePassword().toCharArray());
        } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException | IOException e) {
            throw new IllegalStateException("Error was occurred during getting a keystore with a client certificate. " +
                    "Error: " + e.getMessage(), e);
        }

        SslConfigurator sslConfig = SslConfigurator.newInstance()
                .keyStore(clientKeyStore)
                .keyStorePassword(adaaProperties.getKeystorePassword())
                .keyPassword(adaaProperties.getClientCertPassword());

        return ClientBuilder.newBuilder()
                .hostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier())
                .sslContext(sslConfig.createSSLContext())
                .build();
    }
}
