package com.aapps.wikisearch.network.api;

import com.aapps.wikisearch.search.model.Search;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface SearchAPI {
    @GET("api.php?")
    Observable<Search> getSearchResultsAPI(@QueryMap Map<String, String> params);
}
