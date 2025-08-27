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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

public abstract class OdooApiClient {

    private final Session session;
    private final String resource;
    private final ObjectAdapter adapter;

    public OdooApiClient(Session session, String resource) throws XmlRpcException, OdooApiException {
        this.session = session;
        this.resource = resource;
        adapter = this.session.getObjectAdapter(resource);
    }

    public Row findByPKey(Integer id) throws OdooApiException, XmlRpcException {
        Row result = null;
        FilterCollection filters = new FilterCollection();
        filters.add("id", "=", id);
        List<Row> list = find(filters);
        if (list != null && !list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }

    public List<Row> find(FilterCollection filters) throws XmlRpcException, OdooApiException {
        List<Row> result = new ArrayList<>();
        RowCollection list = adapter.searchAndReadObject(filters, new String[]{});
        for (Row row : list) {
            result.add(row);
        }
        return result;
    }

    public List<Row> find(FilterCollection filters, List<String> fields) throws XmlRpcException, OdooApiException {
        List<Row> result = new ArrayList<>();
        RowCollection list = adapter.searchAndReadObject(filters, fields.toArray(new String[0]));
        for (Row row : list) {
            result.add(row);
        }
        return result;
    }

    public void create(Row row) throws XmlRpcException, OdooApiException {
        adapter.createObject(row);
    }

    public boolean update(Row row) throws XmlRpcException, OdooApiException {
        return adapter.writeObject(row, true);
    }

    public void delete(Integer id) throws OdooApiException, XmlRpcException {
        FilterCollection filters = new FilterCollection();
        filters.add("id", "=", id);
        RowCollection list = adapter.searchAndReadObject(filters, new String[]{});
        adapter.unlinkObject(list);
    }
}
