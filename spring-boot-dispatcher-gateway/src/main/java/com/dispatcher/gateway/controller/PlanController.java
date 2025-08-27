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

import com.dispatcher.gateway.document.Plan;
import com.dispatcher.gateway.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/plans")
@Tag(name = "Plan", description = "Controller for managing plan-related operations")
public class PlanController {

    private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

    private PlanService service;

    public PlanController(PlanService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create plan", tags = "Plan")
    public Plan create(@RequestBody Plan param) {
        Plan plan = service.create(param);
        return plan;
    }

    @GetMapping(path = "")
    @Operation(summary = "Find all plan", tags = "Plan")
    public List<Plan> findAll(@RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        List<Plan> list = service.findAll();
        return list;
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Find plan with it's id", tags = "Plan")
    public Plan findById(@PathVariable Long id) {
        Plan plan = service.findById(id);
        return plan;
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete paln with it's id", tags = "Plan")
    public Boolean delete(@PathVariable Long id) {
        Boolean result = true;
        return result;
    }
}
