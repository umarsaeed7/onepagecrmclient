package com.onepagecrm.models.serializer;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ContactSerializer extends BaseSerializer {

    private static final Logger LOG = Logger.getLogger(ContactSerializer.class.getName());

    public static Contact fromString(String responseBody) throws OnePageException {
        Contact contact = new Contact();
        try {
            JSONObject responseObject = new JSONObject(responseBody);
            JSONObject dataObject = responseObject.getJSONObject(DATA_TAG);

            contact = fromJsonObject(dataObject);

        } catch (JSONException e) {
            LOG.severe("Error parsing Contact object from response body");
            LOG.severe(e.toString());
        }
        return contact;
    }

    public static Contact fromJsonObject(JSONObject contactsElementObject) {
        Contact contact = new Contact();
        try {
            JSONObject contactObject = contactsElementObject.getJSONObject(CONTACT_TAG);
            if (contactObject.has(ID_TAG)) {
                contact.setId(contactObject.getString(ID_TAG));
            }
            if (contactObject.has(COMPANY_NAME_TAG)) {
                contact.setCompanyName(contactObject.getString(COMPANY_NAME_TAG));
            }
            if (contactObject.has(COMPANY_ID_TAG)) {
                contact.setCompanyId(contactObject.getString(COMPANY_ID_TAG));
            }
            if (contactObject.has(TYPE_TAG)) {
                contact.setType(contactObject.getString(TYPE_TAG));
            }
            if (contactObject.has(FIRST_NAME_TAG)) {
                contact.setFirstName(contactObject.getString(FIRST_NAME_TAG));
            }
            if (contactObject.has(LAST_NAME_TAG)) {
                contact.setLastName(contactObject.getString(LAST_NAME_TAG));
            }
            if (contactObject.has(OWNER_ID_TAG)) {
                contact.setOwnerId(contactObject.getString(OWNER_ID_TAG));
            }
            if (contactObject.has(BACKGROUND_TAG)) {
                contact.setBackground(contactObject.getString(BACKGROUND_TAG));
            }
            if (contactObject.has(JOB_TITLE_TAG)) {
                contact.setJobTitle(contactObject.getString(JOB_TITLE_TAG));
            }
            if (contactObject.has(LEAD_SOURCE_ID_TAG)) {
                contact.setLeadSourceId(contactObject.getString(LEAD_SOURCE_ID_TAG));
            }
            if (contactObject.has(PENDING_DEAL_TAG)) {
                contact.setHasPendingDeal(contactObject.getBoolean(PENDING_DEAL_TAG));
            }
            if (contactObject.has(TOTAL_PENDINGS_TAG)) {
                contact.setTotalPending(contactObject.getDouble(TOTAL_PENDINGS_TAG));
            }
            if (contactObject.has(PHOTO_URL_TAG)) {
                contact.setPhotoUrl(contactObject.getString(PHOTO_URL_TAG));
            }
            if (contactObject.has(SALES_CLOSED_FOR_TAG)) {
                // TODO : sales closed for ...
            }
            if (contactObject.has(STARRED_TAG)) {
                contact.setStarred(contactObject.getBoolean(STARRED_TAG));
            }
            if (contactObject.has(STATUS_TAG)) {
                contact.setStatus(contactObject.getString(STATUS_TAG));
            }
            if (contactObject.has(STATUS_ID_TAG)) {
                contact.setStatusId(contactObject.getString(STATUS_ID_TAG));
            }
            if (contactObject.has(CREATED_AT_TAG)) {
                String createdAtStr = contactObject.getString(CREATED_AT_TAG);
                Date createdAt = DateSerializer.fromFormattedString(createdAtStr);
                contact.setCreatedAt(createdAt);
            }
            if (contactObject.has(MODIFIED_AT_TAG)) {
                String modifiedAtStr = contactObject.getString(MODIFIED_AT_TAG);
                Date modifiedAt = DateSerializer.fromFormattedString(modifiedAtStr);
                contact.setModifiedAt(modifiedAt);
            }
            // Add Tags.
            if (contactObject.has(TAGS_TAG)) {
                List<String> tagNames = BaseSerializer.toListOfStrings(contactObject.getJSONArray(TAGS_TAG));
                List<Tag> tags = new ArrayList<>();
                for (int i = 0; i < tagNames.size(); i++) {
                    tags.add(new Tag().setName(tagNames.get(i)));
                }
                if (!tags.isEmpty()) {
                    contact.setTags(tags);
                }
            }
            // Add Custom Fields.
            if (contactObject.has(CUSTOM_FIELDS_TAG)) {
                JSONArray customFieldsArray = contactObject.getJSONArray(CUSTOM_FIELDS_TAG);
                List<CustomField> customFields = CustomFieldSerializer.fromJsonArray(customFieldsArray);
                if (!customFields.isEmpty()) {
                    contact.setCustomFields(customFields);
                }
            }
            // Add Phones.
            if (contactObject.has(PHONES_TAG)) {
                JSONArray phonesArray = contactObject.getJSONArray(PHONES_TAG);
                List<Phone> phones = PhoneSerializer.fromJsonArray(phonesArray);
                if (!phones.isEmpty()) {
                    contact.setPhones(phones);
                }
            }
            // Add Emails.
            if (contactObject.has(EMAILS_TAG)) {
                JSONArray emailsArray = contactObject.getJSONArray(EMAILS_TAG);
                List<Email> emails = EmailSerializer.fromJsonArray(emailsArray);
                if (!emails.isEmpty()) {
                    contact.setEmails(emails);
                }
            }
            // Add Websites.
            if (contactObject.has(URLS_TAG)) {
                JSONArray urlsArray = contactObject.getJSONArray(URLS_TAG);
                List<Url> urls = UrlSerializer.fromJsonArray(urlsArray);
                if (!urls.isEmpty()) {
                    contact.setUrls(urls);
                }
            }
            // Add Address.
            if (contactObject.has(ADDRESS_LIST_TAG)) {
                JSONArray addressArray = contactObject.getJSONArray(ADDRESS_LIST_TAG);
                Address address = AddressSerializer.fromJsonArray(addressArray);
                contact.setAddress(address);
            }
            if (contactsElementObject.has(NEXT_ACTIONS_TAG)) {
                JSONArray actionsArray = contactsElementObject.getJSONArray(NEXT_ACTIONS_TAG);
                List<Action> actions = ActionSerializer.fromJsonArray(actionsArray);
                contact.setActions(actions);
            }
            if (contactsElementObject.has(NEXT_ACTION_TAG)) {
                JSONObject nextActionObject = contactsElementObject.getJSONObject(NEXT_ACTION_TAG);
                Action nextAction = ActionSerializer.fromJsonObject(nextActionObject);
                contact.setNextAction(nextAction);
            }

            return contact;

        } catch (JSONException e) {
            LOG.severe("Error parsing Contact object");
            LOG.severe(e.toString());
            return new Contact();
        }
    }

    /**
     * Serialize Contact object to JSON.
     *
     * @param contact
     * @return
     */
    public static String toJsonObjectFull(Contact contact) {

        JSONObject contactAndActionsObject = new JSONObject();
        JSONObject contactObject = new JSONObject();
        try {
            contactObject = new JSONObject(toJsonObject(contact));
        } catch (JSONException e) {
            LOG.severe("Error creating inner Contact object while constructing Contact object");
            LOG.severe(e.toString());
        }

        // Serialize Actions.
        try {
            JSONArray nextActionsArray = new JSONArray(ActionSerializer.toJsonArray(contact.getActions()));
            addJsonArray(nextActionsArray, contactAndActionsObject, NEXT_ACTIONS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Next Action array while constructing Contact object");
            LOG.severe(e.toString());
        }

        // Serialize Next Action.
        try {
            JSONObject nextActionObject = new JSONObject(ActionSerializer.toJsonObject(contact.getNextAction()));
            addJsonObject(nextActionObject, contactAndActionsObject, NEXT_ACTION_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Next Action object while constructing Contact object");
            LOG.severe(e.toString());
        }

        addJsonObject(contactObject, contactAndActionsObject, CONTACT_TAG);

        return contactAndActionsObject.toString();
    }

    /**
     * Serialize Contact object to JSON.
     *
     * @param contact
     * @return
     */
    public static String toJsonObject(Contact contact) {

        JSONObject contactObject = new JSONObject();

        addJsonStringValue(contact.getId(), contactObject, ID_TAG);
        addJsonStringValue(contact.getType(), contactObject, TYPE_TAG);
        addJsonStringValue(contact.getFirstName(), contactObject, FIRST_NAME_TAG);
        addJsonStringValue(contact.getLastName(), contactObject, LAST_NAME_TAG);
        addJsonStringValue(contact.getCompanyName(), contactObject, COMPANY_NAME_TAG);
        addJsonStringValue(contact.getCompanyId(), contactObject, COMPANY_ID_TAG);
        addJsonStringValue(contact.getJobTitle(), contactObject, JOB_TITLE_TAG);
        addJsonStringValue(contact.getBackground(), contactObject, BACKGROUND_TAG);
        addJsonStringValue(contact.getStatus(), contactObject, STATUS_TAG);
        addJsonStringValue(contact.getStatusId(), contactObject, STATUS_ID_TAG);
        addJsonStringValue(contact.getLeadSourceId(), contactObject, LEAD_SOURCE_ID_TAG);
        addJsonBooleanValue(contact.hasPendingDeal(), contactObject, PENDING_DEAL_TAG);
        addJsonDoubleValue(contact.getTotalPending(), contactObject, TOTAL_PENDINGS_TAG);
        addJsonStringValue(contact.getOwnerId(), contactObject, OWNER_ID_TAG);
        addJsonStringValue(contact.getPhotoUrl(), contactObject, PHOTO_URL_TAG);
        addJsonBooleanValue(contact.isStarred(), contactObject, STARRED_TAG);

        // Serialize Custom Fields.
        try {
            JSONArray customFieldsArray = new JSONArray(CustomFieldSerializer.toJsonArray(contact.getCustomFields()));
            addJsonArray(customFieldsArray, contactObject, CUSTOM_FIELDS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating CustomField array while constructing Contact object");
            LOG.severe(e.toString());
        }

        // Serialize Address.
        try {
            JSONArray addressArray = new JSONArray(AddressSerializer.toJsonArray(contact.getAddress()));
            addJsonArray(addressArray, contactObject, ADDRESS_LIST_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Address array while constructing Contact object");
            LOG.severe(e.toString());
        }

        // Serialize Phones.
        try {
            JSONArray phonesArray = new JSONArray(PhoneSerializer.toJsonArray(contact.getPhones()));
            addJsonArray(phonesArray, contactObject, PHONES_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Phone array while constructing Contact object");
            LOG.severe(e.toString());
        }

        // Serialize Emails.
        try {
            JSONArray emailsArray = new JSONArray(EmailSerializer.toJsonArray(contact.getEmails()));
            addJsonArray(emailsArray, contactObject, EMAILS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Email array while constructing Contact object");
            LOG.severe(e.toString());
        }

        // Serialize Urls.
        try {
            JSONArray urlsArray = new JSONArray(UrlSerializer.toJsonArray(contact.getUrls()));
            addJsonArray(urlsArray, contactObject, URLS_TAG);
        } catch (JSONException e) {
            LOG.severe("Error creating Url array while constructing Contact object");
            LOG.severe(e.toString());
        }

        // Serialize Tags.
        List<Tag> tags = contact.getTags();
        List<String> tagNames = new ArrayList<>();
        if (tags != null && !tags.isEmpty()) {
            for (int i = 0; i < contact.getTags().size(); i++) {
                tagNames.add(contact.getTags().get(i).getName());
            }
        }
        addJsonArray(BaseSerializer.toJsonStringArray(tagNames), contactObject, TAGS_TAG);

        return contactObject.toString();
    }

    public static String toJsonArray(ContactList contacts) {
        JSONArray contactsArray = new JSONArray();
        if (contacts != null && !contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                try {
                    String contactString = toJsonObject(contacts.get(i));
                    contactsArray.put(new JSONObject(contactString));
                } catch (JSONException e) {
                    LOG.severe("Error serializing Contacts array out of ContactList");
                    LOG.severe(e.toString());
                }
            }
        }
        return contactsArray.toString();
    }
}
