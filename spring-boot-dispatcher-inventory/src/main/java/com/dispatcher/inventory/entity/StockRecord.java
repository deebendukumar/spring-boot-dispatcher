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

package com.dispatcher.inventory.entity;

import com.dispatcher.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
@Document(collection = "stock_record")
public class StockRecord extends BaseEntity {

    @Field(name = "variant_id")
    @JsonProperty(value = "variant_id")
    private String variantId;

    @Field(name = "activity_code")
    @JsonProperty(value = "activity_code")
    private String activityCode;

    @Field(name = "warehouse_id")
    @JsonProperty(value = "warehouse_id")
    private String warehouseId;

    @Field(name = "zone_id")
    @JsonProperty(value = "zone_id")
    private String zoneId;

    @Field(name = "rack_id")
    @JsonProperty(value = "rack_id")
    private String rackId;

    @Field(name = "bin_id")
    @JsonProperty(value = "bin_id")
    private String binId;

    @Field(name = "batch_number")
    @JsonProperty(value = "batch_number")
    private String batchNumber;

    @Field(name = "serial_number")
    @JsonProperty(value = "serial_number")
    private String serialNumber;

    @Field(name = "date_manufactured")
    @JsonProperty(value = "date_manufactured")
    private String dateManufactured;

    @Field(name = "date_expiry")
    @JsonProperty(value = "date_expiry")
    private String dateExpiry;

    @Field(name = "best_before")
    @JsonProperty(value = "best_before")
    private String bestBefore;

    @Field(name = "expected_quantity")
    @JsonProperty(value = "expected_quantity")
    private BigDecimal expected_quantity;

    public static StockRecord valueOf() {
        return builder()
                .build();
    }

}
