package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializer.ContactPhotoSerializer;
import com.onepagecrm.models.serializer.ContactSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.PostRequest;
import com.onepagecrm.net.request.PutRequest;
import com.onepagecrm.net.request.Request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Contact extends ApiResource implements Serializable {

    private static final Logger LOG = Logger.getLogger(Contact.class.getName());

    private static final long serialVersionUID = -6073805195226829625L;

    private String id;
    private int intId;
    public static int nextIntId = 1;

    private String ownerId;
    private String firstName;
    private String lastName;
    private String photoUrl;
    private String jobTitle;
    private String background;
    private List<Url> urls;
    private List<Phone> phones;
    private List<Email> emails;
    private String status;
    private String statusId;
    private Boolean starred;

    private String leadSourceId;
    // private LeadSource leadSource;

    private Boolean hasPendingDeal;
    private Double totalPending;
    private String type;
    private String companyName;
    private String companyId;

    // "sale_closed_for": [ ]

    private List<Tag> tags;
    private List<CustomField> customFields;
    private Date createdAt;
    private Date modifiedAt;

    private Address address;

    private List<Action> actions;
    private Action nextAction;

    // private Address address;

    public Contact save() throws OnePageException {
        return this.isValid() ? update() : create();
    }

    private Contact create() throws OnePageException {
        Request request = new PostRequest(
                CONTACTS_ENDPOINT,
                null,
                ContactSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return ContactSerializer.fromString(response.getResponseBody());
    }

    private Contact update() throws OnePageException {
        Request request = new PutRequest(
                addContactIdToEndpoint(CONTACTS_ENDPOINT),
                null,
                ContactSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return ContactSerializer.fromString(response.getResponseBody());
    }

    public Contact addPhoto(String base64EncodedImageString) throws OnePageException {
        Request request = new PutRequest(
                addContactPhotoToEndpoint(CONTACTS_ENDPOINT),
                null,
                ContactPhotoSerializer.toJsonObject(base64EncodedImageString)
        );
        Response response = request.send();
        Contact photoContact = ContactPhotoSerializer.fromString(response.getResponseBody());
        if (this.isValid() && photoContact.photoUrl != null && !photoContact.photoUrl.equals("")) {
            this.photoUrl = photoContact.photoUrl;
        }
        return this;
    }

    private String addContactIdToEndpoint(String endpoint) {
        return endpoint + "/" + this.id;
    }

    private String addContactPhotoToEndpoint(String endpoint) {
        return addContactIdToEndpoint(endpoint) + "/contact_photo";
    }

    public Contact() {
        this.intId = nextIntId;
        nextIntId++;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Contact setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return ContactSerializer.toJsonObject(this);
    }

    public String getFullName() {
        if (lastName != null && !lastName.equals("")) {
            if (firstName != null && !firstName.equals("")) {
                return firstName + " " + lastName;
            } else {
                return lastName;
            }
        }
        return null;
    }

    public String getFullAlphaName() {
        if (lastName != null && !lastName.equals("")) {
            if (firstName != null && !firstName.equals("")) {
                return lastName + ", " + firstName;
            } else {
                return lastName;
            }
        }
        return null;
    }

    public Contact setIntId(int intId) {
        this.intId = intId;
        return this;
    }

    public int getIntId() {
        return intId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Contact setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Contact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Contact setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Contact setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public String getBackground() {
        return background;
    }

    public Contact setBackground(String background) {
        this.background = background;
        return this;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public Contact setUrls(List<Url> urls) {
        this.urls = urls;
        return this;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public Contact setPhones(List<Phone> phones) {
        this.phones = phones;
        return this;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public Contact setEmails(List<Email> emails) {
        this.emails = emails;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Contact setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStatusId() {
        return statusId;
    }

    public Contact setStatusId(String statusId) {
        this.statusId = statusId;
        return this;
    }

    public Boolean isStarred() {
        return starred;
    }

    public Contact setStarred(Boolean starred) {
        this.starred = starred;
        return this;
    }

    public String getLeadSourceId() {
        return leadSourceId;
    }

    public Contact setLeadSourceId(String leadSourceId) {
        this.leadSourceId = leadSourceId;
        return this;
    }

    public Boolean hasPendingDeal() {
        return hasPendingDeal;
    }

    public Contact setHasPendingDeal(Boolean hasPendingDeal) {
        this.hasPendingDeal = hasPendingDeal;
        return this;
    }

    public Double getTotalPending() {
        return totalPending;
    }

    public Contact setTotalPending(Double totalPending) {
        this.totalPending = totalPending;
        return this;
    }

    public String getType() {
        return type;
    }

    public Contact setType(String type) {
        this.type = type;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Contact setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public Contact setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Contact setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public Contact setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Contact setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Contact setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Contact setAddress(Address address) {
        this.address = address;
        return this;
    }

    public List<Action> getActions() {
        return actions;
    }

    public Contact setActions(List<Action> actions) {
        this.actions = actions;
        return this;
    }

    public Action getNextAction() {
        return nextAction;
    }

    public Contact setNextAction(Action nextAction) {
        this.nextAction = nextAction;
        return this;
    }
}
