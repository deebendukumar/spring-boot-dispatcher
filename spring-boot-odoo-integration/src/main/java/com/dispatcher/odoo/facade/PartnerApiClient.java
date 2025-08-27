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
import java.util.List;
import java.util.Optional;

public class PartnerApiClient extends OdooApiClient {

    private static final Logger logger = LoggerFactory.getLogger(PartnerApiClient.class);

    private static final String _MODEL = "res.partner";

    public PartnerApiClient(Session session) throws XmlRpcException, OdooApiException {
        super(session, _MODEL);
    }

    public List<Row> find(Optional<String> name, Optional<String> phone, Optional<String> mobile) throws OdooApiException, XmlRpcException {
        List<Row> result = new ArrayList<>();
        FilterCollection filters = new FilterCollection();
        if (name.isPresent()) {
            filters.add("name", "=", name.get());
        } else if (phone.isPresent()) {
            filters.add("phone_sanitized", "=", phone.get());
        } else if (mobile.isPresent()) {
            filters.add("mobile", "=", mobile.get());
        }
        result = find(filters);
        return result;
    }

//    @Override
//    public Partner create(Partner param) {
//        Partner result = null;
//        try {
//            ObjectAdapter adapter = getObjectAdapter();
//            Row partner = getObjectAdapter().getNewRow(new String[]{"phone",
//                    "email",
//                    "mobile",
//                    "website",
//                    "name",
//                    "vat",
//                    "company_name",
//                    "is_company",
//                    "street",
//                    "street2",
//                    "city",
//                    "zip",
//                    "state_id",
//                    "country_id",
//                    "lang",
//                    "currency_id"});
//            partner.put("phone", param.getPhone());
//            partner.put("email", param.getEmail());
//            partner.put("mobile", param.getMobile());
//            partner.put("website", param.getWebsite());
//            partner.put("name", param.getName());
//            partner.put("vat", param.getVat());
//            partner.put("company_name", param.getCompanyName());
//            partner.put("is_company", param.getIsCompany());
//            partner.put("street", param.getStreet());
//            partner.put("street2", param.getStreet2());
//            partner.put("city", param.getCity());
//            partner.put("zip", param.getZip());
//            partner.put("state_id", param.getStateId());
//            partner.put("country_id", param.getCountryId());
//            partner.put("lang", param.getLang());
//            partner.put("currency_id", param.getCurrencyId());
//            adapter.createObject(partner);
//        } catch (Exception e) {
//            throw DispatcherRuntimeException.newBuilder()
//                    .withMessage(e.getMessage())
//                    .withCause(e)
//                    .build();
//        }
//        return result;
//    }
//
//    @Override
//    public Partner update(Integer id, Partner param) {
//        Partner result = null;
//        try {
//            ObjectAdapter adapter = getObjectAdapter();
//            Row partner = adapter.getNewRow(new String[]{
//                    "id",
//                    "phone",
//                    "email",
//                    "mobile",
//                    "website",
//                    "name",
//                    "vat",
//                    "company_name",
//                    "is_company",
//                    "street",
//                    "street2",
//                    "city",
//                    "zip",
//                    "state_id",
//                    "country_id",
//                    "lang",
//                    "currency_id"});
//            partner.put("id", param.getId());
//            partner.put("phone", param.getPhone());
//            partner.put("email", param.getEmail());
//            partner.put("mobile", param.getMobile());
//            partner.put("website", param.getWebsite());
//            partner.put("name", param.getName());
//            partner.put("vat", param.getVat());
//            partner.put("company_name", param.getCompanyName());
//            partner.put("is_company", param.getIsCompany());
//            partner.put("street", param.getStreet());
//            partner.put("street2", param.getStreet2());
//            partner.put("city", param.getCity());
//            partner.put("zip", param.getZip());
//            partner.put("state_id", param.getStateId());
//            partner.put("country_id", param.getCountryId());
//            partner.put("lang", param.getLang());
//            partner.put("currency_id", param.getCurrencyId());
//            adapter.writeObject(partner, true);
//        } catch (Exception e) {
//            throw DispatcherRuntimeException.newBuilder()
//                    .withMessage(e.getMessage())
//                    .withCause(e)
//                    .build();
//        }
//        return result;
//    }
//
//    @Override
//    public void delete(Integer id) {
//        try {
//            ObjectAdapter adapter = session.getObjectAdapter(_MODEL);
//            FilterCollection filters = new FilterCollection();
//            filters.add("id", "=", id);
//            RowCollection list = adapter.searchAndReadObject(filters, new String[]{});
//            adapter.unlinkObject(list);
//        } catch (Exception e) {
//            throw DispatcherRuntimeException.newBuilder()
//                    .withMessage(e.getMessage())
//                    .withCause(e)
//                    .build();
//        }
//    }
}
