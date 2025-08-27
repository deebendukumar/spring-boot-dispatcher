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

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Data
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "keycloak-admin-client")
public class KeycloakProperties {

    @Getter(AccessLevel.NONE)
    private boolean initializeOnStartup;

    public boolean initializeOnStartup() {
        return initializeOnStartup;
    }

    @NotBlank
    @Value("${keycloak-admin-client.master-realm}")
    private String masterRealm;
    @Value("${keycloak-admin-client.application-realm}")
    private String applicationRealm;
    @Value("${keycloak-admin-client.client-id}")
    private String clientId;
    @Value("${keycloak-admin-client.client-secret}")
    private String clientSecret;
    @Value("${keycloak-admin-client.username}")
    private String username;
    @Value("${keycloak-admin-client.password}")
    private String password;
    @Value("${keycloak-admin-client.uri}")
    private String uri;

    private Integer connectionPoolSize = 16;
}
