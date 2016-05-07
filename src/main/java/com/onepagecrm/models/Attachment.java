package com.onepagecrm.models;

import com.onepagecrm.net.ApiResource;

import java.io.Serializable;

public class Attachment extends ApiResource implements Serializable {

    private String id;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Attachment setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return null;
    }
}
