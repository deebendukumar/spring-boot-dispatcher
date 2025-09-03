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

package com.dispatcher.app.controller.pim;

import com.dispatcher.service.base.AbstractWebController;
import com.dispatcher.app.service.pim.WarehouseApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warehouses")
@Tag(name = "Warehouses", description = "API for manage warehouses")
public class WarehouseController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

    private final WarehouseApiService service;

    WarehouseController(WarehouseApiService service) {
        this.service = service;
    }

    @GetMapping(path = "")
    @Operation(summary = "Get all warehouses")
    public List<HashMap<String, Object>> find(@RequestParam("name") Optional<String> code,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
//        return service.findAll();
        return service.test();
    }

//    @GetMapping(path = "/{id}")
//    @Operation(summary = "Get warehouse by ID")
//    public HashMap<String, Object> findById(@PathVariable Integer id,
//                                            HttpServletRequest request,
//                                            HttpServletResponse response) {
//        return service.findByPKey(id);
//    }
}
