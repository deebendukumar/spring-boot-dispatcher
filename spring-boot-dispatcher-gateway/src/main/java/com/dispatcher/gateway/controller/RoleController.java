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

import com.dispatcher.gateway.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.RoleRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/roles")
@Tag(name = "Role", description = "Controller for managing role-related operations")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    private RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping(path = "")
    @Operation(summary = "Find all roles", tags = "Role")
    public List<RoleRepresentation> findAll(@RequestParam("page") Optional<Integer> page,
                                            @RequestParam("size") Optional<Integer> size) {
        List<RoleRepresentation> list = service.findAll();
        return list;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find role with it's id", tags = "Role")
    public RoleRepresentation findById(@PathVariable String id) {
        RoleRepresentation roleRepresentation = service.findById(id);
        return roleRepresentation;
    }
}
