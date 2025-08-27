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

package com.dispatcher.service.base.entity;

import com.dispatcher.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
@Document(collection = "sequence_number")
public class SequenceNumber extends BaseEntity {

    @Field(name = "classname")
    @JsonProperty(value = "classname")
    private String classname;

    @Field(name = "sequence_number")
    @JsonProperty(value = "sequence_number")
    private BigInteger sequenceNumber;

    @Field(name = "version")
    @JsonProperty(value = "version")
    private Integer version;

    public static SequenceNumber valueOf() {
        return builder()
                .build();
    }
}
