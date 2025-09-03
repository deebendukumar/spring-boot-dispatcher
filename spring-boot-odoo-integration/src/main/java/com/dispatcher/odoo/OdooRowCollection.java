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

package com.dispatcher.odoo;

import com.dispatcher.common.base.Auditable;
import com.dispatcher.odoo.exception.OdooApiException;

import java.util.ArrayList;
import java.util.HashMap;

/***
 * Row collection for OpenERP row data
 */
public class OdooRowCollection extends ArrayList<OdooRow> implements Auditable {

    private static final long serialVersionUID = -168965138153400087L;

    public OdooRowCollection() {
    }

    @SuppressWarnings("unchecked")
    public OdooRowCollection(Object[] openERPResultSet, OdooFieldCollection fields) throws OdooApiException {
        for (int i = 0; i < openERPResultSet.length; i++) {
            OdooRow row = new OdooRow((HashMap<String, Object>) openERPResultSet[i], fields);
            this.add(row);
        }
    }

    @Override
    public void add(int index, OdooRow element) {
        super.add(index, element);
    }

    @Override
    public boolean add(OdooRow e) {
        return super.add(e);
    }
}
