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

package com.dispatcher.pim.service.impl;

import com.dispatcher.pim.entity.Rack;
import com.dispatcher.pim.entity.Variant;
import com.dispatcher.pim.repository.RackRepository;
import com.dispatcher.pim.repository.VariantRepository;
import com.dispatcher.pim.service.RackService;
import com.dispatcher.pim.service.VariantService;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A TxService is a stereotype annotation to define a transactional Spring managed service.
 * A Measured is a marker annotation on classes or public methods to indicate that the annotated class or method are being tracked in terms
 */
@TxService
public class RackServiceImpl implements RackService<Rack> {

    private final RackRepository repository;
    private final Translator translator;

    RackServiceImpl(RackRepository repository, Translator translator) {
        this.repository = repository;
        this.translator = translator;
    }

    @Override
    public Rack findByPKey(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(translator.translate("rack.not.found", id)));
    }

    @Override
    public List<Rack> findAll() {
        return repository.findAll();
    }

    @Override
    public Rack create(Rack rack) {
        return repository.save(rack);
    }

    @Override
    public Rack update(String id, Rack rack) {
        return repository.findById(id)
                .map(existingRack -> {
                    rack.setId(id);
                    return repository.save(rack);
                })
                .orElseThrow(() -> new IllegalArgumentException(translator.translate("rack.not.found", id)));
    }

    @Override
    public Rack delete(String id) {
        return repository.findById(id)
                .map(rack -> {
                    repository.delete(rack);
                    return rack;
                })
                .orElseThrow(() -> new NoSuchElementException("Rack with id " + id + " not found"));
    }

}
