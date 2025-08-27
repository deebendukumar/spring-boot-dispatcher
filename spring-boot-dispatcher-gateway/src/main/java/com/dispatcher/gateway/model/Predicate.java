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

package com.dispatcher.gateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Predicate {

    private String method;
    private String cookie;

    @Field("after_route")
    @JsonProperty(value = "after_route")
    private String afterRoute;

    @Field("before_route")
    @JsonProperty(value = "before_route")
    private String beforeRoute;

    private String header;
    private String query;

    @Field("remote_address")
    @JsonProperty(value = "remote_address")
    private String remoteAddress;
}
