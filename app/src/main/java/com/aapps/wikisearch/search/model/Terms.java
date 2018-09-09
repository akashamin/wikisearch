package com.aapps.wikisearch.search.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Terms {

    @SerializedName("description")
    private List<String> description;

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}
