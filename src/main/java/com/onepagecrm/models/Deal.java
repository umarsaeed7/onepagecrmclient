package com.onepagecrm.models;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.serializer.DealSerializer;
import com.onepagecrm.net.ApiResource;
import com.onepagecrm.net.Response;
import com.onepagecrm.net.request.*;

import java.io.Serializable;
import java.util.Date;

public class Deal extends ApiResource implements Serializable {

    private String id;
    private Double amount;
    private String author;
    private String text;
//    private ContactInfo contactInfo;
    private String contactId;
    private Date createdAt;
    private Date date;
    private Date expectedCloseDate;
    private Integer months;
    private String name;
    private String ownerId;
    private Integer stage;
    private String status;
    private Double totalAmount;
    private Date modifiedAt;
    private Boolean hasRelatedNotes;
    private Date closeDate;
//    private List<Attachment> attachments;

    public Deal save() throws OnePageException {
        return this.isValid() ? update() : create();
    }

    private Deal create() throws OnePageException {
        Request request = new PostRequest(
                DEALS_ENDPOINT,
                null,
                DealSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return DealSerializer.fromString(response.getResponseBody());
    }

    private Deal update() throws OnePageException {
        Request request = new PutRequest(
                addDealIdToEndpoint(DEALS_ENDPOINT),
                null,
                DealSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return DealSerializer.fromString(response.getResponseBody());
    }

    public Deal partial() throws OnePageException {
        Request request = new PatchRequest(
                addDealIdToEndpoint(DEALS_ENDPOINT),
                null,
                DealSerializer.toJsonObject(this)
        );
        Response response = request.send();
        return DealSerializer.fromString(response.getResponseBody());
    }

    public void delete() throws OnePageException {
        Request request = new DeleteRequest(addDealIdToEndpoint(DEALS_ENDPOINT));
        Response response = request.send();
//        return DealSerializer.fromStringDelete(response.getResponseBody());
    }

    private String addDealIdToEndpoint(String endpoint) {
        return endpoint + "/" + this.id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Deal setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return DealSerializer.toJsonObject(this);
    }

    public Double getAmount() {
        return amount;
    }

    public Deal setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Deal setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getText() {
        return text;
    }

    public Deal setText(String text) {
        this.text = text;
        return this;
    }

    public String getContactId() {
        return contactId;
    }

    public Deal setContactId(String contactId) {
        this.contactId = contactId;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Deal setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Deal setDate(Date date) {
        this.date = date;
        return this;
    }

    public Date getExpectedCloseDate() {
        return expectedCloseDate;
    }

    public Deal setExpectedCloseDate(Date expectedCloseDate) {
        this.expectedCloseDate = expectedCloseDate;
        return this;
    }

    public Integer getMonths() {
        return months;
    }

    public Deal setMonths(Integer months) {
        this.months = months;
        return this;
    }

    public String getName() {
        return name;
    }

    public Deal setName(String name) {
        this.name = name;
        return this;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Deal setOwnerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public Integer getStage() {
        return stage;
    }

    public Deal setStage(Integer stage) {
        this.stage = stage;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Deal setStatus(String status) {
        this.status = status;
        return this;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Deal setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Deal setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public Boolean getHasRelatedNotes() {
        return hasRelatedNotes;
    }

    public Deal setHasRelatedNotes(Boolean hasRelatedNotes) {
        this.hasRelatedNotes = hasRelatedNotes;
        return this;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public Deal setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
        return this;
    }
}
