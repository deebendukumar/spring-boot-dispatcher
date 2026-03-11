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

import java.io.Serializable;

public class DispatcherRuntimeException extends RuntimeException {

    private String messageKey;
    private Serializable[] data;

    /**
     * Default constructor.
     */
    public DispatcherRuntimeException() {
        super();
    }

    /**
     * Constructor with mesaage.
     *
     * @param message The message
     */
    public DispatcherRuntimeException(String message) {
        super(message);
    }

    /**
     * Create a TechnicalRuntimeException with message and root cause.
     *
     * @param message The message text
     * @param cause   The root cause
     */
    public DispatcherRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a TechnicalRuntimeException with message and message key.
     *
     * @param message    The message text
     * @param messageKey The message key
     */
    public DispatcherRuntimeException(String message, String messageKey) {
        super(message);
        this.messageKey = messageKey;
    }

    /**
     * Constructs with a message key and a message
     *
     * @param message    Message text
     * @param messageKey Message key
     * @param data       Additional implicit data passed to the caller
     */
    public DispatcherRuntimeException(String message, String messageKey, Serializable[] data) {
        super(message);
        this.messageKey = messageKey;
        this.data = data;
    }

    /**
     * Constructs with a message key and a message
     *
     * @param messageKey Message key
     * @param data       Additional implicit data passed to the caller
     */
    public DispatcherRuntimeException(String messageKey, Serializable[] data) {
        super();
        this.messageKey = messageKey;
        this.data = data;
    }

    /**
     * Create a TechnicalRuntimeException with message, message key and root cause.
     *
     * @param message    The message text
     * @param messageKey The message key
     * @param cause      The root cause
     */
    public DispatcherRuntimeException(String message, String messageKey, Throwable cause) {
        super(message, cause);
        this.messageKey = messageKey;
    }

    private DispatcherRuntimeException(Builder builder) {
        super(builder.message, builder.cause);
        messageKey = builder.messageKey;
        data = builder.data;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * Get the message key.
     *
     * @return The message key
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Get the data, applied to the exception.
     *
     * @return The applied data, the caller should know about how to use this information
     */
    public Serializable[] getData() {
        return data;
    }

    public static final class Builder {
        private String message;
        private String messageKey;
        private Throwable cause;
        private Serializable[] data;

        private Builder() {
        }

        public Builder withMessage(String val) {
            message = val;
            return this;
        }

        public Builder withMessageKey(String val) {
            messageKey = val;
            return this;
        }

        public Builder withCause(Throwable val) {
            cause = val;
            return this;
        }

        public Builder withData(Serializable[] val) {
            data = val;
            return this;
        }

        public DispatcherRuntimeException build() {
            return new DispatcherRuntimeException(this);
        }
    }
}
