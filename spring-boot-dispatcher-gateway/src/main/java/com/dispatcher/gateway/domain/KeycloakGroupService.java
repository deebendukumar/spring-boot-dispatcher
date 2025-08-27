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
import com.dispatcher.gateway.model.Group;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakGroupService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakGroupService.class);

    private KeycloakAdapter keycloakAdapter;
    private KeycloakProperties keycloakProperties;

    public KeycloakGroupService(KeycloakProperties keycloakProperties, KeycloakAdapter keycloakAdapter) {
        this.keycloakProperties = keycloakProperties;
        this.keycloakAdapter = keycloakAdapter;
    }

    public List<Group> getGroups() {
        List<Group> list = new ArrayList<>();
        GroupsResource groupsResource = keycloakAdapter.findGroupsResource(keycloakProperties.getApplicationRealm());
        List<GroupRepresentation> clients = groupsResource.groups();
        if (!clients.isEmpty()) {
            list = clients.stream()
                    .map(groupRepresentation -> new Group(
                            groupRepresentation.getId(),
                            groupRepresentation.getName()))
                    .collect(Collectors.toList());
        }
        return list;
    }
}
