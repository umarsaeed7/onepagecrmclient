package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Country;
import com.onepagecrm.models.serializer.CountrySerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.GetRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.*;

public class Countries extends ArrayList<Country> implements Serializable {

    private static final String COUNTRIES_ENDPOINT = ApiResource.COUNTRIES_ENDPOINT;

    private List<Country> countryList;
    private Map<String, Country> countryMap;

    public static Countries list() throws OnePageException {
        Request request = new GetRequest(COUNTRIES_ENDPOINT);
        Response response = request.send();
        return CountrySerializer.fromString(response.getResponseBody());
    }

    public Countries(List<Country> countryList) {
        this.countryList = new ArrayList<>();
        this.countryMap = new HashMap<>();
        if (countryList != null && !countryList.isEmpty()) {
            for (int i = 0; i < countryList.size(); i++) {
                Country country = countryList.get(i);
                this.countryList.add(country);
                this.countryMap.put(country.getCode(), country);
            }
        }
    }

    public Countries(Map<String, Country> countryMap) {
        this.countryList = new ArrayList<>();
        this.countryMap = new HashMap<>();
        Iterator it = countryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String countryCode = ((Country) pair.getKey()).getCode();
            Country country = (Country) pair.getValue();
            this.countryMap.put(countryCode, country);
            this.countryList.add(country);
            it.remove();
        }
    }

    public Countries() {
        this.countryList = new ArrayList<>();
        this.countryMap = new HashMap<>();
    }

    @Override
    public String toString() {
        return CountrySerializer.toJsonArray(this);
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public Countries setCountryList(List<Country> countryList) {
        if (countryList != null && !countryList.isEmpty()) {
            for (int i = 0; i < countryList.size(); i++) {
                Country country = countryList.get(i);
                this.countryList.add(country);
                this.countryMap.put(country.getCode(), country);
            }
        }
        return this;
    }

    public Map<String, Country> getCountryMap() {
        return countryMap;
    }

    public Countries setCountryMap(Map<String, Country> countryMap) {
        Iterator it = countryMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String countryCode = ((Country) pair.getKey()).getCode();
            Country country = (Country) pair.getValue();
            this.countryMap.put(countryCode, country);
            this.countryList.add(country);
            it.remove();
        }
        return this;
    }

    public boolean isEmpty() {
        return countryList.isEmpty() && countryMap.isEmpty();
    }

    public int size() {
        return countryList.size();
    }

    public void add(int index, Country country) {
        countryList.add(index, country);
        countryMap.put(country.getCode(), country);
    }

    public Country get(int index) {
        return countryList.get(index);
    }

    public Country get(String code) {
        return countryMap.get(code);
    }
}
