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

import com.dispatcher.odoo.FilterCollection;
import com.dispatcher.odoo.Row;
import com.dispatcher.odoo.Session;
import com.dispatcher.odoo.exception.OdooApiException;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductApiClient extends OdooApiClient {

    private static final Logger logger = LoggerFactory.getLogger(ProductApiClient.class);

    private static final String _MODEL = "product.product";

    /**
     * @param session
     * @throws XmlRpcException
     * @throws OdooApiException
     */
    public ProductApiClient(Session session) throws XmlRpcException, OdooApiException {
        super(session, _MODEL);
    }

    /**
     * @param code
     * @return
     * @throws OdooApiException
     * @throws XmlRpcException
     */
    public List<Row> findByCode(String code) throws OdooApiException, XmlRpcException {
        List<Row> result = new ArrayList<>();
        FilterCollection filters = new FilterCollection();
        filters.add("code", "=", code);
        result = find(filters);
        return result;
    }
}
