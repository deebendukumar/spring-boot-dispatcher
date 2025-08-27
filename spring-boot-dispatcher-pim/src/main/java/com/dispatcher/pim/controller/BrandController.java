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
import com.dispatcher.pim.entity.Brand;
import com.dispatcher.pim.service.BrandService;
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
@RequestMapping("/brands")
@Tag(name = "Brands", description = "")
public class BrandController extends AbstractWebController {

    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandService<Brand> service;

    BrandController(BrandService<Brand> service) {
        this.service = service;
    }

    @PostMapping(path = "")
    @Operation(summary = "")
    public Brand create(@RequestBody Brand brand,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        return service.create(brand);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "")
    public Brand find(@PathVariable String id,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        return service.findByPKey(id);
    }

    @GetMapping(path = "")
    @Operation(summary = "")
    public List<Brand> findBy(@RequestParam("phone") Optional<String> phone,
                            @RequestParam("name") Optional<String> name,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        return service.findAll();
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "")
    public Brand update(@PathVariable String id,
                          @RequestBody Brand brand,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        return service.update(id, brand);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "")
    public Brand delete(@PathVariable String id,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        return service.delete(id);
    }
}
