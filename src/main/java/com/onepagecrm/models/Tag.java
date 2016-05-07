package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializer.TagSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.DeleteRequest;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.List;

public class Tag extends BaseResource implements Serializable {

    private static final String TAGS_ENDPOINT = ApiResource.TAGS_ENDPOINT;

    private String name;
    private Integer counts;
    private Integer totalCounts;
    private Integer actionStreamCount;

    public static List<Tag> list() throws OnePageException {
        Request request = new GetRequest(
                TAGS_ENDPOINT,
                Query.perPageQueryString(100)
        );
        Response response = request.send();
        return TagSerializer.fromString(response.getResponseBody());
    }

    public boolean save() throws OnePageException {
        Request request = new PostRequest(
                TAGS_ENDPOINT,
                null,
                TagSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return TagSerializer.fromStringSave(response.getResponseBody());
    }

    public boolean delete() throws OnePageException {
        Request request = new DeleteRequest(addNameToEndpoint(TAGS_ENDPOINT));
        Response response = request.send();
        return TagSerializer.fromStringDelete(response.getResponseBody());
    }

    private String addNameToEndpoint(String endpoint) {
        return endpoint + "/" + this.name;
    }

    public Tag() {
    }

    public Tag(Tag tag) {
        this.name = tag.getName();
        this.counts = tag.getCounts();
        this.totalCounts = tag.getTotalCounts();
        this.actionStreamCount = tag.getActionStreamCount();
    }

    @Override
    public String toString() {
        return TagSerializer.toJsonObject(this);
    }

    /**
     * Method to compare Tags to one another based off of their name.
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Tag) {
            Tag toCompare = (Tag) object;
            if (this.name != null && toCompare.name != null) {
                return this.name.equals(toCompare.name);
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCounts() {
        return counts;
    }

    public Tag setCounts(Integer counts) {
        this.counts = counts;
        return this;
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public Tag setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
        return this;
    }

    public Integer getActionStreamCount() {
        return actionStreamCount;
    }

    public Tag setActionStreamCount(Integer actionStreamCount) {
        this.actionStreamCount = actionStreamCount;
        return this;
    }
}
