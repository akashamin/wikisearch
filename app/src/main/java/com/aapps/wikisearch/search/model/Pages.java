package com.aapps.wikisearch.search.model;

import com.google.gson.annotations.SerializedName;

public class Pages {
    @SerializedName("pageid")
    private int pageid;

    @SerializedName("ns")
    private int ns;

    @SerializedName("title")
    private String title;

    @SerializedName("index")
    private int index;

    @SerializedName("thumbnail")
    private Thumbnail thumbnail;

    @SerializedName("terms")
    private Terms terms;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getNs() {
        return ns;
    }

    public void setNs(int ns) {
        this.ns = ns;
    }

    public int getPageid() {
        return pageid;
    }

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
