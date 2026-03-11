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

package com.dispatcher.common.exception;

import org.ameba.i18n.Translator;

import java.io.Serializable;

public class BadRequestException extends DispatcherRuntimeException {

    /**
     * Create a new DataNotFoundException with a message text and the root
     * exception.
     *
     * @param message Message text as String
     * @param cause   The root exception
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new DataNotFoundException with a message text.
     *
     * @param message Message text as String
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Create a new DataNotFoundException with the id of the expected entity.
     *
     * @param id Id of the expected entity
     */
    public BadRequestException(Serializable id) {
        super(String.format("Entity class not found in persistence layer, id=[%s]", id));
    }

    public BadRequestException(Translator translator, String messageKey, Object... param) {
        super(translator.translate(messageKey, param), messageKey);
    }
}
