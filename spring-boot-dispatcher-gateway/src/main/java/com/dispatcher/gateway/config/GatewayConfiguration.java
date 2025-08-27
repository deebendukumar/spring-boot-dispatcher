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

@Profile("!dev")
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class GatewayConfiguration {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**"
    };

    private Environment environment;
    private final ApplicationProperties applicationProperties;

    public GatewayConfiguration(Environment environment, ApplicationProperties applicationProperties) {
        this.environment = environment;
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        //http.cors().disable();
        //http.oauth2Login();
        http.authorizeExchange().pathMatchers(AUTH_WHITELIST).permitAll();
        http.authorizeExchange().anyExchange().authenticated();
        http.oauth2ResourceServer().jwt();
        return http.build();
    }

    @Bean
    public RouteLocator routeLocator(ApiRouteService apiRouteService, RouteLocatorBuilder routeLocatorBuilder) {
        return new ApiRouteLocatorImpl(environment,
                applicationProperties,
                apiRouteService,
                routeLocatorBuilder);
    }
}