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

import com.dispatcher.gateway.model.Realm;
import org.keycloak.representations.idm.RealmRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakRealmService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakRealmService.class);

    private KeycloakAdapter keycloakAdapter;

    public KeycloakRealmService(KeycloakAdapter keycloakAdapter) {
        this.keycloakAdapter = keycloakAdapter;
    }

    public List<Realm> getRealms() {
        List<Realm> realmNames = new ArrayList<>();
        List<RealmRepresentation> realms = keycloakAdapter.findAll();
        if (!realms.isEmpty()) {
            realmNames = realms.stream().map(RealmRepresentation::getRealm).map(Realm::fromValue).collect(Collectors.toList());
        }
        return realmNames;
    }

}
