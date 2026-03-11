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

package com.dispatcher.partners.service.impl;

import com.dispatcher.adapters.odoo.OdooSession;
import com.dispatcher.adapters.odoo.exception.OdooApiException;
import com.dispatcher.common.model.Partner;
import com.dispatcher.partners.service.PartnerService;
import com.dispatcher.common.exception.DataNotFoundException;
import com.dispatcher.adapters.odoo.facade.OdooPartnerApiClient;
import org.ameba.annotation.Measured;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.util.*;

/**
 * A TxService is a stereotype annotation to define a transactional Spring managed service.
 * A Measured is a marker annotation on classes or public methods to indicate that the annotated class or method are being tracked in terms
 */
@TxService
public class PartnerServiceImpl implements PartnerService<Partner> {

    private static final Logger logger = LoggerFactory.getLogger(PartnerServiceImpl.class);

    private final Translator translator;
    private final Environment environment;
    private final OdooSession session;
    private final OdooPartnerApiClient facade;

    PartnerServiceImpl(Environment environment, Translator translator, OdooSession session) {
        this.environment = environment;
        this.translator = translator;
        this.session = session;
        this.facade = createPartnerFacade(session);
    }

    public static OdooPartnerApiClient createPartnerFacade(OdooSession session) {
        OdooPartnerApiClient facade = null;
        try {
            facade = new OdooPartnerApiClient(session);
        } catch (XmlRpcException e) {
            logger.error("error creating partner facade {}", e.getMessage());
        } catch (OdooApiException e) {
            logger.error("error creating partner facade {}", e.getMessage());
        }
        return facade;
    }

    @Override
    public Partner findByPKey(String id) {
        return null;
    }

    @Override
    public List<Partner> findByPhone(String phone) {
        return List.of();
    }

    @Override
    public List<Partner> findByName(String name) {
        return List.of();
    }

    @Override
    public List<Partner> findAll() {
        return List.of();
    }

    @Override
    public Partner create(Partner partner) {
        return null;
    }

    @Override
    public Partner update(String id, Partner partner) {
        return null;
    }

    @Override
    public Partner delete(String id) {
        return null;
    }
}
