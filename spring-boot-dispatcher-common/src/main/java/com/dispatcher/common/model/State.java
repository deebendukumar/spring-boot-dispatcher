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

package com.dispatcher.common.model;

import com.dispatcher.common.base.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class State implements Auditable {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "display_name")
    private String displayName;

    @JsonProperty(value = "country_id")
    private Integer countryId;

//    public static State valueOf(Row row) {
//        return builder()
//                .id(row.getID())
//                .code(String.valueOf(row.getFieldsOdoo().get("code")))
//                .name(String.valueOf(row.getFieldsOdoo().get("name")))
//                .displayName(String.valueOf(row.getFieldsOdoo().get("display_name")))
//                .countryId((Integer) (row.getFieldsOdoo().get("country_id")))
//                .build();
//    }
}
