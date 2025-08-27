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

package com.dispatcher.preadvice.entity;

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
@Document(collection = "pre_advice")
public class PreAdviceLine extends BaseEntity {

    @Field(name = "order_number")
    @JsonProperty(value = "order_number")
    private String orderNumber;

    @Field(name = "line_number")
    @JsonProperty(value = "line_number")
    private String lineNumber;

    @Field(name = "variant_id")
    @JsonProperty(value = "variant_id")
    private String variantId;

    @Field(name = "batch_number")
    @JsonProperty(value = "batch_number")
    private String batchNumber;

    @Field(name = "amount")
    @JsonProperty(value = "amount")
    private BigDecimal amount;  //The advised amount

    @Field(name = "confirmed_amount")
    @JsonProperty(value = "confirmed_amount")
    private BigDecimal confirmedAmount;  //The advised amount

    public static PreAdviceLine valueOf() {
        return builder()
                .build();
    }
}
