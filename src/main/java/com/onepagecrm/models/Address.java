package com.onepagecrm.models;

import com.onepagecrm.models.serializer.AddressSerializer;

import java.io.Serializable;

public class Address extends BaseResource implements Serializable {

    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String countryCode;

    public Address() {
    }

    @Override
    public String toString() {
        return AddressSerializer.toJsonArray(this);
    }

    public boolean isValid() {
        return address != null && !address.equals("") ||
                city != null && !city.equals("") ||
                state != null && !state.equals("") ||
                zipCode != null && !zipCode.equals("") ||
                countryCode != null && !countryCode.equals("");
    }

    @Override
    public boolean equals(Object object) {
        boolean addressObjectsEqual = false;
        boolean addressesEqual = false;
        boolean citiesEqual = false;
        boolean statesEqual = false;
        boolean zipCodesEqual = false;
        boolean countryCodesEqual = false;
        if (object instanceof Address) {
            Address toCompare = (Address) object;
            if (this.address != null && toCompare.address != null) {
                addressesEqual = this.address.equals(toCompare.address);
            }
            if (this.city != null && toCompare.city != null) {
                citiesEqual = this.city.equals(toCompare.city);
            }
            if (this.state != null && toCompare.state != null) {
                statesEqual = this.state.equals(toCompare.state);
            }
            if (this.zipCode != null && toCompare.zipCode != null) {
                zipCodesEqual = this.zipCode.equals(toCompare.zipCode);
            }
            if (this.countryCode != null && toCompare.countryCode != null) {
                countryCodesEqual = this.countryCode.equals(toCompare.countryCode);
            }
            addressObjectsEqual = addressesEqual
                    && citiesEqual
                    && statesEqual
                    && zipCodesEqual
                    && countryCodesEqual;
        }
        return addressObjectsEqual;
    }

    public String getAddress() {
        return address;
    }

    public Address setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Address setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }
}
