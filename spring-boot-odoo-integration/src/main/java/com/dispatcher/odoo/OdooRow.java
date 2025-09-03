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
import com.dispatcher.odoo.OdooField.FieldType;
import com.dispatcher.odoo.exception.OdooApiException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TimeZone;

/***
 * Holds data returned from the OpenERP server.
 */
public class OdooRow implements Auditable {

    private final HashMap<String, Object> openERPResult;
    private final OdooFieldCollection fields;
    private final ArrayList<RowChangedListener> rowChangedListeners = new ArrayList<RowChangedListener>();
    private final OdooFieldCollection changedFields = new OdooFieldCollection();

    /**
     * Default constructor
     *
     * @param openERPResult The HashMap object returned from readObject. containing all data for this row
     * @param fields        FieldCollection that this row holds data for.
     * @throws OdooApiException
     */
    public OdooRow(HashMap<String, Object> openERPResult, OdooFieldCollection fields) throws OdooApiException {
        this.openERPResult = openERPResult;
        this.fields = fields;

        // This is a new row, add entries for every field
        if (openERPResult.isEmpty()) {
            this.put("id", 0);
            for (int i = 0; i < fields.size(); i++)
                this.put(fields.get(i).getName(), null);
        }
    }

    /**
     * Returns the database ID of the object/row.
     *
     * @return
     */
    public int getID() {
        Object idValue = get("id");
        return Integer.parseInt(idValue.toString());
    }

    /**
     * Returns the database string field of the object/row.
     *
     * @return string
     */
    public String getString(String fieldName) {
        String res = null;
        if (get(fieldName) != null) {
            res = get(fieldName).toString();
        }
        return res;
    }

    /**
     * Add a listener to be notified when a row is being changed
     *
     * @param listener
     */
    public void addRowChangedLisener(RowChangedListener listener) {
        if (!rowChangedListeners.contains(listener))
            rowChangedListeners.add(listener);
    }

    /**
     * Copy constructor to create a new row from a template row
     */
    @SuppressWarnings("unchecked")
    public OdooRow(OdooRow templateRow) {
        this.openERPResult = (HashMap<String, Object>) templateRow.openERPResult.clone();
        this.fields = (OdooFieldCollection) templateRow.fields.clone();
    }

    /**
     * Get the field information this row is holding data for.
     *
     * @return
     */
    public OdooFieldCollection getFields() {
        return fields;
    }

    /**
     * Fetch the data for a specific field
     *
     * @param field Field to fetch data for
     * @return A data object that can be of any type, or null if the field was not found.
     */
    public Object get(OdooField field) {
        return this.get(field.getName());
    }

    /**
     * Fetch the data for a specific field
     *
     * @param fieldName Field name to fetch data for
     * @return A data object.  It could be of any type, or null if the field was not found.
     * @throws ParseException
     */
    public Object get(String fieldName) {

        // ID is a special case.  It is always returned in a query
        if (fieldName != null && fieldName.equals("id"))
            return openERPResult.get(fieldName);

        OdooField fieldMeta = getField(fieldName);
        if (fieldMeta == null)
            return null;

        Object value = openERPResult.get(fieldName);
        FieldType fieldType = fieldMeta.getType();

        if (fieldType != FieldType.BOOLEAN && value instanceof Boolean)
            return null;

        if (value instanceof Object[] && ((Object[]) value).length == 0)
            return null;

        if (value instanceof String && fieldType == FieldType.DATE) {
            DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
            dfm.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                return dfm.parse(value.toString());
            } catch (ParseException p) {
                return null;
            }
        }

        if (value instanceof String && fieldType == FieldType.DATETIME) {
            DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dfm.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                return dfm.parse(value.toString());
            } catch (ParseException p) {
                return null;
            }
        }

        return value;
    }

    private OdooField getField(String fieldName) {
        for (OdooField fld : fields)
            if (fld.getName().equals(fieldName))
                return fld;
        return null;
    }

    /**
     * Updates row field values
     *
     * @param fieldName Name of the field to update
     * @param value     New value that must be associated with the field
     * @throws OdooApiException
     */
    public void put(String fieldName, Object value) throws OdooApiException {
        if (fieldName.equals("id")) {
            openERPResult.put(fieldName, value);
            return;
        }

        OdooField fld = getField(fieldName);
        if (fld == null)
            throw new OdooApiException("Field '" + fieldName + "' was not found in row");

        FieldType fieldType = fld.getType();

        if (fieldType == FieldType.ONE2MANY)
            value = new Object[]{value, null};

        // See if the value actually changed
        if (openERPResult.containsKey(fieldName)) {
            Object oldValue = openERPResult.get(fieldName);
            if (oldValue == null && value == null)
                return;

            if ((oldValue == null && value != null)
                    || (oldValue != null && value == null)
                    || !oldValue.equals(value))
                openERPResult.remove(fieldName);
            else return;
        }

        openERPResult.put(fieldName, value);

        getChangedFields().add(fld);

        for (RowChangedListener listener : rowChangedListeners)
            listener.rowChanged(fld, this);

    }

    /**
     * Updates the value for a Many2Many field
     *
     * @param fieldName Name of the many to many field to update
     * @param values    Object [] of ids to update the many2many field with
     * @param append    If the values should be added to the existing values.  If not, the value is replaced.
     * @throws OdooApiException
     */
    public void putMany2ManyValue(String fieldName, Object[] values, boolean append) throws OdooApiException {
        OdooField fld = getField(fieldName);
        if (fld.getType() != FieldType.MANY2MANY)
            throw new OdooApiException("Field '" + fieldName + "' is not a many2many field");

        Object currentValue = get(fieldName);

        if (currentValue == null)
            put(fieldName, values);

        ArrayList<Object> newValues = new ArrayList<Object>();

        if (append)
            Collections.addAll(newValues, (Object[]) currentValue);

        for (Object val : values) {
            if (!newValues.contains(val))
                newValues.add(val);
        }

        put(fieldName, newValues.toArray(new Object[newValues.size()]));
    }

    /**
     * Notifies the row that pending changes have been applied for the row to do cleanup,
     * for example changed fields are cleared.
     */
    public void changesApplied() {
        changedFields.clear();
    }

    /**
     * Returns only fields that have changed since the row was loaded
     *
     * @return
     */
    public OdooFieldCollection getChangedFields() {
        return changedFields;
    }

    /**
     * Returns the map with fields content that the row was filled
     *
     * @return
     */
    public HashMap<String, Object> getFieldsOdoo() {
        return openERPResult;
    }

    /***
     * Event handler to notify listeners when a row changes values
     * @author Pieter van der Merwe
     *
     */
    public static interface RowChangedListener {
        void rowChanged(OdooField fld, OdooRow row);
    }
}
