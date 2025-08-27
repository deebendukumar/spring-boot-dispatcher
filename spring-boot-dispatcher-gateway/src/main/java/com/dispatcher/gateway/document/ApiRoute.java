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

package com.dispatcher.gateway.document;

import com.dispatcher.gateway.model.Endpoint;
import com.dispatcher.gateway.model.Filter;
import com.dispatcher.gateway.model.Predicate;
import com.dispatcher.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@formatter:off
/**
 * https://localhost:9999/restfulservice/v1/users/{id}
 * ------ protocol
 *         --------- host / domain name
 *                   ---- port number
 *                        -------------- application context
 *                                       -- apiVersion
 *                                          ----- resource
 *                                                -- parameter
 */
//@formatter:on
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "routes")
public class ApiRoute extends BaseEntity {

    @Id
    private String id;
    private String name;
    private String description;

    @JsonProperty(value = "context")
    private String context;                     //context path should be unique per api

    @JsonProperty(value = "version")
    private String version;                     //apiVersion of api

    @JsonProperty(value = "endpoint")
    private Endpoint endpoint;

    private Predicate predicate;                //holds endpoint related details
    private Filter filter;                      //

    private Integer orders = 0;

    private Plan plan;

    public void setName(String name) {
        this.name = name.toUpperCase().replaceAll(" ", "-");
    }
}
