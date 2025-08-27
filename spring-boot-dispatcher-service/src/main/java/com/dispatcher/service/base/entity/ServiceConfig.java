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

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
@Document(collection = "service_config")
public class ServiceConfig extends BaseEntity {

    @Field(name = "version")
    @JsonProperty(value = "version")
    private Integer version;

    @Field(name = "client_id")
    @JsonProperty(value = "client_id")
    private String clientId;

    @Field(name = "useforgoodsin")
    @JsonProperty(value = "useforgoodsin")
    private Boolean useforgoodsin;

    @Field(name = "useforgoodsout")
    @JsonProperty(value = "useforgoodsout")
    private Boolean useforgoodsout;

    @Field(name = "useforpicking")
    @JsonProperty(value = "useforpicking")
    private Boolean useforpicking;

    @Field(name = "useforreplenish")
    @JsonProperty(value = "useforreplenish")
    private Boolean useforreplenish;

    @Field(name = "useforstorage")
    @JsonProperty(value = "useforstorage")
    private Boolean useforstorage;

    @Field(name = "usefortransfer")
    @JsonProperty(value = "usefortransfer")
    private Boolean usefortransfer;

    public static ServiceConfig valueOf() {
        return builder()
                .build();
    }
}
