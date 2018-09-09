package com.aapps.wikisearch.search.model;

import com.google.gson.annotations.SerializedName;

public class Continue {
    @SerializedName("gpsoffset")
    private String gpsoffset;

    @SerializedName("continue")
    private String strContinue;

    public String getGpsoffset() {
        return gpsoffset;
    }

    public void setGpsoffset(String gpsoffset) {
        this.gpsoffset = gpsoffset;
    }

    public String getStrContinue() {
        return strContinue;
    }

    public void setStrContinue(String strContinue) {
        this.strContinue = strContinue;
    }
}
