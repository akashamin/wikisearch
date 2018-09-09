package com.aapps.wikisearch.search.model;

import com.google.gson.annotations.SerializedName;

public class Thumbnail {
    @SerializedName("source")
    private String source;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
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
