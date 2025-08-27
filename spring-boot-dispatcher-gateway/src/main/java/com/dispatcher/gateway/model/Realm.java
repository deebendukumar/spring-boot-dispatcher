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

import com.dispatcher.common.base.BaseEntity;

import static org.apache.commons.lang3.Validate.notBlank;

public class Realm extends BaseEntity {

    private String value;

    private Realm(String value) {
        this.value = value;
    }

    protected Realm() {
        super();
        // for framework
    }

    public static Realm fromValue(String realmName) {
        notBlank(realmName, "realmName may not be blank");
        return new Realm(realmName);
    }
}
