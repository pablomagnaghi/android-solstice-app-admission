package com.contacts.presenter;

import android.os.Bundle;

import com.contacts.model.Contact;
import com.contacts.rest.ApiClient;
import com.contacts.rest.ApiInterface;
import com.contacts.utils.Constants;
import com.contacts.view.MainMvpView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class MainPresenter extends BasePresenter<MainMvpView> {

    ApiClient mApiClient;
    List<Contact> mContacts;

    @Inject
    public MainPresenter(ApiClient apiClient) {
        mApiClient = apiClient;
    }

    public void loadContacts(Bundle savedInstanceState) {
        checkViewAttached();

        if (savedInstanceState != null) {
            mContacts = savedInstanceState.getParcelableArrayList(Constants.CONTACTS);
        }

        if (mContacts != null) {
            if (mContacts.isEmpty()) {
                getMvpView().showContactsEmpty();
            } else {
                getMvpView().showContacts(mContacts);
            }
        } else {
            Observable<List<Contact>> contactsObservable = mApiClient.getClient().create(ApiInterface.class).getContacts();

            addSubscription(contactsObservable, new Subscriber<List<Contact>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if (isViewAttached()) {
                        getMvpView().showError();
                    }
                }

                @Override
                public void onNext(List<Contact> contacts) {
                    if (contacts.isEmpty()) {
                        getMvpView().showContactsEmpty();
                    } else {
                        getMvpView().showContacts(contacts);
                    }

                }
            });
        }
    }
}

