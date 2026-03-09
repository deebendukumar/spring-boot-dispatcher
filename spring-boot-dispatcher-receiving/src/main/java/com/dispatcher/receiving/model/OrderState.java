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

package com.dispatcher.receiving.model;

public class OrderState {

    /**
     * Order is not ready to be handled. Used within creation processes.
     */
    public static final int UNDEFINED = 0;

    /**
     * Order is ready created.
     */
    public static final int CREATED = 50;

    /**
     * Order is ready for automatic pre-processing
     */
    public static final int RELEASED = 100;

    /**
     * Processing is interrupted.
     */
    public static final int PAUSE = 200;

    /**
     * Can be processed
     */
    public static final int PROCESSABLE = 300;

    /**
     * Reserved for a user to process
     */
    public static final int RESERVED = 400;

    /**
     * Processing has been started.
     */
    public static final int STARTED = 500;

    /**
     * The order is not finished, but can not be continued at the moment.
     */
    public static final int PENDING = 550;

    /**
     * All picks are done.
     */
    public static final int PICKED = 600;

    /**
     * Package can be started
     */
    public static final int PACKING = 640;

    /**
     * All package is done
     */
    public static final int PACKED = 650;

    /**
     * Shipping can be started
     */
    public static final int SHIPPING = 670;

    /**
     * Shipping is done
     */
    public static final int SHIPPED = 680;

    /**
     * The order has been finished completely.
     */
    public static final int FINISHED = 700;

    /**
     * For some reasons the order failed.
     */
    public static final int FAILED = 710;

    /**
     * The order has been canceled
     */
    public static final int CANCELED = 800;

    /**
     * The operation is done and possible post processing actions have been done.
     */
    public static final int POSTPROCESSED = 900;

    /**
     * The order can be deleted.
     */
    public static final int DELETABLE = 1000;
}

