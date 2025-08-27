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

package com.dispatcher.gateway.controller;

import com.dispatcher.gateway.domain.exception.UserNameExtractException;
import com.dispatcher.gateway.model.User;
import com.dispatcher.gateway.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Controller for managing user-related operations")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Secured({"ROLE_admin", "ROLE_employee"})
    @Operation(summary = "Create user", tags = "User")
    public ResponseEntity<Response> create(@RequestBody User user) {
        Response response = service.create(user);
        String path = response.getLocation().getPath();
        String userId = path.replaceAll(".*/([^/]+)$", "$1");
        user.setId(userId);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping(path = "")
    @Secured({"ROLE_admin", "ROLE_employee"})
    @Operation(summary = "Find all users", tags = "User")
    public ResponseEntity<List<UserRepresentation>> findAll(@RequestParam("page") Optional<Integer> page,
                                                            @RequestParam("size") Optional<Integer> size) {
        List<UserRepresentation> list = service.findAll();
        return new ResponseEntity<List<UserRepresentation>>(list, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @Secured({"ROLE_admin", "ROLE_employee"})
    @Operation(summary = "Find user with it's id", tags = "User")
    public ResponseEntity<UserRepresentation> findById(@PathVariable String id) {
        UserRepresentation userRepresentation = service.findById(id);
        return new ResponseEntity<UserRepresentation>(userRepresentation, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/logout")
    @Operation(summary = "Logout", tags = "User")
    public ResponseEntity<UserRepresentation> logout(@PathVariable String id) {
        UserRepresentation result = new UserRepresentation();
        service.logout(id);
        return new ResponseEntity<UserRepresentation>(result, HttpStatus.OK);
    }

    @GetMapping("/info")
    @Secured({"ROLE_admin", "ROLE_employee"})
    @Operation(summary = "Get user information", tags = "User")
    public ResponseEntity<UserRepresentation> getUserInfo() {
        String username = extractUsernameFromToken();
        UserRepresentation userRepresentation = service.findByName(username);
        return new ResponseEntity<>(userRepresentation, HttpStatus.OK);
    }

    private String extractUsernameFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            Object preferredUsernameClaim = jwt.getClaim("preferred_username");
            if (preferredUsernameClaim != null) {
                return preferredUsernameClaim.toString();
            }
        } else {
            logger.warn("Unexpected principal type: {}", authentication.getPrincipal().getClass().getName());
        }
        throw new UserNameExtractException(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.toString());
    }

}
