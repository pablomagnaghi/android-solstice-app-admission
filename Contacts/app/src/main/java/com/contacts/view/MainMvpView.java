package com.contacts.view;

import com.contacts.model.Contact;

import java.util.List;

public interface MainMvpView extends MvpView {
    void showContacts(List<Contact> contacts);
}

