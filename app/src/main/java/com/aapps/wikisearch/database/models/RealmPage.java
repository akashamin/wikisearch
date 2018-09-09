package com.aapps.wikisearch.database.models;

import com.aapps.wikisearch.search.model.Terms;
import com.aapps.wikisearch.search.model.Thumbnail;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class RealmPage extends RealmObject {

    @PrimaryKey
    private int pageid;

    private int ns;

    private String title;

    private int index;

    private RealmThumbnail thumbnail;

    private RealmTerms terms;

    private Date updatedDate;

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

    public RealmTerms getTerms() {
        return terms;
    }

    public void setTerms(RealmTerms terms) {
        this.terms = terms;
    }

    public RealmThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(RealmThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
