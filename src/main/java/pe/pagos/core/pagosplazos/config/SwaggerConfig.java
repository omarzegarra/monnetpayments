package pe.pagos.core.pagosplazos.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    private static final String COPLAZOSCOMMAND = "/pagosplazos";

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(COPLAZOSCOMMAND);
        devServer.setDescription("Servicio para registrar pagos en cuotas en BD");
        Contact contact = new Contact();
        contact.setEmail("OMARH.ZEGARRA@GMAIL.COM");
        contact.setName("OMAR-ZEGARRA-BALMACEDA");

        Info info =
                new Info()
                        .title("API REST services-Prueba")
                        .version("1.0")
                        .contact(contact)
                        .description("Especificacion de REST API services");

        List<Server> list = Arrays.asList(devServer);

        return new OpenAPI().info(info).servers(list);
    }
}
