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
        "id",
        "name",
        "body",
        "body_html"
})
@Data
@Builder
public class Rack extends BaseEntity {

    @JsonProperty(value = "warehouse_id")
    private String warehouseId;

    @JsonProperty(value = "zone_id")
    private String zoneId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "active")
    private Boolean active;

    @JsonProperty(value = "client_id")
    private String clientId;

    public static Rack valueOf() {
        return builder()
                .build();
    }
}
