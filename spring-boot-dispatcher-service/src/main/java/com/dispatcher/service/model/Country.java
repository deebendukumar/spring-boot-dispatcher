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

package com.dispatcher.service.model;

import com.dispatcher.common.base.Auditable;
import com.dispatcher.odoo.Row;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Country implements Auditable {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "display_name")
    private String displayName;

    @JsonProperty(value = "currency_id")
    private Integer currencyId;

    @JsonProperty(value = "currency")
    private Currency currency;

    @JsonProperty(value = "phone_code")
    private Integer phoneCode;

    @JsonProperty(value = "state_ids")
    private List<Integer> stateIds;

    public static Country valueOf(Row row) {
        return builder()
                .id(row.getID())
                .code(String.valueOf(row.getFieldsOdoo().get("code")))
                .name(String.valueOf(row.getFieldsOdoo().get("name")))
                .displayName(String.valueOf(row.getFieldsOdoo().get("display_name")))
                .currencyId((Integer) (row.getFieldsOdoo().get("currency_id")))
                .phoneCode((Integer) (row.getFieldsOdoo().get("phone_code")))
                .stateIds((List<Integer>) (row.getFieldsOdoo().get("state_ids")))
                .build();
    }
}
