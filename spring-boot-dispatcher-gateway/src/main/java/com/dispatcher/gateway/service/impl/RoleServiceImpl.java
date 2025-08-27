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

package com.dispatcher.gateway.service.impl;

import com.dispatcher.gateway.domain.KeycloakAdapter;
import com.dispatcher.gateway.service.RoleService;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final Environment environment;
    private final KeycloakAdapter keycloakAdapter;

    public RoleServiceImpl(Environment environment, KeycloakAdapter keycloakAdapter) {
        this.environment = environment;
        this.keycloakAdapter = keycloakAdapter;
    }

    @Override
    public RoleRepresentation findById(String id) {
        RolesResource rolesResource = findRealmRolesResource();
        RoleResource roleResource = rolesResource.get(id);
        return roleResource.toRepresentation();
    }

    @Override
    public List<RoleRepresentation> findAll() {
        RolesResource rolesResource = findRealmRolesResource();
        return rolesResource.list();
    }

    private RolesResource findRealmRolesResource() {
        return keycloakAdapter.findRealmRolesResource(environment.getProperty("keycloak-admin-client.application-realm"));
    }
}
