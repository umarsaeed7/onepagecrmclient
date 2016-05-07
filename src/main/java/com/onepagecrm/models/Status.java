package com.onepagecrm.models;

import java.io.Serializable;
import java.util.List;

import com.onepagecrm.models.internal.TeamCount;
import com.onepagecrm.models.serializer.StatusSerializer;
import com.onepagecrm.net.ApiResource;

public class Status extends ApiResource implements Serializable {

    private String id;
    private String color;
    private String status;
    private String text;
    private String description;
    private int counts;
    private int totalCount;
    private int actionStreamCount;
    private List<TeamCount> teamCounts;

    public Status() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Status setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return StatusSerializer.toJsonObject(this);
    }

    public String getColor() {
        return color;
    }

    public Status setColor(String color) {
        this.color = color;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Status setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getText() {
        return text;
    }

    public Status setText(String text) {
        this.text = text;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Status setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public Status setCounts(int counts) {
        this.counts = counts;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Status setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getActionStreamCount() {
        return actionStreamCount;
    }

    public Status setActionStreamCount(int actionStreamCount) {
        this.actionStreamCount = actionStreamCount;
        return this;
    }

    public List<TeamCount> getTeamCounts() {
        return teamCounts;
    }

    public Status setTeamCounts(List<TeamCount> teamCounts) {
        this.teamCounts = teamCounts;
        return this;
    }
}