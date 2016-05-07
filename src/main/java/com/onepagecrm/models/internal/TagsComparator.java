package com.onepagecrm.models.internal;

import com.onepagecrm.models.Tag;

import java.util.Comparator;

public class TagsComparator implements Comparator<Tag> {

    /**
     * Method which will allow Tags to be alphabetically sorted.
     *
     * @param tag1 - Tag
     * @param tag2 - Tag
     * @return
     */
    @Override
    public int compare(final Tag tag1, final Tag tag2) {
        if (tag1.getName() != null && tag2.getName() != null) {
            String name1 = tag1.getName().toLowerCase();
            String name2 = tag2.getName().toLowerCase();
            return name1.compareTo(name2);
        } else {
            return 0;
        }
    }
}
