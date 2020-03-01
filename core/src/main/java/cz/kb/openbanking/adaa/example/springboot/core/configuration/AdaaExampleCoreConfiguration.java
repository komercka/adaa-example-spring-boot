package cz.kb.openbanking.adaa.example.springboot.core.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for core module.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties(AdaaProperties.class)
public class AdaaExampleCoreConfiguration {
}
