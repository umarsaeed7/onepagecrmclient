package com.onepagecrm.models;

import com.onepagecrm.models.serializer.DealSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cillian Myles (cillian@onepagecrm.com) on 11/24/15.
 */
public class DealList extends ArrayList<Deal> implements Serializable {

    private List<Deal> deals;

    public DealList(List<Deal> deals) {
        this.deals = new ArrayList<>();
        if (deals != null && !deals.isEmpty()) {
            for (int i = 0; i < deals.size(); i++) {
                this.deals.add(deals.get(i));
            }
        }
    }

    public DealList() {
        this.deals = new ArrayList<>();
    }

    public String toString() {
        return DealSerializer.toJsonArray(this);
    }

    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = new ArrayList<>();
        if (deals != null && !deals.isEmpty()) {
            for (int i = 0; i < deals.size(); i++) {
                this.deals.add(deals.get(i));
            }
        }
    }

    public boolean isEmpty() {
        return deals.isEmpty();
    }

    public int size() {
        return deals.size();
    }

    public void add(int index, Deal deal) {
        deals.add(index, deal);
    }

    public boolean add(Deal deal) {
        return deals.add(deal);
    }

    public Deal get(int index) {
        return deals.get(index);
    }

    public Deal set(int index, Deal deal) {
        deals.set(index, deal);
        return deal;
    }

    public int indexOf(Deal deal) {
        return deals.indexOf(deal);
    }
}
