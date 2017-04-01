package com.contacts.injection.modules;

import android.app.Application;

import com.contacts.rest.ApiClient;
import com.contacts.ui.adapters.ContactsAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    ApiClient provideApiClient() {
        return new ApiClient();
    }

    @Provides
    @Singleton
    ContactsAdapter provideContactsAdapter() {
        return new ContactsAdapter(mApplication.getApplicationContext());
    }
}
