package com.contacts.injection.components;

import com.contacts.injection.modules.ApplicationModule;
import com.contacts.ui.activities.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
