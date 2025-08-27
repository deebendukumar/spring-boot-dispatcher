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

import com.dispatcher.gateway.document.Plan;
import com.dispatcher.gateway.repository.PlanRepository;
import com.dispatcher.gateway.service.PlanService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {

    private PlanRepository repository;

    public PlanServiceImpl(PlanRepository repository) {
        this.repository = repository;
    }

    @Override
    public Plan findById(Long id) {
        Optional<Plan> optional = this.repository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public List<Plan> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Plan create(Plan plan) {
        plan.setId(String.valueOf(plan.generateRandomNumber()));
        if (repository.existsByName(plan.getName()))
            throw new DuplicateKeyException("name already exist");
        return repository.save(plan);
    }

    @Override
    public Boolean delete(Long id) {
        Boolean status = false;
        if (repository.existsById(id)) {
            repository.delete(repository.findById(id).get());
            status = true;
        }
        return status;
    }
}
