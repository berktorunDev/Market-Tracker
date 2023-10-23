package com.market.tracker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/*
 * This class configures the OpenAPI documentation for the Market Tracker application.
 * The OpenAPI documentation is accessible via the following URLs:
 * - Swagger UI: http://localhost:8080/swagger-ui/index.html#/ (Developer-friendly interface)
 * - JSON Document: http://localhost:8080/v3/api-docs (API documentation in JSON format)
 *
 * Info: Contains general information about the API.
 * - "contact": Includes contact information related to the API.
 * - "description": Provides a description of the API.
 * - "title": Specifies the title of the API.
 * - "version": Defines the version of the API.
 *
 * Servers: Defines the servers for the API.
 * - "description": Describes the server.
 * - "url": Specifies the base URL of the server. In this example, the URL for the local environment is provided.
 */

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Berk Torun", email = "berktorun.dev@gmail.com", url = "https://www.linkedin.com/in/berk-torun/"), description = "OpenApi documentation for Market Tracker App", title = "OpenApi specification - BerktorunDev", version = "1.0"), servers = {
        @Server(description = "Local ENV", url = "http://localhost:8080")
})
public class OpenApiConfig {

}
