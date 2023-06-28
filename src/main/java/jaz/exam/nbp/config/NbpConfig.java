package jaz.exam.nbp.config;

import jaz.exam.nbp.webclient.WebClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class NbpConfig {
    @Bean
    public WebClient webClient() {
        return WebClientFactory.getWebClientWithErrorHandling();
    }
}
