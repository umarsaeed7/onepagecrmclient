package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.Countries;
import com.onepagecrm.models.internal.Country;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CountrySerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(CountrySerializer.class.getName());

    public static Countries fromString(String responseBody) throws OnePageException {
        String parsedResponse;
        OnePageException exception;

        try {
            parsedResponse = (String) BaseSerializer.fromString(responseBody);
            JSONObject dataObject = new JSONObject(parsedResponse);
            JSONArray countriesArray = dataObject.getJSONArray(COUNTRIES_TAG);
            return fromJsonArray(countriesArray);

        } catch (ClassCastException e) {
            exception = (OnePageException) BaseSerializer.fromString(responseBody);
            throw exception;

        } catch (JSONException e) {
            LOG.severe("Could not find Country array");
            LOG.severe(e.toString());
        }
        return new Countries();
    }

    public static Countries fromJsonArray(JSONArray countryArray) {
        List<Country> countries = new ArrayList<>();
        for (int j = 0; j < countryArray.length(); j++) {
            JSONObject countryObject;
            try {
                countryObject = countryArray.getJSONObject(j);
                countryObject = countryObject.getJSONObject(COUNTRY_TAG);
                countries.add(fromJsonObject(countryObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing Country array");
                LOG.severe(e.toString());
            }
        }
        return new Countries(countries);
    }

    public static Country fromJsonObject(JSONObject countryObject) {
        Country country = new Country();
        try {
            if (countryObject.has(NAME_TAG)) {
                country.setName(countryObject.getString(NAME_TAG));
            }
            if (countryObject.has(CODE_TAG)) {
                country.setCode(countryObject.getString(CODE_TAG));
            }
            if (countryObject.has(PHONE_PREFIX_TAG)) {
                country.setPrefix(countryObject.getString(PHONE_PREFIX_TAG));
            }
            if (countryObject.has(CURRENCY_TAG)) {
                country.setCurrency(countryObject.getString(CURRENCY_TAG));
            }
            return country;
        } catch (JSONException e) {
            LOG.severe("Error parsing Country object");
            LOG.severe(e.toString());
        }
        return country;
    }

    public static String toJsonObject(Country country) {
        JSONObject countryObject = new JSONObject();
        addJsonStringValue(country.getName(), countryObject, NAME_TAG);
        addJsonStringValue(country.getCode(), countryObject, CODE_TAG);
        addJsonStringValue(country.getPrefix(), countryObject, PHONE_PREFIX_TAG);
        addJsonStringValue(country.getCurrency(), countryObject, CURRENCY_TAG);
        return countryObject.toString();
    }

    public static String toJsonArray(Countries countries) {
        JSONArray countryArray = new JSONArray();
        List<Country> countryList = countries.getCountryList();
        for (int i = 0; i < countryList.size(); i++) {
            try {
                JSONObject outerObject = new JSONObject();
                JSONObject countryObject = new JSONObject(toJsonObject(countryList.get(i)));
                addJsonObject(countryObject, outerObject, COUNTRY_TAG);
                addJsonObject(outerObject, countryArray);
            } catch (JSONException e) {
                LOG.severe("Error creating JSONObject out of Country");
                LOG.severe(e.toString());
            }
        }
        return countryArray.toString();
    }
}
