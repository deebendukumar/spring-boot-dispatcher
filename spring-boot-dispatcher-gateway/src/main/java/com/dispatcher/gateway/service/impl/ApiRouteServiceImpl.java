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

package com.dispatcher.gateway.service.impl;

import com.dispatcher.gateway.document.ApiRoute;
import com.dispatcher.gateway.repository.ApiRouteRepository;
import com.dispatcher.gateway.service.ApiRouteService;
import com.dispatcher.gateway.util.Passport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ApiRouteServiceImpl implements ApiRouteService {

    private static final String GATEWAY_ROUTES = "gateway_routes::";

    private ApiRouteRepository repository;

    public ApiRouteServiceImpl(ApiRouteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiRoute create(ApiRoute route) {
        ApiRoute result = null;
        if (!repository.existsByContext(route.getContext())) {
            route.setId(Passport.encrypt(route.getName()));
            result = repository.save(route);
        }
        return result;
    }

    @Override
    public List<ApiRoute> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean delete(String id) {
        Boolean result = false;
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public ApiRoute update(String id, ApiRoute route) {
        ApiRoute result = null;
        if (repository.findById(id).isPresent()) {
            result = repository.save(route);
        }
        return result;
    }

    @Override
    public ApiRoute get(String id) {
        ApiRoute result = null;
        Optional<ApiRoute> optional = repository.findById(id);
        if (optional.isPresent()) {
            result = optional.get();
        }
        return result;
    }

    @Override
    public List<ApiRoute> query() {
        List<ApiRoute> result = null;
        return result;
    }

    @Override
    @PostConstruct
    public Boolean overload() {
        Boolean result = false;
        log.info("The global initialization gateway route is successful!");
        return result;
    }
}
