package com.contacts.presenter;

import android.os.Bundle;
import android.os.Parcelable;

import com.contacts.model.Contact;
import com.contacts.rest.ApiClient;
import com.contacts.rest.ApiInterface;
import com.contacts.utils.Constants;
import com.contacts.view.MainMvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainMvpView> {

    ApiClient mApiClient;
    List<Contact> mContacts;

    @Inject
    public MainPresenter(ApiClient apiClient) {
        mApiClient = apiClient;
        mContacts = new ArrayList<Contact>();
    }

    public void restoreState(Bundle savedInstanceState) {
        checkViewAttached();

        if (savedInstanceState != null) {
            mContacts = savedInstanceState.getParcelableArrayList(Constants.CONTACTS);
            getMvpView().showContacts(mContacts);
        } else {
            loadContactsApi();
        }
    }

    public void loadContactsApi() {
        Observable<List<Contact>> contactsObservable = mApiClient.getClient().create(ApiInterface.class).getContacts();

        addSubscription(contactsObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((List<Contact> contacts) -> showContacts(contacts),
                        (error) -> getMvpView().showError(),
                        () -> {}));
    }

    private void showContacts(List<Contact> contacts) {
        mContacts = contacts;
        getMvpView().showContacts(contacts);
    }

    public void saveState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList(Constants.CONTACTS, (ArrayList<? extends Parcelable>) mContacts);
    }
}
