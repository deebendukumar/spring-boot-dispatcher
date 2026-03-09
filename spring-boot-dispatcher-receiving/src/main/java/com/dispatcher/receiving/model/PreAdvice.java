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

package com.dispatcher.receiving.model;

import com.dispatcher.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
@Document(collection = "pre_advice")
public class PreAdvice extends BaseEntity {

    @Field(name = "order_number")
    @JsonProperty(value = "order_number")
    private String orderNumber;

    @Field(name = "external_number")
    @JsonProperty(value = "external_number")
    private String externalNumber;

    @Field(name = "external_id")
    @JsonProperty(value = "external_id")
    private String externalId;

    @Field(name = "order_state")
    @JsonProperty(value = "order_state")
    private Integer orderState = OrderState.UNDEFINED;

    @Field(name = "started")
    @JsonProperty(value = "started")
    private Date started;

    @Field(name = "finished")
    @JsonProperty(value = "finished")
    private Date finished;

    @Field(name = "lines")
    @JsonProperty(value = "lines")
    private List<PreAdviceLine> lines;

    @Field(name = "sender_name")
    @JsonProperty(value = "sender_name")
    private String senderName;

    @Field(name = "carrier_name")
    @JsonProperty(value = "carrier_name")
    private String carrierName;

    @Field(name = "delivery_date")
    @JsonProperty(value = "delivery_date")
    private Date deliveryDate;

    public static PreAdvice valueOf() {
        return builder()
                .build();
    }
}
