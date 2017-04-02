package com.contacts.presenter;

import android.content.Intent;

import com.contacts.model.Contact;
import com.contacts.utils.Constants;
import com.contacts.view.ContactDetailsMvpView;

import javax.inject.Inject;

public class ContactDetailsPresenter extends BasePresenter<ContactDetailsMvpView> {

    Contact mContact;

    @Inject
    public ContactDetailsPresenter() {}

    public void loadContactDetails(Intent intent) {
        checkViewAttached();

        mContact = (Contact) intent.getParcelableExtra(Constants.CONTACT_INTENT);

        if (mContact != null) {
            getMvpView().showContactDetails(mContact);
        }
        else {
            getMvpView().showError();
        }
    }

    public void loadFavorite() {
        if (mContact.isFavorite()) {
            getMvpView().showFavorite();
        }
    }
}
