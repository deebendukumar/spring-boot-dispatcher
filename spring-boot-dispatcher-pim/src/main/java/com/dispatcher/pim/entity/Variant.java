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

package com.dispatcher.pim.entity;

import com.dispatcher.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
@Document(collection = "variants")
public class Variant extends BaseEntity {

    @Field(name = "product_id")
    @JsonProperty(value = "product_id")
    private String productId;

    @Field(name = "title")
    @JsonProperty(value = "title")
    private String title;

    @Field(name = "body")
    @JsonProperty(value = "body")
    private String body;

    @Field(name = "body_html")
    @JsonProperty(value = "body_html")
    private String bodyHtml;

    @Field(name = "short_description")
    @JsonProperty(value = "short_description")
    private String shortDescription;

    @Field(name = "sku")
    @JsonProperty(value = "sku")
    private String sku;

    @Field(name = "barcode")
    @JsonProperty(value = "barcode")
    private String barcode;

    @Field(name = "serial_no")
    @JsonProperty(value = "serial_no")
    private String serialNo;

    @Field(name = "active")
    @JsonProperty(value = "active")
    private Boolean active;

    @Field(name = "fragile")
    @JsonProperty(value = "fragile")
    private Boolean fragile;

    @JsonProperty(value = "hazmat")
    private Hazmat hazmat; //Whether a hazardous material or not

    @JsonProperty(value = "bin")
    private Bin bin;

    @Field(name = "unit")
    @JsonProperty(value = "unit")
    private String unit;

    @Field(name = "units_per_case")
    @JsonProperty(value = "units_per_case")
    private String unitsPerCase;

    @Field(name = "cases_per_pallet")
    @JsonProperty(value = "cases_per_pallet")
    private String casesPerPallet;

    @Field(name = "min_order_size")
    @JsonProperty(value = "min_order_size")
    private String minOrderSize;

    @Field(name = "user_def_1")
    @JsonProperty(value = "user_def_1")
    private String userDef1;

    public static Variant valueOf() {
        return builder()
                .build();
    }
}
