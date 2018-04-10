package com.ourblog.android;

/**
 * Created by Chloe on 4/10/2018.
 */

public class Article {
    public String title = "chloetesting title";
    public String content = "chloe testing content";
    public String tag ="";

    public String currentUser;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }



    public Article() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

}
