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

package com.dispatcher.inventory.model;

public class StockState {

	/**
	 * Stock is in incoming processes.<br>
	 * Not usable for ordinary outgoing operations.<br>
	 * Not synchronized with ERP.
	 */
	public static final int UNDEFINED = 0;

	/**
	 * Stock is in incoming processes.<br>
	 * Not usable for ordinary outgoing operations.<br>
	 * Not synchronized with ERP.
	 */
	public static final int INCOMING = 100;

	/**
	 * Normal store stock
	 */
	public static final int ON_STOCK = 300;

	/**
	 * Stock is picked for outgoing processing.<br>
	 */
	public static final int PICKED = 600;

	/**
	 * Stock is picked for outgoing processing.<br>
	 */
	public static final int PACKED = 650;

	/**
	 * Stock has left warehouse
	 */
	public static final int SHIPPED = 680;

	/**
	 * Can be removed
	 */
	public static final int DELETABLE = 1000;
}
