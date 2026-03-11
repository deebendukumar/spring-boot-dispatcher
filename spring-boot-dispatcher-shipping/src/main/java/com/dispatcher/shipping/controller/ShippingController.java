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

package com.dispatcher.shipping.controller;

import com.dispatcher.common.base.AbstractWebController;
import com.dispatcher.shipping.contract.ShippingRequest;
import com.dispatcher.shipping.contract.ShippingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping")
@Tag(name = "Authentication", description = "")
public class ShippingController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(ShippingController.class);

    ShippingController() {
    }

    @PostMapping(path = "/create")
    @Operation(summary = "")
    public ShippingResponse create(@Valid @RequestBody ShippingRequest shippingRequest,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {
        return null;
    }


    @PostMapping(path = "/dispatch")
    @Operation(summary = "")
    public ShippingResponse dispatch(@Valid @RequestBody ShippingRequest shippingRequest,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/confirm")
    @Operation(summary = "")
    public ShippingResponse confirm(@Valid @RequestBody ShippingRequest shippingRequest,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
        return null;
    }

    @GetMapping(path = "/orders")
    @Operation(summary = "")
    public ShippingResponse orders(@Valid @RequestBody ShippingRequest shippingRequest,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/{id}")
    @Operation(summary = "")
    public ShippingResponse start(@Valid @RequestBody ShippingRequest shippingRequest,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) {
        return null;
    }

    @PostMapping(path = "/history")
    @Operation(summary = "")
    public ShippingResponse history(@Valid @RequestBody ShippingRequest shippingRequest,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
        return null;
    }
}
