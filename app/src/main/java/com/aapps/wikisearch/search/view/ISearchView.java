package com.aapps.wikisearch.search.view;

import com.aapps.wikisearch.database.models.RealmPage;
import com.aapps.wikisearch.search.model.Pages;

import java.util.List;

import io.realm.RealmResults;

public interface ISearchView {
    void updateSearchResults(List<Pages> mPages);

    void showError(String message);

    void showSavedSearches(RealmResults<RealmPage> mRealmPageRealmResults);
}
