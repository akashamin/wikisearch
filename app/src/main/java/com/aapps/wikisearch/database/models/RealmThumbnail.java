package com.aapps.wikisearch.database.models;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

@RealmClass
public class RealmThumbnail extends RealmObject {
    private String source;

    private int width;

    private String height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
