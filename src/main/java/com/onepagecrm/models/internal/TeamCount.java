package com.onepagecrm.models.internal;

import java.io.Serializable;

public class TeamCount implements Serializable {

    private String userId;
    private int counts;

    public TeamCount() {
    }

    public TeamCount(String userId, int counts) {
        this.userId = userId;
        this.counts = counts;
    }

    public String toString() {
        return "userId=\'" + userId + "\', counts=\'" + counts;
    }

    public String getUserId() {
        return userId;
    }

    public TeamCount setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public TeamCount setCounts(int counts) {
        this.counts = counts;
        return this;
    }
}