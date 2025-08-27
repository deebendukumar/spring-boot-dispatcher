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
import com.dispatcher.gateway.model.Role;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakRoleService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakRoleService.class);

    private KeycloakAdapter keycloakAdapter;
    private KeycloakProperties keycloakProperties;

    public KeycloakRoleService(KeycloakProperties keycloakProperties, KeycloakAdapter keycloakAdapter) {
        this.keycloakProperties = keycloakProperties;
        this.keycloakAdapter = keycloakAdapter;
    }

    /**
     * This will list down all the roles that belongs to a realm
     *
     * @return
     */
    public List<Role> getRoles() {
        List<Role> list = new ArrayList<>();
        RolesResource rolesResource = keycloakAdapter.findRealmRolesResource(keycloakProperties.getApplicationRealm());
        List<RoleRepresentation> clients = rolesResource.list();
        if (!clients.isEmpty()) {
            list = clients.stream()
                    .map(roleRepresentation -> new Role(
                            roleRepresentation.getId(),
                            roleRepresentation.getName(),
                            roleRepresentation.getDescription()))
                    .collect(Collectors.toList());
        }
        return list;
    }
}
