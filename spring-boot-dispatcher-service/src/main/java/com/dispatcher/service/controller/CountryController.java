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

package com.dispatcher.service.controller;

import com.dispatcher.common.base.AbstractWebController;
import com.dispatcher.service.service.impl.CountryApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/countries")
@Tag(name = "Countries", description = "API to manage country data")
public class CountryController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    private final CountryApiService service;

    CountryController(CountryApiService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get country by ID")
    public HashMap<String, Object> findById(@PathVariable Integer id,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        return service.findByPKey(id);
    }

    @GetMapping(path = "")
    @Operation(summary = "Get all countries")
    public List<HashMap<String, Object>> find(HttpServletRequest request,
                                              HttpServletResponse response) {
        return service.findAll();
    }
}
