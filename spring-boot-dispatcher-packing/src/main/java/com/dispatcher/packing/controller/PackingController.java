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

package com.dispatcher.packing.controller;

import com.dispatcher.packing.contract.PackingRequest;
import com.dispatcher.packing.contract.PackingResponse;
import com.dispatcher.common.base.AbstractWebController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packing")
@Tag(name = "Authentication", description = "")
public class PackingController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(PackingController.class);

    PackingController() {
    }

    @PostMapping(path = "/start")
    @Operation(summary = "")
    public PackingResponse wave(@Valid @RequestBody PackingRequest packingRequest,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {
        return null;
    }


    @PostMapping(path = "/scan")
    @Operation(summary = "")
    public PackingResponse batch(@Valid @RequestBody PackingRequest packingRequest,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/confirm")
    @Operation(summary = "")
    public PackingResponse findTasks(@Valid @RequestBody PackingRequest packingRequest,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/cancel")
    @Operation(summary = "")
    public PackingResponse cancel(@Valid @RequestBody PackingRequest packingRequest,
                                         HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/orders")
    @Operation(summary = "")
    public PackingResponse orders(@Valid @RequestBody PackingRequest packingRequest,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/history")
    @Operation(summary = "")
    public PackingResponse history(@Valid @RequestBody PackingRequest packingRequest,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }

}
