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

package com.dispatcher.common.model.product;

import com.dispatcher.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@JsonPropertyOrder({
        "id"
})
@Data
@Builder
public class Product extends BaseEntity {

    @JsonProperty(value = "client_id")
    private String clientId;

    @JsonProperty(value = "site_id")
    private String siteId;

    @JsonProperty(value = "brand")
    private Brand brand;

    @JsonProperty(value = "categories")
    private List<Category> categories;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "product_type")
    private String productType;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "body")
    private String body;

    @JsonProperty(value = "body_html")
    private String bodyHtml;

    @JsonProperty(value = "short_description")
    private String shortDescription;

    @JsonProperty(value = "tags")
    private List<String> tags;

    @JsonProperty(value = "has_attributes")
    private Boolean hadAttributes;

    public static Product valueOf() {
        return builder()
                .build();
    }
}
