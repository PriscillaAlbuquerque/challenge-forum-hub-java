package forumHub.api.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class springDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerkey",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("API Forumhub")
                        .description("API Rest da aplicação Forumhub, contendo as funcionalidades de CRUD dos tópicos.")
                        .contact(new Contact()
                                .name("Priscilla Albuquerque")
                                .email("priscilla.ferreira89@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE2.0.")));
    }
}
// classe de configuração do spring doc
