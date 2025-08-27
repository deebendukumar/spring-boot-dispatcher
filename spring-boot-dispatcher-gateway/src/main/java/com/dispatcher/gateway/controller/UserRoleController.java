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

import com.dispatcher.gateway.document.Application;
import com.dispatcher.gateway.model.Role;
import com.dispatcher.gateway.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/{user-id}/role")
public class UserRoleController {

    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    private UserService service;

    public UserRoleController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Secured({"ROLE_admin"})
    public ResponseEntity<Application> assign(@RequestBody Role role) {
        Application result = new Application();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Secured({"ROLE_admin"})
    @DeleteMapping(value = "/{role-id}")
    public ResponseEntity<Application> remove(@PathVariable String roleId) {
        Application result = new Application();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
