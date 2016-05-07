package com.onepagecrm.models;

import java.io.Serializable;

public abstract class BaseResource implements Serializable {

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object object);
}
