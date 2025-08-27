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

package com.dispatcher.pim.controller;

import com.dispatcher.common.base.AbstractWebController;
import com.dispatcher.pim.entity.Rack;
import com.dispatcher.pim.service.RackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/racks")
@Tag(name = "Racks", description = "")
public class RackController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(RackController.class);

    private final RackService<Rack> service;

    RackController(RackService<Rack> service) {
        this.service = service;
    }

    @PostMapping(path = "")
    @Operation(summary = "")
    public Rack create(@RequestBody Rack rack,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        return service.create(rack);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "")
    public Rack find(@PathVariable String id,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        return service.findByPKey(id);
    }

    @GetMapping(path = "")
    @Operation(summary = "")
    public List<Rack> findBy(@RequestParam("phone") Optional<String> phone,
                            @RequestParam("name") Optional<String> name,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        return service.findAll();
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "")
    public Rack update(@PathVariable String id,
                          @RequestBody Rack rack,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        return service.update(id, rack);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "")
    public Rack delete(@PathVariable String id,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        return service.delete(id);
    }
}
