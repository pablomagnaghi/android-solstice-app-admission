package com.contacts.view;

import com.contacts.model.Contact;

public interface ContactDetailsMvpView extends MvpView {
    void showContactDetails(Contact contact);
    void showFavorite();
}
