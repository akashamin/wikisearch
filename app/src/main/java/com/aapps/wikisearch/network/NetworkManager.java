package com.aapps.wikisearch.network;

import com.aapps.wikisearch.network.api.SearchAPI;
import com.aapps.wikisearch.search.model.Search;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    Retrofit mRetrofit = null;

    public NetworkManager(String baseURL) {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseURL)
                    .build();
        }
    }

    public Observable<Search> getSearchResults(HashMap<String, String> params) {
        return mRetrofit.create(SearchAPI.class).getSearchResultsAPI(params);
    }
}
