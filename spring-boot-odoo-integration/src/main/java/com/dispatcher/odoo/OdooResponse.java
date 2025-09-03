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

package com.dispatcher.odoo;

public class OdooResponse {

	private final boolean isSuccessful;
	private final Exception errorCause;
	private final Object responseObject;
	private final Object[] responseObjectAsArray;

	public OdooResponse(final Exception errorCause) {
		this.isSuccessful = false;
		this.errorCause = errorCause;
		this.responseObject = null;
		this.responseObjectAsArray = new Object[0];
	}

	public OdooResponse(final Object responseObject) {
		this.isSuccessful = true;
		this.errorCause = null;
		this.responseObject = responseObject;
		if (responseObject instanceof Object[]) {
			this.responseObjectAsArray = (Object[]) responseObject;
		} else {
			this.responseObjectAsArray = new Object[] { responseObject };
		}
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public Throwable getErrorCause() {
		return errorCause;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public Object[] getResponseObjectAsArray() {
		return responseObjectAsArray;
	}
}
