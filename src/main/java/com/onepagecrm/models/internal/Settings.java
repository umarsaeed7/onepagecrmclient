package com.onepagecrm.models.internal;

import java.io.Serializable;
import java.util.List;

public class Settings implements Serializable {

    private Reminder reminder;
    private String timeZone; // *****************
    private String dateFormat; // *****************
    private int listingSize;
    private String currency; // *****************
    private String currencySymbol;
    private List<Country> popularCountries; // *****************
    private List<DealStage> dealStages;
    private String defaultView; // *****************
    private boolean showTidyStream; // *****************
}