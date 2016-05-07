package com.onepagecrm.models.internal;

import com.onepagecrm.models.serializer.CountrySerializer;

import java.io.Serializable;

public class Country implements Serializable {

    private String name;
    private String code;
    private String currency;
    private String prefix;

    @Override
    public String toString() {
        return CountrySerializer.toJsonObject(this);
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Country setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public Country setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public Country setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
