package com.onepagecrm.models;

import com.onepagecrm.models.internal.Paginator;

public class Query {

    public static String paginatorToString(Paginator paginator) {
        return "?page=" + paginator.getCurrentPage() +
                "&per_page=" + paginator.getPerPage();
    }

    public static String perPageQueryString(int number) {
        return "?per_page=" + number;
    }

    public static String teamValueQueryString(boolean value) {
        return "?team=" + value;
    }

    public static String contactIdQueryString(String contactId) {
        return "?contact_id=" + contactId;
    }

}
