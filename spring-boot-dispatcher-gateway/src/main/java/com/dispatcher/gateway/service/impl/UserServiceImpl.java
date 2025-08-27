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
import com.dispatcher.gateway.domain.exception.KeycloakInternalError;
import com.dispatcher.gateway.domain.exception.ResponseErrorMessage;
import com.dispatcher.gateway.domain.exception.UserCreationFailedException;
import com.dispatcher.gateway.domain.exception.UserNotFoundException;
import com.dispatcher.gateway.model.User;
import com.dispatcher.gateway.service.UserService;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final Environment environment;
    private final KeycloakAdapter keycloakAdapter;

    public UserServiceImpl(Environment environment, KeycloakAdapter keycloakAdapter) {
        this.environment = environment;
        this.keycloakAdapter = keycloakAdapter;
    }

    @Override
    public Response create(User user) {
        UsersResource usersResource = findUsersResource();

        // Check if the user with the same username already exists
        if (usersResource.search(user.getUsername()).size() > 0) {
            throw new DuplicateKeyException("User with username '" + user.getUsername() + "' already exists.");
        } else if (usersResource.search(null, null, null, user.getEmail(), null, null).size() > 0) {
            throw new DuplicateKeyException("User with email '" + user.getEmail() + "' already exists.");
        }
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setAttributes(Collections.singletonMap("origin", Arrays.asList(user.getOrigin())));

        userRepresentation.setRealmRoles(user.getRealmRoles());

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(user.getPassword());
        userRepresentation.setCredentials(Collections.singletonList(passwordCred));

        Map<String, Boolean> access = new HashMap<>();
        access.put("view", true);
        userRepresentation.setAccess(access);

        /**
         * driver
         * admin
         * employee
         */
        userRepresentation.setRealmRoles(user.getRealmRoles());

        Response response = usersResource.create(userRepresentation);
        if (response.getStatus() != 201) {
            ResponseErrorMessage message = response.readEntity(ResponseErrorMessage.class);
            throw new UserCreationFailedException(user.getUsername(), response.getStatus(), message.getErrorMessage());
        }
        return response;
    }

    @Override
    public UserRepresentation findById(String id) {
        UsersResource usersResource = findUsersResource();
        return usersResource.get(id).toRepresentation();
    }

    @Override
    public List<UserRepresentation> findAll() {
        UsersResource usersResource = findUsersResource();
        return usersResource.list();
    }

    @Override
    public void logout(String id) {
        try {
            UsersResource usersResource = findUsersResource();
            UserResource userResource = usersResource.get(id);
            userResource.logout();
        } catch (Exception ex) {
            throw new KeycloakInternalError(id,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
    }

    @Override
    public UserRepresentation findByName(String username) {
        UsersResource usersResource = findUsersResource();
        List<UserRepresentation> users = usersResource.search(username);

        if (users != null && !users.isEmpty()) {
            return users.get(0);
        } else {
            throw new UserNotFoundException(username, HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.toString());
        }
    }

    private UsersResource findUsersResource() {
        return keycloakAdapter.findUsersResource(environment.getProperty("keycloak-admin-client.application-realm"));
    }

}
