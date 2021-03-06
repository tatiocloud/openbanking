package io.ram.openbanking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String AUTH_SERVER = "http://localhost";
    private static final String CLIENT_ID = "123";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .paths(PathSelectors.any())
                        .build();
                        //.securitySchemes(Arrays.asList(securityScheme()))
                        //.securityContexts(Arrays.asList(securityContext()));
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                        .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
                        .tokenRequestEndpoint(
                                        new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
                        .build();

        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
                        .grantTypes(Arrays.asList(grantType))
                        .scopes(Arrays.asList(scopes()))
                        .build();
        return oauth;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                        .securityReferences(
                                        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                        .forPaths(PathSelectors.regex("/foos.*"))
                        .build();
    }
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                        new AuthorizationScope("read", "for read operations"),
                        new AuthorizationScope("write", "for write operations"),
                        new AuthorizationScope("foo", "Access foo API") };
        return scopes;
    }
}
