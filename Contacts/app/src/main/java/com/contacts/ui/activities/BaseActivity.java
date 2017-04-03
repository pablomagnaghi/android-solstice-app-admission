package com.contacts.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.contacts.ContactsApplication;
import com.contacts.injection.components.ApplicationComponent;

public class BaseActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((ContactsApplication) getApplication()).getApplicationComponent();
    }
}