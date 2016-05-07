package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.Date;

public class Sales implements Serializable {

    private int target;
    private int won;
    private int pending;
    private Date expires; // ************

    public Sales() {

    }

    public Sales(int target, int won, int pending, Date expires) {
        this.target = target;
        this.won = won;
        this.pending = pending;
        this.expires = expires;
    }

    public int getTarget() {
        return target;
    }

    public Sales setTarget(int target) {
        this.target = target;
        return this;
    }

    public int getWon() {
        return won;
    }

    public Sales setWon(int won) {
        this.won = won;
        return this;
    }

    public int getPending() {
        return pending;
    }

    public Sales setPending(int pending) {
        this.pending = pending;
        return this;
    }

    public Date getExpires() {
        return expires;
    }

    public Sales setExpires(Date expires) {
        this.expires = expires;
        return this;
    }
}