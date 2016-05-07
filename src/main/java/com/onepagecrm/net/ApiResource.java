package com.onepagecrm.net;

import com.onepagecrm.models.BaseResource;

import java.io.Serializable;

public abstract class ApiResource extends BaseResource implements Serializable {

    public static final String CHARSET = "UTF-8";

    public static final String ACTION_STREAM_ENDPOINT = "action_stream";
    public static final String CONTACTS_ENDPOINT = "contacts";
    public static final String CALLS_ENDPOINT = "calls";
    public static final String CUSTOM_FIELDS_ENDPOINT = "custom_fields";
    public static final String COUNTRIES_ENDPOINT = "countries"; // -> Countries class as doesn't inherit from here
    public static final String TAGS_ENDPOINT = "tags"; // -> Countries class as doesn't inherit from here
    public static final String DEALS_ENDPOINT = "deals";

    public abstract String getId();

    public abstract ApiResource setId(String id);

    @Override
    public abstract String toString();

    /**
     * Method to compare ApiResource's to one another based off of their id.
     *
     * @param object
     * @return
     */
    public boolean equals(Object object) {
        if (object instanceof ApiResource) {
            ApiResource toCompare = (ApiResource) object;
            if (this.getId() != null && toCompare.getId() != null) {
                return this.getId().equals(toCompare.getId());
            }
        }
        return false;
    }

    public boolean isValid() {
        return this.getId() != null && !this.getId().equals("");
    }
}
