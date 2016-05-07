package com.onepagecrm.models;

import com.onepagecrm.models.internal.ContactsCount;
import com.onepagecrm.models.internal.Settings;
import com.onepagecrm.models.internal.TeamStream;

import java.io.Serializable;
import java.util.List;

public class Account implements Serializable {

    public static List<User> team;
    public static User loggedInUser;
    public static Settings settings;
    public Countries countries;

    public List<CustomField> customFields;
    public List<Filter> filters;
    public List<CallResult> callResults;
    public List<Tag> tags;
    public List<Status> statuses;
    public List<LeadSource> leadSources;

    public TeamStream teamStream;
    public ContactsCount contactsCount;

    // public static User switchUser(login, password) {

    public Account() {
    }

    public static List<User> getTeam() {
        return team;
    }

    public Account setTeam(List<User> team) {
        Account.team = team;
        return this;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public Account setLoggedInUser(User loggedInUser) {
        Account.loggedInUser = loggedInUser;
        return this;
    }

    public static Settings getSettings() {
        return settings;
    }

    public Account setSettings(Settings settings) {
        Account.settings = settings;
        return this;
    }

    public Countries getCountries() {
        return countries;
    }

    public Account setCountries(Countries countries) {
        this.countries = countries;
        return this;
    }

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public Account setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
        return this;
    }

    public List<CallResult> getCallResults() {
        return callResults;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public Account setFilters(List<Filter> filters) {
        this.filters = filters;
        return this;
    }

    public Account setCallResults(List<CallResult> callResults) {
        this.callResults = callResults;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Account setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public Account setStatuses(List<Status> statuses) {
        this.statuses = statuses;
        return this;
    }

    public List<LeadSource> getLeadSources() {
        return leadSources;
    }

    public Account setLeadSources(List<LeadSource> leadSources) {
        this.leadSources = leadSources;
        return this;
    }

    public TeamStream getTeamStream() {
        return teamStream;
    }

    public Account setTeamStream(TeamStream teamStream) {
        this.teamStream = teamStream;
        return this;
    }

    public ContactsCount getContactsCount() {
        return contactsCount;
    }

    public Account setContactsCount(ContactsCount contactsCount) {
        this.contactsCount = contactsCount;
        return this;
    }
}
