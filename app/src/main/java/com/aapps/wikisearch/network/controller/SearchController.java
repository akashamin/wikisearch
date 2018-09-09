package com.aapps.wikisearch.network.controller;

import com.aapps.wikisearch.network.NetworkManager;
import com.aapps.wikisearch.search.model.Error;
import com.aapps.wikisearch.search.model.Search;
import com.aapps.wikisearch.util.GlobalBus;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;


public class SearchController {

    NetworkManager mNetworkManager;
    String baseURL = "https://en.wikipedia.org//w/";


    public SearchController() {
        mNetworkManager = new NetworkManager(baseURL);
    }

    public void getSearchResults(String searchString) {
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "query");
        params.put("formatversion", "2");
        params.put("generator", "prefixsearch");
        params.put("gpssearch", searchString);
        params.put("gpslimit", "10");
        params.put("prop", "pageimages|pageterms");
        params.put("piprop", "thumbnail");
        params.put("pithumbsize", "50");
        params.put("pilimit", "10");
        params.put("wbptterms", "description");
        params.put("format", "json");

        Observable<Search> mSearchObservable = mNetworkManager.getSearchResults(params);

        mSearchObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Search>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Search value) {
                        if (value != null && value.getQuery() != null) {
                            GlobalBus.getBus().post(value);
                        } else {
                            Error mError = new Error();
                            mError.setMessage("No results found!");
                            GlobalBus.getBus().post(mError);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Error mError = new Error();
                        if (e instanceof IOException) {
                            mError.setMessage("Network Error!");
                        } else {
                            mError.setMessage("Something is not right! Please try again later!");
                        }
                        GlobalBus.getBus().post(mError);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
