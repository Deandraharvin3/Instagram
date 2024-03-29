package com.example.instagram.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;


@ParseClassName("User")
public class User extends ParseObject {
    public static final String KEY_USERNAME = "username";

    public String getUsername() {
        return getString(KEY_USERNAME);
    }
    public void setUsername(String username) {
        put(KEY_USERNAME, username);
    }

}
