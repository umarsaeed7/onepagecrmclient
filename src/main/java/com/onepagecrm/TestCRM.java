/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onepagecrm;

import com.onepagecrm.exceptions.OnePageException;
import com.onepagecrm.models.ContactList;
import com.onepagecrm.models.serializer.ContactListSerializer;
import com.onepagecrm.net.request.Request;
import org.json.JSONObject;

/**
 *
 * @author umarsaeed
 */
public class TestCRM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Request.SERVER = Request.APP_SERVER;

            com.onepagecrm.models.User loggedInUser = com.onepagecrm.models.User.login("username", "password");

            System.out.print("Logged in User : " + loggedInUser);

            ContactList stream = loggedInUser.actionStream();

            String serialized = ContactListSerializer.toJsonObject(stream);

            ContactList serializedAndParsed = ContactListSerializer.fromJsonObject(
                    new JSONObject(serialized)
            );

            System.out.print("stream : " + stream);
            System.out.print("stream.getPaginator() : " + stream.getPaginator());
            System.out.print("serializedAndParsed.getPaginator() : " + serializedAndParsed.getPaginator());
        } catch (OnePageException ex) {
            ex.printStackTrace();
        }
    }

}
