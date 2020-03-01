package cz.kb.openbanking.adaa.example.springboot.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Application's entry point.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "cz.kb.openbanking.adaa.example.springboot")
public class AdaaExampleApplication {

    /**
     * Entry-point of the application.
     *
     * @param args application arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(AdaaExampleApplication.class, args);
    }
}
