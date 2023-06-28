package jaz.exam.nbp.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {
    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("NBP API Jaz zaliczenie")
                        .description("Just NBP API Jaz zaliczenie")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("example")
                                .email("exaample@pjatk.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation")
                        .url("google.com"));
    }

}
