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
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.*;
import org.keycloak.admin.client.token.TokenService;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
public class KeycloakAdapter {

    private Keycloak keycloak;
    private TokenService tokenService;

    public KeycloakAdapter(KeycloakProperties keycloakProperties) {
        ResteasyClient restClient = new ResteasyClientBuilder().connectionPoolSize(keycloakProperties.getConnectionPoolSize()).build();
        keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakProperties.getUri())
                .realm(keycloakProperties.getApplicationRealm())
                .grantType(OAuth2Constants.PASSWORD)
                .username(keycloakProperties.getUsername())
                .password(keycloakProperties.getPassword())
                .clientId(keycloakProperties.getClientId())
                .clientSecret(keycloakProperties.getClientSecret())
                .resteasyClient(restClient)
                .build();
        tokenService = restClient.target(keycloakProperties.getUri()).proxy(TokenService.class);
    }

    public List<RealmRepresentation> findAll() {
        RealmsResource realmsResource = keycloak.realms();
        return realmsResource.findAll();
    }

    public RealmResource findRealmResource(String realm) {
        return keycloak.realm(realm);
    }

    public ClientsResource findClientsResource(String realm) {
        return findRealmResource(realm).clients();
    }

    public ClientResource findClientResource(String realm, String clientId) {
        ClientsResource clientsResource = findClientsResource(realm);
        String clientUuid = findClientUuid(clientsResource, clientId);
        return clientsResource.get(clientUuid);
    }

    public UsersResource findUsersResource(String realm) {
        RealmResource realmResource = findRealmResource(realm);
        return realmResource.users();
    }

    public GroupsResource findGroupsResource(String realm) {
        RealmResource realmResource = findRealmResource(realm);
        return realmResource.groups();
    }

    public RolesResource findRealmRolesResource(String realm) {
        RealmResource realmResource = findRealmResource(realm);
        return realmResource.roles();
    }

    public RolesResource findClientRolesResource(String realm, String clientId) {
        ClientResource clientResource = findClientResource(realm, clientId);
        return clientResource.roles();
    }

    public String findClientUuid(String realm, String clientId) {
        ClientsResource clientsResource = findClientsResource(realm);
        return findClientUuid(clientsResource, clientId);
    }

    public String findClientUuid(ClientsResource clientsResource, String clientId) {
        List<ClientRepresentation> clientRepresentations = clientsResource.findByClientId(clientId);
        if (clientRepresentations.isEmpty()) {
            throw new NotFoundException("client not found for clientId '" + clientId + "'");
        }
        return clientRepresentations.stream().findFirst().get().getId();
    }

    public TokenService getTokenService() {
        return tokenService;
    }
}
