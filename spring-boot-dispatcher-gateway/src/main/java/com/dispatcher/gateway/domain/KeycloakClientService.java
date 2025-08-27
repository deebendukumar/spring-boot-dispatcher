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
import com.dispatcher.gateway.document.Application;
import com.dispatcher.gateway.domain.exception.ClientNotFoundException;
import com.dispatcher.gateway.domain.exception.ResponseErrorMessage;
import com.dispatcher.gateway.domain.exception.UserCreationFailedException;
import com.dispatcher.gateway.model.Client;
import com.dispatcher.gateway.util.Passport;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakClientService {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakClientService.class);

    private KeycloakAdapter keycloakAdapter;
    private KeycloakProperties keycloakProperties;

    public KeycloakClientService(KeycloakProperties keycloakProperties, KeycloakAdapter keycloakAdapter) {
        this.keycloakProperties = keycloakProperties;
        this.keycloakAdapter = keycloakAdapter;
    }

    public List<ClientRepresentation> findAll() {
        List<Client> list = new ArrayList<>();
        ClientsResource clientsResource = keycloakAdapter.findClientsResource(keycloakProperties.getApplicationRealm());
        List<ClientRepresentation> clients = clientsResource.findAll();
//        if (!clients.isEmpty()) {
//            list = clients.stream()
//                    .map(clientRepresentation -> {
//                        return transform(clientRepresentation);
//                    })
//                    .collect(Collectors.toList());
//        }
        return clients;
    }

    public Application create(Application application) {
        ClientsResource clientsResource = keycloakAdapter.findClientsResource(keycloakProperties.getApplicationRealm());

        // Check if the application with the same name already exists
        if (clientsResource.findByClientId(application.getName()).size() > 0) {
            throw new DuplicateKeyException("Application with name '" + application.getName() + "' already exists.");
        }

        ClientRepresentation clientRepresentation = new ClientRepresentation();
        clientRepresentation.setName(application.getName());
        clientRepresentation.setClientId(application.getName());
        clientRepresentation.setDescription(application.getDescription());
        clientRepresentation.setProtocol("openid-connect");
        clientRepresentation.setSecret(Passport.encrypt(clientRepresentation.getName()));
        clientRepresentation.setBearerOnly(false);
        clientRepresentation.setStandardFlowEnabled(true);
        clientRepresentation.setImplicitFlowEnabled(false);
        clientRepresentation.setDirectAccessGrantsEnabled(true);
        clientRepresentation.setServiceAccountsEnabled(false);

        try {
            Response response = clientsResource.create(clientRepresentation);

            if (response.getStatus() != 201) {
                ResponseErrorMessage message = response.readEntity(ResponseErrorMessage.class);
                throw new UserCreationFailedException(clientRepresentation.getName(),
                        response.getStatus(),
                        message.getErrorMessage());
            }

            String path = response.getLocation().getPath();
            String clientId = path.replaceAll(".*/([^/]+)$", "$1");
            application.setId(clientId);
            return application;
        } catch (ClientErrorException ex) {
            throw new UserCreationFailedException(clientRepresentation.getName(),
                    ex.getResponse().getStatus(),
                    "Failed to create user on Keycloak. Details: " + ex.getResponse().getStatusInfo().getReasonPhrase());
        }
    }

    public Application update(String id, Application application) {
        ClientsResource clientsResource = keycloakAdapter.findClientsResource(keycloakProperties.getApplicationRealm());

        // Check if the application with the same name already exists
        if (clientsResource.findByClientId(id).size() > 0) {
        }
        return application;
    }


    public ClientRepresentation findById(String id) {
        ClientsResource clientsResource = keycloakAdapter.findClientsResource(keycloakProperties.getApplicationRealm());
        if (clientsResource.findByClientId(id).isEmpty()) {
            throw new ClientNotFoundException(id,
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.toString());
        }
        return clientsResource.findByClientId(id).get(0);
    }

    public List<String> findAllApplicationName() {
        ClientsResource clientsResource = keycloakAdapter.findClientsResource(keycloakProperties.getApplicationRealm());
        List<ClientRepresentation> clients = clientsResource.findAll();

        return clients.stream()
                .map(ClientRepresentation::getName)
                .collect(Collectors.toList());
    }


    private Client transform(ClientRepresentation clientRepresentation) {
        Client client = new Client();
        client.setId(clientRepresentation.getId());
        client.setName(clientRepresentation.getName());
        client.setAdminUrl(clientRepresentation.getAdminUrl());
        client.setBaseUrl(clientRepresentation.getBaseUrl());
        client.setRootUrl(clientRepresentation.getRootUrl());
        client.setDescription(clientRepresentation.getDescription());
        client.setEnabled(clientRepresentation.isEnabled());
        return client;
    }
}
