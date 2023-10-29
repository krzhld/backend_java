package edu.hw3.ContactProblem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ContactList {
    private ContactList() {
    }

    private static final String ASCENDING_ORDER = "ASC";
    private static final String DESCENDING_ORDER = "DESC";

    public static List<Contact> parseContacts(String[] contactStrings, String sortOrder) {
        if (contactStrings == null || contactStrings.length == 0) {
            return List.of();
        }
        return Arrays
            .stream(contactStrings)
            .map(ContactList::parseContact)
            .sorted(getParseSortOrder(sortOrder))
            .toList();
    }

    private static Contact parseContact(String contactString) {
        if (contactString.isEmpty()) {
            throw new IllegalArgumentException("Contact must not be empty!");
        }
        String[] fullName = contactString.split("\\s+");
        String name = null;
        String surname = null;
        if (fullName.length > 0) {
            name = fullName[0];
        }
        if (fullName.length > 1) {
            surname = fullName[1];
        }
        return new Contact(name, surname);
    }

    private static Comparator<Contact> getParseSortOrder(String sortOrder) {
        if (sortOrder.equals(ASCENDING_ORDER)) {
            return Comparator.naturalOrder();
        } else if (sortOrder.equals(DESCENDING_ORDER)) {
            return Comparator.reverseOrder();
        }
        throw new IllegalArgumentException("Invalid sort order!");
    }
}
