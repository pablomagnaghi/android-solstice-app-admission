package com.contacts;

import android.app.Application;

import com.contacts.injection.components.ApplicationComponent;
import com.contacts.injection.components.DaggerApplicationComponent;
import com.contacts.injection.modules.ApplicationModule;

public class ContactsApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}