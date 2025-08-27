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

package com.dispatcher.gateway.domain;

import com.dispatcher.gateway.config.KeycloakProperties;
import com.dispatcher.gateway.model.User;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakUserService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakUserService.class);

    private KeycloakAdapter keycloakAdapter;
    private KeycloakProperties keycloakProperties;

    public KeycloakUserService(KeycloakProperties keycloakProperties, KeycloakAdapter keycloakAdapter) {
        this.keycloakProperties = keycloakProperties;
        this.keycloakAdapter = keycloakAdapter;
    }

    public List<User> getClients() {
        List<User> list = new ArrayList<>();
        UsersResource usersResource = keycloakAdapter.findUsersResource(keycloakProperties.getApplicationRealm());
        List<UserRepresentation> clients = usersResource.list();
        if (!clients.isEmpty()) {
            list = clients.stream()
                    .map(userRepresentation -> {
                        return transform(userRepresentation);
                    })
                    .collect(Collectors.toList());
        }
        return list;
    }

    private User transform(UserRepresentation representation) {
        User user = new User();
        user.setId(representation.getId());
        user.setEmail(representation.getEmail());
        return user;
    }
}
