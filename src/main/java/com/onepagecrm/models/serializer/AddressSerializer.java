package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Address;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

public class AddressSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(AddressSerializer.class.getName());

    public static Address fromJsonArray(JSONArray addressArray) {
        ArrayList<Address> addresses = new ArrayList<>();
        for (int j = 0; j < addressArray.length(); j++) {
            JSONObject addressObject;
            try {
                addressObject = addressArray.getJSONObject(j);
                addresses.add(fromJsonObject(addressObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing Address array");
                LOG.severe(e.toString());
            }
        }
        return addresses.get(0);
    }

    public static Address fromJsonObject(JSONObject addressObject) {
        Address address = new Address();
        try {
            if (addressObject.has(ADDRESS_TAG)) {
                address.setAddress(addressObject.getString(ADDRESS_TAG));
            }
            if (addressObject.has(CITY_TAG)) {
                address.setCity(addressObject.getString(CITY_TAG));
            }
            if (addressObject.has(STATE_TAG)) {
                address.setState(addressObject.getString(STATE_TAG));
            }
            if (addressObject.has(ZIP_CODE_TAG)) {
                address.setZipCode(addressObject.getString(ZIP_CODE_TAG));
            }
            if (addressObject.has(COUNTRY_CODE_TAG)) {
                address.setCountryCode(addressObject.getString(COUNTRY_CODE_TAG));
            }
        } catch (JSONException e) {
            LOG.severe("Error parsing Address object");
            LOG.severe(e.toString());
        }
        return address;
    }

    public static String toJsonObject(Address address) {
        JSONObject addressObject = new JSONObject();
        if (address != null) {
            addJsonStringValue(address.getAddress(), addressObject, ADDRESS_TAG);
            addJsonStringValue(address.getCity(), addressObject, CITY_TAG);
            addJsonStringValue(address.getState(), addressObject, STATE_TAG);
            addJsonStringValue(address.getZipCode(), addressObject, ZIP_CODE_TAG);
            addJsonStringValue(address.getCountryCode(), addressObject, COUNTRY_CODE_TAG);
        }
        return addressObject.toString();
    }

    public static String toJsonArray(Address address) {
        JSONArray addressArray = new JSONArray();
        if (address != null) {
            try {
                String addressString = toJsonObject(address);
                if (addressString != null) {
                    addressArray.put(new JSONObject(addressString));
                }
            } catch (JSONException e) {
                LOG.severe("Error creating JSONObject out of Address");
                LOG.severe(e.toString());
            }
        }
        return addressArray.toString();
    }
}
