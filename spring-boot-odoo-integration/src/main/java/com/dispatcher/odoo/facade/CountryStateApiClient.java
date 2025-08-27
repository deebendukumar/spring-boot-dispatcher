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

package com.dispatcher.odoo.facade;

import com.dispatcher.odoo.*;
import com.dispatcher.odoo.exception.OdooApiException;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountryStateApiClient extends OdooApiClient {

    private static final Logger logger = LoggerFactory.getLogger(CountryStateApiClient.class);

    private static final String _MODEL = "res.country.state";

    public CountryStateApiClient(Session session) throws XmlRpcException, OdooApiException {
        super(session, _MODEL);
    }
}
