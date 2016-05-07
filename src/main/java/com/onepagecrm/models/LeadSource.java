package com.onepagecrm.models;

import com.onepagecrm.models.internal.TeamCount;
import com.onepagecrm.models.serializer.LeadSourceSerializer;
import com.onepagecrm.net.ApiResource;

import java.io.Serializable;
import java.util.List;

public class LeadSource extends ApiResource implements Serializable {

    private String id;
    private String text;
    private int counts;
    private int totalCount;
    private List<TeamCount> teamCounts;

    public LeadSource() {
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public LeadSource setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return LeadSourceSerializer.toJsonObject(this);
    }

    public String getText() {
        return text;
    }

    public LeadSource setText(String text) {
        this.text = text;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public LeadSource setCounts(int counts) {
        this.counts = counts;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public LeadSource setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public List<TeamCount> getTeamCounts() {
        return teamCounts;
    }

    public LeadSource setTeamCounts(List<TeamCount> teamCounts) {
        this.teamCounts = teamCounts;
        return this;
    }
}
