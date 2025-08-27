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

import com.dispatcher.pim.entity.Warehouse;
import com.dispatcher.pim.entity.Zone;
import com.dispatcher.pim.repository.WarehouseRepository;
import com.dispatcher.pim.repository.ZoneRepository;
import com.dispatcher.pim.service.WarehouseService;
import com.dispatcher.pim.service.ZoneService;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A TxService is a stereotype annotation to define a transactional Spring managed service.
 * A Measured is a marker annotation on classes or public methods to indicate that the annotated class or method are being tracked in terms
 */
@TxService
public class ZoneServiceImpl implements ZoneService<Zone> {

    private final ZoneRepository repository;
    private final Translator translator;

    ZoneServiceImpl(ZoneRepository repository, Translator translator) {
        this.repository = repository;
        this.translator = translator;
    }

    @Override
    public Zone findByPKey(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(translator.translate("zone.not.found", id)));
    }

    @Override
    public List<Zone> findAll() {
        return repository.findAll();
    }

    @Override
    public Zone create(Zone zone) {
        return repository.save(zone);
    }

    @Override
    public Zone update(String id, Zone zone) {
        return repository.findById(id)
                .map(existingZone -> {
                    zone.setId(id);
                    return repository.save(zone);
                })
                .orElseThrow(() -> new IllegalArgumentException(translator.translate("zone.not.found", id)));
    }

    @Override
    public Zone delete(String id) {
        return repository.findById(id)
                .map(zone -> {
                    repository.delete(zone);
                    return zone;
                })
                .orElseThrow(() -> new NoSuchElementException("Zone with id " + id + " not found"));
    }

}
