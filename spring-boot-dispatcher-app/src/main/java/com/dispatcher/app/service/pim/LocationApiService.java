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

package com.dispatcher.app.service.pim;

import com.dispatcher.common.base.AbstractWebService;
import com.dispatcher.adapters.odoo.service.LocationServiceInterface;
import com.dispatcher.adapters.odoo.service.ServiceAdapter;
import org.ameba.annotation.TxService;
import org.ameba.i18n.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A TxService is a stereotype annotation to define a transactional Spring
 * managed service.
 * A Measured is a marker annotation on classes or public methods to indicate
 * that the annotated class or method are being tracked in terms
 */
@TxService
public class LocationApiService extends AbstractWebService {

    private static final Logger logger = LoggerFactory.getLogger(LocationApiService.class);

    private final Translator translator;
    private final Environment environment;
    private final ApplicationContext applicationContext;

    LocationApiService(ApplicationContext applicationContext,
                       Environment environment,
                       Translator translator) {
        this.applicationContext = applicationContext;
        this.environment = environment;
        this.translator = translator;
    }

    public List<HashMap<String, Object>> test() {
        return new ArrayList<>();
    }
}
