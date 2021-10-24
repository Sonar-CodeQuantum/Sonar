package com.example.sonar.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Friend")
public class Friend extends ParseObject {
    // keys from post class in database
    public static final String KEY_USER = "user";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "mobile";
    public static final String KEY_PHOTO = "photo";

    public Friend() {
    }

    public Friend(String name, String number) {
        setName(name);
        setNumber(number);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser parseUser) {
        put(KEY_USER, parseUser);
    }

    public String getName() {
        return getString(KEY_NAME);
    }
    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getNumber() {
        return getString(KEY_NUMBER);
    }

    public void setNumber(String number) {
        put(KEY_NAME, number);
    }

    public ParseFile getPhoto() {
        return getParseFile(KEY_PHOTO);
    }
    public void setPhoto(ParseFile parseFile) {
        put(KEY_PHOTO, parseFile);
    }

}
