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

import com.dispatcher.common.base.Auditable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/***
 * Array of Field objects.
 */
public class OdooFieldCollection extends ArrayList<OdooField> implements Auditable {

	private static final long serialVersionUID = 470551054665276346L;
	
	/**
	 * Sorts the field entries in this field collection by Name
	 */
	public void SortByName(){
	  Collections.sort(this,new FieldByNameComparator());
	}
	
	private class FieldByNameComparator implements Comparator<OdooField> {

	  @Override
	  public int compare(OdooField arg0, OdooField arg1) {
	    return arg0.getName().compareTo(arg1.getName());
	  }
	}
}
