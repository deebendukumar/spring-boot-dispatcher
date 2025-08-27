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

import com.dispatcher.pim.entity.Brand;
import com.dispatcher.pim.repository.BrandRepository;
import com.dispatcher.pim.service.BrandService;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;

import java.util.List;

/**
 * A TxService is a stereotype annotation to define a transactional Spring managed service.
 * A Measured is a marker annotation on classes or public methods to indicate that the annotated class or method are being tracked in terms
 */
@TxService
public class BrandServiceImpl implements BrandService<Brand> {

    private final BrandRepository repository;
    private final Translator translator;

    BrandServiceImpl(BrandRepository repository, Translator translator) {
        this.repository = repository;
        this.translator = translator;
    }

    @Override
    public Brand findByPKey(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Brand> findAll() {
        return repository.findAll();
    }

    @Override
    public Brand create(Brand brand) {
        return repository.save(brand);
    }

    @Override
    public Brand update(String id, Brand brand) {
        return repository.findById(id)
                .map(existingBrand -> {
                    brand.setId(id);
                    return repository.save(brand);
                })
                .orElse(null);
    }

    @Override
    public Brand delete(String id) {
        return repository.findById(id)
                .map(existingBrand -> {
                    repository.delete(existingBrand);
                    return existingBrand;
                })
                .orElse(null);
    }
}
