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

package com.dispatcher.putaway.controller;

import com.dispatcher.putaway.contract.PutawayRequest;
import com.dispatcher.putaway.contract.PutawayResponse;
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
@RequestMapping("/putaway")
@Tag(name = "Authentication", description = "")
public class PutawayController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(PutawayController.class);

    PutawayController() {
    }

    @PostMapping(path = "/tasks")
    @Operation(summary = "")
    public PutawayResponse createTask(@Valid @RequestBody PutawayRequest putawayRequest,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }


    @GetMapping(path = "/tasks")
    @Operation(summary = "")
    public PutawayResponse findTask(@PathVariable String id,
                                                           HttpServletRequest httpServletRequest,
                                                           HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/tasks/{id}")
    @Operation(summary = "")
    public PutawayResponse findTaskById(@PathVariable String id,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/suggest-location")
    @Operation(summary = "")
    public PutawayResponse suggestLocation(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/complete")
    @Operation(summary = "")
    public PutawayResponse confirm(@PathVariable String id,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/history")
    @Operation(summary = "")
    public PutawayResponse cancel(@PathVariable String id,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
        return null;
    }
}
