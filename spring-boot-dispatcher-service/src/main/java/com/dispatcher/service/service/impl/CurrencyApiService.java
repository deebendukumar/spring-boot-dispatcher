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

package com.dispatcher.service.service.impl;

import com.dispatcher.odoo.Session;
import com.dispatcher.odoo.exception.OdooApiException;
import com.dispatcher.odoo.facade.CurrencyApiClient;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 * A TxService is a stereotype annotation to define a transactional Spring managed service.
 * A Measured is a marker annotation on classes or public methods to indicate that the annotated class or method are being tracked in terms
 */
@TxService
public class CurrencyApiService extends OdooApiService {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyApiService.class);

    private final String _MODEL = "res.currency";

    private final Translator translator;
    private final Environment environment;

    CurrencyApiService(Environment environment, Translator translator, Session session) {
        this.environment = environment;
        this.translator = translator;
        facade = createCurrencyFacade(session);
    }

    public static CurrencyApiClient createCurrencyFacade(Session session) {
        CurrencyApiClient facade = null;
        try {
            facade = new CurrencyApiClient(session);
        } catch (XmlRpcException e) {
            logger.error("error creating currency facade {}", e.getMessage());
        } catch (OdooApiException e) {
            logger.error("error creating currency facade {}", e.getMessage());
        }
        return facade;
    }
}
