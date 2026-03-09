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

package com.dispatcher.receiving.controller;

import com.dispatcher.receiving.contract.ReceivingRequest;
import com.dispatcher.receiving.contract.ReceivingResponse;
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
@RequestMapping("/receiving")
@Tag(name = "Authentication", description = "")
public class ReceivingController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(ReceivingController.class);

    ReceivingController() {
    }

    @PostMapping(path = "/asn")
    @Operation(summary = "")
    public ReceivingResponse create(@Valid @RequestBody ReceivingRequest receivingRequest,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
        return null;
    }


    @PutMapping(path = "/asn/{id}")
    @Operation(summary = "")
    public ReceivingResponse findAdvanceShipmentNoticeById(@PathVariable String id,
                                                           HttpServletRequest httpServletRequest,
                                                           HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/start")
    @Operation(summary = "")
    public ReceivingResponse start(@PathVariable String id,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/scan")
    @Operation(summary = "")
    public ReceivingResponse scan(HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/complete")
    @Operation(summary = "")
    public ReceivingResponse complete(@PathVariable String id,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/history")
    @Operation(summary = "")
    public ReceivingResponse history(@PathVariable String id,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
        return null;
    }
}
