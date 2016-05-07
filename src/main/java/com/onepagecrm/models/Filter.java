package com.onepagecrm.models;

import com.onepagecrm.models.serializer.FilterSerializer;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;

/**
 * Created by Cillian Myles <cillian@onepagecrm.com> on 01/12/2015.
 */
public class Filter extends ApiResource implements Serializable {

    private String id;
    private String name;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public ApiResource setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return FilterSerializer.toJsonObject(this);
    }

    public String getName() {
        return name;
    }

    public Filter setName(String name) {
        this.name = name;
        return this;
    }
}
