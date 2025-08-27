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

import com.dispatcher.pim.entity.Product;
import com.dispatcher.pim.repository.ProductRepository;
import com.dispatcher.pim.service.ProductService;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A TxService is a stereotype annotation to define a transactional Spring managed service.
 * A Measured is a marker annotation on classes or public methods to indicate that the annotated class or method are being tracked in terms
 */
@TxService
public class ProductServiceImpl implements ProductService<Product> {

    private final ProductRepository repository;
    private final Translator translator;

    ProductServiceImpl(ProductRepository repository, Translator translator) {
        this.repository = repository;
        this.translator = translator;
    }

    @Override
    public Product findByPKey(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(translator.translate("product.not.found", id)));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product create(Product product) {

        return repository.save(product);

    }

    @Override
    public Product update(String id, Product product) {
        return repository .findById(id)
                .map(existingProduct -> {
                    product.setId(id);
                    return repository.save(product);
                })
                .orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found"));
    }

    @Override
    public Product delete(String id) {
        return repository.findById(id)
                .map(product -> {
                    repository.delete(product);
                    return product;
                })
                .orElseThrow(() -> new NoSuchElementException("Product with id " + id + " not found"));
    }
}
