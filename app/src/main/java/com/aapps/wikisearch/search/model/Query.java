package com.aapps.wikisearch.search.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Query {
    @SerializedName("pages")
    private List<Pages> pages;

    public List<Pages> getPages() {
        return pages;
    }

    public void setPages(List<Pages> pages) {
        this.pages = pages;
    }
}
