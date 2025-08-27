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
import com.dispatcher.odoo.exception.OdooApiException;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A TxService is a stereotype annotation to define a transactional Spring
 * managed service.
 * A Measured is a marker annotation on classes or public methods to indicate
 * that the annotated class or method are being tracked in terms
 */
public class OdooAbstractApiService {

    private static final Logger logger = LoggerFactory.getLogger(OdooAbstractApiService.class);

    protected OdooApiClient facade;

    public HashMap<String, Object> findByPKey(Integer id) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            Row row = this.facade.findByPKey(id);
            result = row.getFieldsOdoo();
        } catch (OdooApiException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<HashMap<String, Object>> findAll() {
        List<HashMap<String, Object>> result = new ArrayList<>();
        try {
            List<Row> list = this.facade.find(new FilterCollection());
            for (Row row : list) {
                result.add(row.getFieldsOdoo());
                logger.info(row.toJson());
            }
        } catch (OdooApiException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return result;
    }

}
