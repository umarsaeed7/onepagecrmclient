package com.onepagecrm.models;

import com.onepagecrm.exceptions.InvalidListingTypeException;
import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.internal.Paginator;
import com.onepagecrm.models.serializer.ContactListSerializer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ContactList extends ArrayList<Contact> implements Serializable {

    private static final Logger LOG = Logger.getLogger(ContactList.class.getName());

    private static final long serialVersionUID = 8185938052776557364L;

    public static final int AS_LISTING = 1219;
    public static final int AZ_LISTING = 8662;

    private int type;

    private List<Contact> contacts;
    private Paginator paginator;

    public ContactList nextPage() throws OnePageException {
        this.paginator.getNextPageNo();
        switch (type) {
            case AS_LISTING:
                return Account.loggedInUser.actionStream(paginator);
            case AZ_LISTING:
                return Account.loggedInUser.contacts(paginator);
            default:
                throw new InvalidListingTypeException("Not a supported contact listing type.")
                        .setErrorMessage("Not a supported contact listing type.");
        }
    }

    public ContactList refresh() throws OnePageException {
        ContactList list = new ContactList();
        switch (type) {
            case AS_LISTING:
                list = Account.loggedInUser.actionStream();
                break;
            case AZ_LISTING:
                list = Account.loggedInUser.contacts();
                break;
        }
        this.setContacts(list);
        return this;
    }

    public ContactList(List<Contact> contacts, int type) {
        this.type = type;
        new ContactList(contacts);
    }

    public ContactList(int type) {
        this.type = type;
        new ContactList();
    }

    public ContactList setType(int type) {
        this.type = type;
        return this;
    }

    public int getType() {
        return type;
    }

    public ContactList(List<Contact> contacts) {
        this.contacts = new ArrayList<>();
        this.paginator = new Paginator();
        if (contacts != null && !contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                this.contacts.add(contacts.get(i));
            }
        }
    }

    public ContactList() {
        this.contacts = new ArrayList<>();
        this.paginator = new Paginator();
    }

    public ContactList addNextPage(ContactList contactsList) {
        if (this.contacts != null && !this.contacts.isEmpty()) {
            List<Contact> contacts = contactsList.getContacts();
            if (contacts != null && !contacts.isEmpty()) {
                for (Contact contact : contacts) {
                    this.contacts.add(contact);
                }
            }
        }
        return this;
    }

    public String toString() {
        return ContactListSerializer.toJsonObject(this);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = new ArrayList<>();
        if (contacts != null && !contacts.isEmpty()) {
            for (int i = 0; i < contacts.size(); i++) {
                this.contacts.add(contacts.get(i));
            }
        }
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public ContactList setPaginator(Paginator paginator) {
        this.paginator = paginator;
        return this;
    }

    public boolean isEmpty() {
        return contacts.isEmpty();
    }

    public int size() {
        return contacts.size();
    }

    public void add(int index, Contact contact) {
        contacts.add(index, contact);
    }

    public boolean add(Contact contact) {
        return contacts.add(contact);
    }

    public Contact get(int index) {
        return contacts.get(index);
    }

    public Contact set(int index, Contact contact) {
        contacts.set(index, contact);
        return contact;
    }

    public int indexOf(Contact contact) {
        return contacts.indexOf(contact);
    }

    /**
     * Determines mContacts which are phone-able i.e. have at least 1 phone
     * number.
     * <p/>
     * Takes a list of mContacts. Returns a list of Contacts which have phone
     * numbers.
     *
     * @return
     */
    public ContactList getPhoneableContacts() {
        ArrayList<Contact> phoneableContacts = new ArrayList<>();
        if (contacts != null && !contacts.isEmpty()) {
            int counter = 0;
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getPhones() != null && !contacts.get(i).getPhones().isEmpty()) {
                    contacts.get(i).setIntId(counter + 1);
                    phoneableContacts.add(contacts.get(i));
                    counter++;
                }
            }
        }
        return new ContactList(phoneableContacts);
    }

    /**
     * Determines mContacts which STARRED contacts are phone-able i.e. have at
     * least 1 phone number.
     * <p/>
     * Takes a list of mContacts. Returns a list of Contacts which have phone
     * numbers.
     *
     * @return
     */
    public ContactList getStarredPhoneableContacts() {
        ArrayList<Contact> phoneableContacts = new ArrayList<>();
        if (contacts != null && !contacts.isEmpty()) {
            int counter = 0;
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).isStarred()) {
                    if (contacts.get(i).getPhones() != null && !contacts.get(i).getPhones().isEmpty()) {
                        contacts.get(i).setIntId(counter + 1);
                        phoneableContacts.add(contacts.get(i));
                        counter++;
                    }
                }
            }
        }
        return new ContactList(phoneableContacts);
    }

    /**
     * Checks if a phone number exists in action stream.
     * <p/>
     * Takes a list of mContacts Returns the Contact object if the phone numbers
     * match.
     *
     * @param incomingNumber
     * @return
     */
    public Contact inActionStream(String incomingNumber) {
        for (int i = 0; i < contacts.size(); i++) {
            List<Phone> phones = contacts.get(i).getPhones();
            if (phones != null && !phones.isEmpty()) {
                for (int j = 0; j < contacts.get(i).getPhones().size(); j++) {
                    if (incomingNumber.equals(contacts.get(i).getPhones().get(j).getValue())) {
                        return contacts.get(i);
                    }
                }
            }
        }
        return new Contact();
    }

    /**
     * Simply gets the nextPage Contact in the list.
     * <p/>
     * If at end of list, jumps back to the start.
     *
     * @param position
     * @return
     */
    public Contact getNextContact(int position) {
        if (contacts != null && !contacts.isEmpty()) {
            int length = contacts.size();
            int newPosition;
            if (position < (length - 1)) {
                newPosition = position + 1;
            } else {
                newPosition = 0;
            }
            return contacts.get(newPosition);
        } else {
            return new Contact();
        }
    }

    /**
     * Simply gets the previous contact in the list.
     * <p/>
     * If at the start, jumps back to the end.
     *
     * @param position
     * @return
     */
    public Contact getPreviousContact(int position) {
        if (contacts != null && !contacts.isEmpty()) {
            int length = contacts.size();
            int newPosition;
            if (position > 0) {
                newPosition = position - 1;
            } else {
                newPosition = length - 1;
            }
            return contacts.get(newPosition);
        } else {
            return new Contact();
        }
    }

    /**
     * Get the array index of the given contact.
     *
     * @param contact
     * @return
     */
    public int getArrayPosition(Contact contact) {
        int position = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contact.getId().equals(contacts.get(i).getId())) {
                position = i;
            }
        }
        return position;
    }
}
