package com.aapps.wikisearch.search.model;

import com.google.gson.annotations.SerializedName;

public class Search {
    @SerializedName("batchcomplete")
    private String batchcomplete;

    @SerializedName("continue")
    private Continue objContinue;

    @SerializedName("query")
    private Query query;

    public String getBatchcomplete() {
        return batchcomplete;
    }

    public void setBatchcomplete(String batchcomplete) {
        this.batchcomplete = batchcomplete;
    }

    public Continue getObjContinue() {
        return objContinue;
    }

    public void setObjContinue(Continue objContinue) {
        this.objContinue = objContinue;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
