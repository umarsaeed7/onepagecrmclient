package com.onepagecrm.models;

import com.onepagecrm.models.internal.CustomFieldValue;
import com.onepagecrm.models.serializer.CustomFieldSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.List;

public class CustomField extends ApiResource implements Serializable {

    public static final String TYPE_ANNIVERSARY = "anniversary";
    public static final String TYPE_DATE = "date";
    public static final String TYPE_DROPDOWN = "select_box";
    public static final String TYPE_MULTI_LINE_TEXT = "multi_line_text";
    public static final String TYPE_MULTIPLE_CHOICE = "multiple_choice";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_SINGLE_LINE_TEXT = "single_line_text";

    private String id;
    private String name;
    private Integer position;
    private String type;
    private List<String> choices;
    private Integer reminderDays;
    private CustomFieldValue value;

    public static List<CustomField> list() {
        Request request = new GetRequest(CUSTOM_FIELDS_ENDPOINT, Query.perPageQueryString(100));
        Response response = request.send();
        return CustomFieldSerializer.fromString(response.getResponseBody());
    }

    public String save() {
        Request request = new PostRequest(
                CUSTOM_FIELDS_ENDPOINT,
                null,
                CustomFieldSerializer.toJsonObjectNew(this)
        );
        Response response = request.send();
        return response.getResponseBody();
    }

    public CustomField() {
    }

    public CustomField(CustomField customField) {
        this.id = customField.getId();
        this.name = customField.getName();
        this.position = customField.getPosition();
        this.type = customField.getType();
        this.choices = customField.getChoices();
        this.reminderDays = customField.getReminderDays();
        this.value = customField.getValue();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public CustomField setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return CustomFieldSerializer.toJsonObject(this);
    }

    public String getName() {
        return name;
    }

    public CustomField setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPosition() {
        return position;
    }

    public CustomField setPosition(Integer position) {
        this.position = position;
        return this;
    }

    public String getType() {
        return type;
    }

    public CustomField setType(String type) {
        this.type = type;
        return this;
    }

    public List<String> getChoices() {
        return choices;
    }

    public CustomField setChoices(List<String> choices) {
        this.choices = choices;
        return this;
    }

    public Integer getReminderDays() {
        return reminderDays;
    }

    public CustomField setReminderDays(Integer reminderDays) {
        this.reminderDays = reminderDays;
        return this;
    }

    public CustomFieldValue getValue() {
        return value;
    }

    public CustomField setValue(CustomFieldValue value) {
        this.value = value;
        return this;
    }
}
