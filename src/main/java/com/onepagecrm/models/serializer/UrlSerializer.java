package com.onepagecrm.models.serializer;

import com.onepagecrm.models.Url;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UrlSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(UrlSerializer.class.getName());

    public static List<Url> fromJsonArray(JSONArray urlsArray) {
        List<Url> urls = new ArrayList<>();
        for (int j = 0; j < urlsArray.length(); j++) {
            JSONObject urlObject;
            try {
                urlObject = urlsArray.getJSONObject(j);
                urls.add(fromJsonObject(urlObject));
            } catch (JSONException e) {
                LOG.severe("Error parsing url array");
                LOG.severe(e.toString());
            }
        }
        return urls;
    }

    public static Url fromJsonObject(JSONObject urlObject) {
        Url url = new Url();
        try {
            String type = urlObject.getString(TYPE_TAG);
            String value = urlObject.getString(VALUE_TAG);
            return url
                    .setType(type)
                    .setValue(value);
        } catch (JSONException e) {
            LOG.severe("Error parsing url object");
            LOG.severe(e.toString());
        }
        return url;
    }

    public static String toJsonObject(Url url) {
        if (url.getValue() != null) {
            JSONObject urlObject = new JSONObject();
            String webType = url.getType();
            if (webType != null && webType.length() > 0) {
                if (!webType.equalsIgnoreCase("google+")) {
                    webType = webType.toLowerCase();
                } else {
                    webType = "google_plus";
                }
            }
            addJsonStringValue(webType, urlObject, TYPE_TAG);
            addJsonStringValue(url.getValue(), urlObject, VALUE_TAG);
            return urlObject.toString();
        } else {
            return null;
        }
    }

    public static String toJsonArray(List<Url> urls) {
        JSONArray urlsArray = new JSONArray();
        if (urls != null && !urls.isEmpty()) {
            for (int i = 0; i < urls.size(); i++) {
                try {
                    if (urls.get(i).getValue() != null) {
                        urlsArray.put(new JSONObject(toJsonObject(urls.get(i))));
                    }
                } catch (JSONException e) {
                    LOG.severe("Error creating JSONArray out of Urls");
                    LOG.severe(e.toString());
                }
            }
        }
        return urlsArray.toString();
    }
}
