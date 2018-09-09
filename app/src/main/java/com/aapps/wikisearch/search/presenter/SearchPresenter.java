package com.aapps.wikisearch.search.presenter;

import com.aapps.wikisearch.database.RealmSearchController;
import com.aapps.wikisearch.database.models.RealmPage;
import com.aapps.wikisearch.network.controller.SearchController;
import com.aapps.wikisearch.search.model.Error;
import com.aapps.wikisearch.search.model.Pages;
import com.aapps.wikisearch.search.model.Search;
import com.aapps.wikisearch.search.view.ISearchView;
import com.aapps.wikisearch.util.GlobalBus;
import com.squareup.otto.Subscribe;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;

public class SearchPresenter {

    private ISearchView mSearchView;
    private SearchController mSearchController;
    private boolean isBusRegistered = false;
    private RealmSearchController mRealmSearchController;

    public SearchPresenter(ISearchView mSearchView) {
        this.mSearchView = mSearchView;
        mSearchController = new SearchController();
        mRealmSearchController = new RealmSearchController();
    }

    public void start() {
        if (!isBusRegistered) {
            GlobalBus.getBus().register(this);
            isBusRegistered = true;
        }
    }

    public void stop() {
        if (isBusRegistered) {
            GlobalBus.getBus().unregister(this);
            isBusRegistered = false;
        }
    }

    @Subscribe
    public void onSearchResultsReceived(Search mSearch) {
        if (mSearch != null && mSearch.getQuery() != null) {
            List<Pages> mPages = mSearch.getQuery().getPages();
            mSearchView.updateSearchResults(mPages);
        }
    }

    @Subscribe
    public void onErrorOccurred(Error mError) {
        mSearchView.showError(mError.getMessage());
    }


    public void getSearchResults(String searchQuery) {
        mSearchController.getSearchResults(searchQuery);
    }

    public void onSearchItemClicked(Pages pages) {
        mRealmSearchController.saveSearchResult(pages);
    }

    public void fetchSavedSearchFromDB() {
        RealmResults<RealmPage> mRealmPageRealmResults = mRealmSearchController.getSavedSearch();
        mSearchView.showSavedSearches(mRealmPageRealmResults);
    }
}
