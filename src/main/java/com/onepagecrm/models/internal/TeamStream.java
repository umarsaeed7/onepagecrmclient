package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.List;

public class TeamStream implements Serializable {

    private List<TeamCount> users;
    private int all;

    public TeamStream() {

    }

    public TeamStream(List<TeamCount> users, int all) {
        this.users = users;
        this.all = all;
    }

    public String toString() {
        return "users=\'" + users + "\', all=\'" + all;
    }

    public List<TeamCount> getUsers() {
        return users;
    }

    public TeamStream setUsers(List<TeamCount> users) {
        this.users = users;
        return this;
    }

    public int getAll() {
        return all;
    }

    public TeamStream setAll(int all) {
        this.all = all;
        return this;
    }
}
