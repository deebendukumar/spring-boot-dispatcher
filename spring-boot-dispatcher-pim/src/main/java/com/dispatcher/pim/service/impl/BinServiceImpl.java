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

import com.dispatcher.pim.entity.Bin;
import com.dispatcher.pim.repository.BinRepository;
import com.dispatcher.pim.service.BinService;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;

import java.util.List;

/**
 * A TxService is a stereotype annotation to define a transactional Spring managed service.
 * A Measured is a marker annotation on classes or public methods to indicate that the annotated class or method are being tracked in terms
 */
@TxService
public class BinServiceImpl implements BinService<Bin> {

    private final BinRepository repository;
    private final Translator translator;

    BinServiceImpl(BinRepository repository, Translator translator) {
        this.repository = repository;
        this.translator = translator;
    }

    @Override
    public Bin findByPKey(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Bin> findAll() {
        return repository.findAll();
    }

    @Override
    public Bin create(Bin bin) {
        return repository.save(bin);
    }

    @Override
    public Bin update(String id, Bin bin) {
        Bin existingBin = findByPKey(id);
        if (existingBin != null) {
            bin.setId(id);
            return repository.save(bin);
        }
        return null;
    }

    @Override
    public Bin delete(String id) {
        Bin existingBin = findByPKey(id);
        if (existingBin != null) {
            repository.delete(existingBin);
            return existingBin;
        }
        return null;
    }
}
