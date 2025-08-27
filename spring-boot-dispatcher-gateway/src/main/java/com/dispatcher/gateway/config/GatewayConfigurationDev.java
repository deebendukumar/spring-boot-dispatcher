/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dispatcher.gateway.config;

import com.dispatcher.gateway.service.ApiRouteService;
import com.dispatcher.gateway.service.impl.ApiRouteLocatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class GatewayConfigurationDev {

    private static final Logger logger = LoggerFactory.getLogger(GatewayConfigurationDev.class);

    @Value("${spring.security.cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${spring.security.cors.allowed-methods}")
    private List<String> allowedMethods;

    @Value("${spring.security.cors.allowed-headers}")
    private List<String> allowedHeaders;

    @Value("${spring.security.cors.max-age}")
    private Long maxAge;

    private static final String[] AUTH_WHITELIST = {
            "/actuator/**",
    };

    private Environment environment;
    private final ApplicationProperties applicationProperties;

    public GatewayConfigurationDev(Environment environment, ApplicationProperties applicationProperties) {
        this.environment = environment;
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        //http.oauth2Login();
        http.authorizeExchange().pathMatchers(AUTH_WHITELIST).permitAll();
        http.authorizeExchange().anyExchange().authenticated();
        http.oauth2ResourceServer().jwt();
        return http.build();
    }

    @Bean
    public RouteLocator routeLocator(ApiRouteService apiRouteService, RouteLocatorBuilder routeLocatorBuilder) {
        return new ApiRouteLocatorImpl(
                environment,
                applicationProperties,
                apiRouteService,
                routeLocatorBuilder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedOrigins(getAllowedOriginsList());
        configuration.setAllowedMethods(allowedMethods);
        configuration.setAllowedHeaders(allowedHeaders);
        configuration.setMaxAge(maxAge);
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Bean
//    public ErrorWebExceptionHandler errorWebExceptionHandler() {
//        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
//        logger.info("Created GlobalExceptionHandler bean: {}", globalExceptionHandler);
//        return globalExceptionHandler;
//    }

    private List<String> getAllowedOriginsList() {
        return Arrays.asList(allowedOrigins.split(","));
    }
}
