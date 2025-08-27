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
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Filter {

    @Field("add_request_header")
    @JsonProperty(value = "add_request_header")
    private String addRequestHeader;

    @Field("add_request_parameter")
    @JsonProperty(value = "add_request_parameter")
    private String addRequestParameter;

    @Field("add_response_header")
    @JsonProperty(value = "add_response_header")
    private String addResponseHeader;

    @Field("circuit_breaker")
    @JsonProperty(value = "circuit_breaker")
    private CircuitBreaker circuitBreaker;
}
