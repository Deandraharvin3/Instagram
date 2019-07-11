package com.example.instagram.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_DATE = "createdAt";

//    public Post() {
//        super();
//    }
//    public Post(String body) {
//        super();
//    }
    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }
    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }
    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }
    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }
    public ParseFile getMedia() {
        return getParseFile("image");
    }
    public void setMedia(ParseFile parseFile) {
        put("image", parseFile);
    }
    public Date getCreatedAt(){
        return getDate(KEY_DATE);
    }
    public void setCreatedAt(Date createdAt) {
        put(KEY_DATE, createdAt);
    }

    public static class Query extends ParseQuery<Post> {
        public Query() {
            super(Post.class);
        }
        public Query getTop () {
            setLimit(20);
            return this;
        }
        public Query withUser() {
            include("user");
            return this;
        }
    }
}
