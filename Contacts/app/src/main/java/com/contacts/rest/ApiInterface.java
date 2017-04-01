package com.contacts.rest;

import com.contacts.model.Contact;
import com.contacts.utils.Constants;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    @GET(Constants.API_GET_CONTACTS)
    Observable<List<Contact>> getContacts();
}