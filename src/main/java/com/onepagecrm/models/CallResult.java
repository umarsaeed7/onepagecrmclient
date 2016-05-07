package com.onepagecrm.models;

import com.onepagecrm.models.serializer.CallResultSerializer;

import java.io.Serializable;

public class CallResult extends BaseResource implements Serializable {

    private static final long serialVersionUID = -3981577783870512717L;

    private String id; // id for the call result e.g. "interested", "559bc6c31787fa7b1700024b"
    private String display; // display text for the call result e.g. "Interested", "EXTRA"
    private String text; // note for the call result e.g. "He was interested", "This is a different note"

    private int intId; // needed internally

    public CallResult() {
    }

    public String getId() {
        return id;
    }

    public CallResult setId(String id) {
        this.id = id;
        return this;
    }

    public String toString() {
        return CallResultSerializer.toJsonObject(this);
    }

    public boolean equals(Object object) {
        if (object instanceof CallResult) {
            CallResult toCompare = (CallResult) object;
            if (this.id != null && toCompare.id != null) {
                return this.id.equals(toCompare.id);
            }
        }
        return false;
    }

    public String getText() {
        return text;
    }

    public CallResult setText(String text) {
        this.text = text;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public CallResult setDisplay(String display) {
        this.display = display;
        return this;
    }

    public int getintId() {
        return intId;
    }

    public CallResult setIntId(int intId) {
        this.intId = intId;
        return this;
    }
}
