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

import com.dispatcher.gateway.security.AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Profile("!dev")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private Environment environment;
    private AuthenticationProvider provider;

    public SecurityConfiguration() {
    }

    private static final RequestMatcher UNPROTECTED_URLS = new AndRequestMatcher(
            new AntPathRequestMatcher("/tokens/**")
    );

    private static final RequestMatcher PROTECTED_URLS = new AndRequestMatcher(
            new AntPathRequestMatcher("/**")
    );

    public SecurityConfiguration(Environment environment, final AuthenticationProvider authenticationProvider) {
        super();
        this.environment = environment;
        this.provider = authenticationProvider;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf((csrf) -> csrf.disable());
        httpSecurity.cors(cors -> cors.disable());
        httpSecurity.authorizeHttpRequests((authz) -> authz
                .requestMatchers(PROTECTED_URLS).authenticated())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
