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

package com.dispatcher.service.model;

import com.dispatcher.common.base.Auditable;
import com.dispatcher.odoo.Row;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "id",
        "type",
        "phone",
        "email",
        "website",
        "employee",
        "parent_id",
        "name",
        "email_normalized",
        "display_name",
        "vat",
        "is_blacklisted",
        "active",
        "user_id",
        "additional_info",
        "company_name",
        "phone_mobile_search",
        "child_ids",
        "category_id",
        "company_id",
        "is_public",
        "is_company",
        "parent_name",
        "company_type",
        "phone_blacklisted",
        "mobile_blacklisted",
        "commercial_company_name",
        "translated_display_name",
        "create_date",
        "title",
        "street",
        "street2",
        "city",
        "zip",
        "state_id",
        "country_code",
        "country_id",
        "lang",
        "contact_address",
        "email_formatted",
        "mobile",
        "comment",
        "phone_sanitized",
        "currency_id"
})
@Data
@Builder
public class Partner implements Auditable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("website")
    private String website;
    @JsonProperty("employee")
    private Boolean employee;
    @JsonProperty("parent_id")
    private Boolean parentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email_normalized")
    private String emailNormalized;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("vat")
    private String vat;
    @JsonProperty("is_blacklisted")
    private Boolean isBlacklisted;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("user_id")
    private Boolean userId;
    @JsonProperty("additional_info")
    private Boolean additionalInfo;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("phone_mobile_search")
    private Boolean phoneMobileSearch;
    @JsonProperty("child_ids")
    private List<Integer> childIds;
    @JsonProperty("category_id")
    private List<Integer> categoryId;
    @JsonProperty("company_id")
    private Boolean companyId;
    @JsonProperty("is_public")
    private Boolean isPublic;
    @JsonProperty("is_company")
    private Boolean isCompany;
    @JsonProperty("parent_name")
    private Boolean parentName;
    @JsonProperty("company_type")
    private String companyType;
    @JsonProperty("phone_blacklisted")
    private Boolean phoneBlacklisted;
    @JsonProperty("mobile_blacklisted")
    private Boolean mobileBlacklisted;
    @JsonProperty("commercial_company_name")
    private String commercialCompanyName;
    @JsonProperty("translated_display_name")
    private String translatedDisplayName;
    @JsonProperty("create_date")
    private String createDate;
    @JsonProperty("title")
    private Boolean title;
    @JsonProperty("street")
    private String street;
    @JsonProperty("street2")
    private String street2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("state_id")
    private Integer stateId;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("country_id")
    private Integer countryId;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("contact_address")
    private String contactAddress;
    @JsonProperty("email_formatted")
    private String emailFormatted;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("comment")
    private Boolean comment;
    @JsonProperty("phone_sanitized")
    private String phoneSanitized;
    @JsonProperty("currency_id")
    private Integer currencyId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();

    public static Partner valueOf(Row row) {
        return builder()
                .id(row.getID())
                .type(String.valueOf(row.getFieldsOdoo().get("type")))
                .phone(String.valueOf(row.getFieldsOdoo().get("phone")))
                .mobile(String.valueOf(row.getFieldsOdoo().get("mobile")))
                .email(String.valueOf(row.getFieldsOdoo().get("email")))
                .website(String.valueOf(row.getFieldsOdoo().get("website")))
                .employee((Boolean) (row.getFieldsOdoo().get("employee")))
                .name(String.valueOf(row.getFieldsOdoo().get("name")))
                .displayName(String.valueOf(row.getFieldsOdoo().get("display_name")))
                .vat(String.valueOf(row.getFieldsOdoo().get("vat")))
                .isBlacklisted((Boolean) (row.getFieldsOdoo().get("is_blacklisted")))
                .active((Boolean) (row.getFieldsOdoo().get("active")))
                .companyName(String.valueOf(row.getFieldsOdoo().get("company_name")))
                .isCompany((Boolean) (row.getFieldsOdoo().get("is_company")))
                .street(String.valueOf(row.getFieldsOdoo().get("street")))
                .street2(String.valueOf(row.getFieldsOdoo().get("street2")))
                .city(String.valueOf(row.getFieldsOdoo().get("city")))
                .stateId((Integer) (row.getFieldsOdoo().get("state_id")))
                .countryId((Integer) (row.getFieldsOdoo().get("country_id")))
                .zip(String.valueOf(row.getFieldsOdoo().get("zip")))
                .lang(String.valueOf(row.getFieldsOdoo().get("lang")))
                .contactAddress(String.valueOf(row.getFieldsOdoo().get("contact_address")))
                .currencyId((Integer) (row.getFieldsOdoo().get("currency_id")))
                .build();
    }
}
