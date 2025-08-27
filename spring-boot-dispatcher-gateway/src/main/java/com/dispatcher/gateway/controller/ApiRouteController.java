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

import com.dispatcher.gateway.document.ApiRoute;
import com.dispatcher.gateway.service.ApiRouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/routes")
@Tag(name = "Route", description = "Controller for managing route-related operations")
public class ApiRouteController {

    private static final Logger logger = LoggerFactory.getLogger(ApiRouteController.class);

    private ApiRouteService service;

    public ApiRouteController(ApiRouteService service) {
        this.service = service;
    }

    @PostMapping
    @Secured({"ROLE_admin"})
    @Operation(summary = "Create route", tags = "Route")
    public ApiRoute create(@RequestBody ApiRoute route) {
        logger.info("add a route " + route.toJson());
        return service.create(route);
    }

    @Secured({"ROLE_admin", "ROLE_employee"})
    @GetMapping(path = "")
    @Operation(summary = "Find all route", tags = "Route")
    public List<ApiRoute> findAll(@RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        return service.findAll();
    }

    @Secured("ROLE_admin")
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get route with it's id", tags = "Route")
    public ApiRoute get(@PathVariable String id) {
        ApiRoute result = service.get(id);
        logger.info(result.toJson());
        return result;
    }

    @Secured("ROLE_admin")
    @PutMapping(value = "/{id}")
    @Operation(summary = "Update route with it's id", tags = "Route")
    public ApiRoute update(@PathVariable String id, @RequestBody ApiRoute route) {
        ApiRoute result = service.update(id, route);
        logger.info(result.toJson());
        return result;
    }

    @Secured("ROLE_admin")
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete route with it's id", tags = "Route")
    public Boolean delete(@PathVariable String id) {
        Boolean result = true;
        return result;
    }

    @Secured("ROLE_admin")
    @PostMapping(value = "/conditions")
    @Operation(summary = "Search route", tags = "Route")
    public Boolean search() {
        Boolean result = true;
        return result;
    }

    @Secured("ROLE_admin")
    @PostMapping(value = "/overload")
    @Operation(summary = "Overload route", tags = "Route")
    public Boolean overload() {
        Boolean result = true;
        return result;
    }
}
