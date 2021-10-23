package com.example.sonar;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("40jyJWRMiA9stxrXHt4m5jbO4Rnbt1eG2E49WkML")
                .clientKey("cxXjVsLl5cr7QgNRyWKvpNSITmPMuCJ8QG9fqK2y")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
