package com.example.sonar;

import android.app.Application;

import com.example.sonar.models.Friend;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Friend.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("40jyJWRMiA9stxrXHt4m5jbO4Rnbt1eG2E49WkML")
                .clientKey("cxXjVsLl5cr7QgNRyWKvpNSITmPMuCJ8QG9fqK2y")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
