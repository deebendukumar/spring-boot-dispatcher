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

package com.dispatcher.inventory.controller;

import com.dispatcher.inventory.contract.CrossDockZoneResponse;
import com.dispatcher.inventory.contract.WarehouseRequest;
import com.dispatcher.inventory.contract.WarehouseResponse;
import com.dispatcher.service.base.AbstractWebController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crossdock-zones")
@Tag(name = "Authentication", description = "")
public class CrossDockZoneController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(CrossDockZoneController.class);

    CrossDockZoneController() {
    }

    @PostMapping(path = "")
    @Operation(summary = "")
    public CrossDockZoneResponse create(@Valid @RequestBody WarehouseRequest warehouseRequest,
                                        HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) {
        return null;
    }


    @PutMapping(path = "/{id}")
    @Operation(summary = "")
    public CrossDockZoneResponse update(@PathVariable String id,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
        return null;
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "")
    public CrossDockZoneResponse delete(@PathVariable String id,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "")
    @Operation(summary = "")
    public CrossDockZoneResponse find(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "")
    public CrossDockZoneResponse findById(@PathVariable String id,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {
        return null;
    }
}
