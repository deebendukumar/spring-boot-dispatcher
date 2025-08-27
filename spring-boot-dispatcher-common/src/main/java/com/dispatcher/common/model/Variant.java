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

import com.dispatcher.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
public class Variant extends BaseEntity {

    @JsonProperty(value = "product_id")
    private String productId;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "body")
    private String body;

    @JsonProperty(value = "body_html")
    private String bodyHtml;

    @JsonProperty(value = "short_description")
    private String shortDescription;

    @JsonProperty(value = "sku")
    private String sku;

    @JsonProperty(value = "barcode")
    private String barcode;

    @JsonProperty(value = "serial_no")
    private String serialNo;

    @JsonProperty(value = "active")
    private Boolean active;

    @JsonProperty(value = "fragile")
    private Boolean fragile;

    @JsonProperty(value = "hazmat")
    private Hazmat hazmat; //Whether a hazardous material or not

    @JsonProperty(value = "bin")
    private Bin bin;

    @JsonProperty(value = "unit")
    private String unit;

    @JsonProperty(value = "units_per_case")
    private String unitsPerCase;

    @JsonProperty(value = "cases_per_pallet")
    private String casesPerPallet;

    @JsonProperty(value = "min_order_size")
    private String minOrderSize;

    @JsonProperty(value = "user_def_1")
    private String userDef1;

    public static Variant valueOf() {
        return builder()
                .build();
    }
}
