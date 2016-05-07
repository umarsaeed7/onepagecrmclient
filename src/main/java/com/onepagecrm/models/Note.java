package com.onepagecrm.models;

import com.onepagecrm.net.ApiResource;

import java.io.Serializable;

public class Note extends ApiResource implements Serializable {

    private String id;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Note setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return null;
    }
}
