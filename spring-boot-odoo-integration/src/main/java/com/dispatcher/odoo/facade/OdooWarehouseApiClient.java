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

import com.dispatcher.odoo.OdooFilterCollection;
import com.dispatcher.odoo.OdooRow;
import com.dispatcher.odoo.OdooSession;
import com.dispatcher.odoo.exception.OdooApiException;
import org.apache.xmlrpc.XmlRpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OdooWarehouseApiClient extends OdooApiClient {

    private static final Logger logger = LoggerFactory.getLogger(OdooWarehouseApiClient.class);

    private static final String _MODEL = "stock.warehouse";

    public OdooWarehouseApiClient(OdooSession session) throws XmlRpcException, OdooApiException {
        super(session, _MODEL);
    }

    public List<OdooRow> findByCode(String code) throws OdooApiException, XmlRpcException {
        List<OdooRow> result = new ArrayList<>();
        OdooFilterCollection filters = new OdooFilterCollection();
        filters.add("code", "=", code);
        result = find(filters);
        return result;
    }

//    private Warehouse parse(Row row) {
//        String json = row.toJson();
//        logger.info(json);
//        OdooWarehouseObject odooWarehouseObject = toOdooWarehouseObject(json);
//        Warehouse warehouse = convert(odooWarehouseObject);
//        return warehouse;
//    }

//    private Warehouse convert(OdooWarehouseObject source) {
//        return WarehouseJsonMapper.INSTANCE.toWarehouse(source.getFieldsOdoo());
//    }
//
//    private OdooWarehouseObject toOdooWarehouseObject(String json) {
//        OdooWarehouseObject result = null;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            result = mapper.readValue(json, OdooWarehouseObject.class);
//        } catch (JsonProcessingException ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }
}
