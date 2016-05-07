package com.onepagecrm.models.internal;

import com.onepagecrm.models.serializer.BaseSerializer;
import com.onepagecrm.models.serializer.CustomFieldValueSerializer;

import java.io.Serializable;

public class CustomFieldValue implements Serializable {

    private String stringValue;
    private Integer integerValue;
    private Float floatValue;
    private String[] stringArrayValue;

    public Serializable getValue() {
        if (stringValue != null) {
            return this.stringValue;
        } else if (integerValue != null) {
            return this.integerValue;
        } else if (floatValue != null) {
            return this.floatValue;
        } else if (stringArrayValue != null) {
            return this.stringArrayValue;
        }
        return null;
    }

    public String getStringValue() {
        String retString = "";
        if (stringValue != null) {
            retString = this.stringValue;
        } else if (integerValue != null) {
            retString = String.valueOf(this.integerValue);
        } else if (floatValue != null) {
            retString = String.valueOf(this.floatValue);
        } else if (stringArrayValue != null) {
            retString = BaseSerializer.toCommaSeparatedString(this.stringArrayValue);
        }
        return retString;
    }

    public Serializable setValue(Object object) {
        if (object instanceof String) {
            this.setValue((String) object);
        }
        if (object instanceof Integer) {
            this.setValue((Integer) object);
        }
        if (object instanceof Float) {
            this.setValue((Float) object);
        }
        if (object instanceof String[]) {
            this.setValue((String[]) object);
        }
        return this;
    }

    @Override
    public String toString() {
        return CustomFieldValueSerializer.toJsonObject(this);
    }

    public Serializable setValue(String stringValue) {
        this.stringValue = stringValue;
        this.integerValue = null;
        this.floatValue = null;
        this.stringArrayValue = null;
        return getValue();
    }

    public Serializable setValue(Integer integerValue) {
        this.stringValue = null;
        this.integerValue = integerValue;
        this.floatValue = null;
        this.stringArrayValue = null;
        return getValue();
    }

    public Serializable setValue(Float floatValue) {
        this.stringValue = null;
        this.integerValue = null;
        this.floatValue = floatValue;
        this.stringArrayValue = null;
        return getValue();
    }

    public Serializable setValue(String[] stringArrayValue) {
        this.stringValue = null;
        this.integerValue = null;
        this.floatValue = null;
        this.stringArrayValue = stringArrayValue;
        return getValue();
    }
}
