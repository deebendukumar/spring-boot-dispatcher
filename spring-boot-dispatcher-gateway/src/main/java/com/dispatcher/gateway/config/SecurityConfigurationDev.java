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

import com.dispatcher.gateway.exception.JwtAuthenticationEntryPoint;
import com.dispatcher.gateway.security.AuthenticationProvider;
import com.dispatcher.gateway.security.KeycloakRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Profile("dev")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigurationDev {

    private Environment environment;
    private AuthenticationProvider provider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private static final RequestMatcher PROTECTED_URLS = new AndRequestMatcher(
            new AntPathRequestMatcher("/apim/api/v1.0/**")
    );

    public SecurityConfigurationDev(Environment environment,
                                    final AuthenticationProvider authenticationProvider,
                                    final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        super();
        this.environment = environment;
        this.provider = authenticationProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        httpSecurity.csrf((csrf) -> csrf.disable());
//        httpSecurity.cors().disable();
//        httpSecurity.authorizeRequests()
//                .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                //.antMatchers(HttpMethod.GET, "/apim/api/v1.0/routes").hasRole(environment.getProperty("spring.security.allowed.roles"))
//                .anyRequest()
//                .authenticated();
//        httpSecurity
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthenticationConverter);
        return httpSecurity.build();
    }
}
