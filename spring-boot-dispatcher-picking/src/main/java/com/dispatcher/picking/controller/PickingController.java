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

package com.dispatcher.picking.controller;

import com.dispatcher.picking.contract.PickingRequest;
import com.dispatcher.picking.contract.PickingResponse;
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
@RequestMapping("/picking")
@Tag(name = "Authentication", description = "")
public class PickingController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(PickingController.class);

    PickingController() {
    }

    @PostMapping(path = "/wave")
    @Operation(summary = "")
    public PickingResponse wave(@Valid @RequestBody PickingRequest pickingRequest,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
        return null;
    }


    @PostMapping(path = "/batch")
    @Operation(summary = "")
    public PickingResponse batch(@Valid @RequestBody PickingRequest pickingRequest,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/tasks")
    @Operation(summary = "")
    public PickingResponse findTasks(@Valid @RequestBody PickingRequest pickingRequest,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/tasks/{id}")
    @Operation(summary = "")
    public PickingResponse findTasksById(@Valid @RequestBody PickingRequest pickingRequest,
                                             HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/start")
    @Operation(summary = "")
    public PickingResponse start(@Valid @RequestBody PickingRequest pickingRequest,
                                                 HttpServletRequest httpServletRequest,
                                                 HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/scan")
    @Operation(summary = "")
    public PickingResponse confirm(@Valid @RequestBody PickingRequest pickingRequest,
                                                 HttpServletRequest httpServletRequest,
                                                 HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/short")
    @Operation(summary = "")
    public PickingResponse shorts(@Valid @RequestBody PickingRequest pickingRequest,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/cancel")
    @Operation(summary = "")
    public PickingResponse cancel(@Valid @RequestBody PickingRequest pickingRequest,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/history")
    @Operation(summary = "")
    public PickingResponse history(@Valid @RequestBody PickingRequest pickingRequest,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }

}
