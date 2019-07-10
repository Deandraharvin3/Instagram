package com.example.instagram;

import android.app.Application;

import com.example.instagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("deandraharvin3")
                .clientKey("Dajah312!")
                .server("http://deandraharvin3-instagram.herokuapp.com/parse")
                .build();
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(configuration);

    }
}
