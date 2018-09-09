package com.aapps.wikisearch.search.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aapps.wikisearch.R;
import com.aapps.wikisearch.database.models.RealmPage;
import com.aapps.wikisearch.search.model.Pages;
import com.aapps.wikisearch.search.model.Search;
import com.aapps.wikisearch.search.presenter.SearchPresenter;
import com.aapps.wikisearch.search.view.adapter.SavedSearchAdapter;
import com.aapps.wikisearch.search.view.adapter.SearchAdapter;
import com.aapps.wikisearch.web.view.WebviewActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements ISearchView, SearchAdapter.SearchAdapterListener, SavedSearchAdapter.SavedSearchAdapterListener {

    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @BindView(R.id.edt_search)
    EditText mSearch;

    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBar;

    @BindView(R.id.search_results_recycler_view)
    RecyclerView mSearchRecyclerView;

    @BindView(R.id.main_layout)
    CoordinatorLayout mMainLayout;

    @BindView(R.id.search_result_title)
    TextView mSearchResultTitle;

    @BindView(R.id.recent_search_result_title)
    TextView mRecentSearchResultTitle;

    @BindView(R.id.recent_search_results_recycler_view)
    RecyclerView mRecentSearchRecyclerView;

    SearchPresenter mSearchPresenter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.LayoutManager mLayoutManager1;
    SearchAdapter mSearchAdapter;
    SavedSearchAdapter mSavedSearchAdapter;
    List<Pages> mSearchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
        initPresenter();
        if (!checkNetworkStatus()) {
            showError("Network Error!");
        }
    }

    protected boolean checkNetworkStatus() {
        ConnectivityManager mConectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null && mNetworkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Call API on text changed of search edit text
     */
    @OnTextChanged(R.id.edt_search)
    public void onSearchTextChanged() {
        String searchText = mSearch.getText().toString().trim();
        if (searchText.length() > 0) {
            mSearchPresenter.getSearchResults(searchText);
        } else {
            mSearchRecyclerView.setVisibility(View.GONE);
            mSearchResultTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mSearchPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * fetch saved search from db
         */
        mSearchPresenter.fetchSavedSearchFromDB();
        mSearch.setText("");
    }

    private void initUI() {
        /**
         * Init Imageloader
         */
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(config);
        /**
         * Init Search Recycler view
         */
        mLayoutManager = new LinearLayoutManager(this);
        mSearchRecyclerView.setLayoutManager(mLayoutManager);
        mSearchRecyclerView.setHasFixedSize(true);
        mSearchAdapter = new SearchAdapter(this, mSearchList, this);
        mSearchRecyclerView.setAdapter(mSearchAdapter);
        mSearchRecyclerView.setNestedScrollingEnabled(false);

        /**
         * Init saved search recycler view
         */
        mLayoutManager1 = new LinearLayoutManager(this);
        mRecentSearchRecyclerView.setLayoutManager(mLayoutManager1);
        mRecentSearchRecyclerView.setHasFixedSize(true);
        mRecentSearchRecyclerView.setNestedScrollingEnabled(false);
    }


    private void initPresenter() {
        mSearchPresenter = new SearchPresenter(this);
        mSearchPresenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSearchPresenter != null)
            mSearchPresenter.stop();
    }

    @OnClick(R.id.edt_search)
    public void onSearchClicked() {
        mAppBar.setExpanded(false);
    }

    @Override
    public void updateSearchResults(List<Pages> mPages) {
        if (mPages.size() > 0) {
            mSearchAdapter.updateSearchList(mPages);
            mSearchRecyclerView.setVisibility(View.VISIBLE);
            mSearchResultTitle.setVisibility(View.VISIBLE);
        } else {
            mSearchRecyclerView.setVisibility(View.GONE);
            mSearchResultTitle.setVisibility(View.GONE);
        }

    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar.make(mMainLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
        mSearchRecyclerView.setVisibility(View.GONE);
        mSearchResultTitle.setVisibility(View.GONE);
    }

    @Override
    public void showSavedSearches(RealmResults<RealmPage> mRealmPageRealmResults) {

        if (mRealmPageRealmResults.size() > 0) {
            mSavedSearchAdapter = new SavedSearchAdapter(mRealmPageRealmResults, true);
            mSavedSearchAdapter.setSearchAdapterListener(this);
            mRecentSearchRecyclerView.setAdapter(mSavedSearchAdapter);
            mRecentSearchRecyclerView.setVisibility(View.VISIBLE);
            mRecentSearchResultTitle.setVisibility(View.VISIBLE);
        } else {
            mRecentSearchRecyclerView.setVisibility(View.GONE);
            mRecentSearchResultTitle.setVisibility(View.GONE);
        }

    }

    @Override
    public void onSearchItemClicked(Pages pages) {
        mSearchPresenter.onSearchItemClicked(pages);
        /**
         * Show webview for the item clicked
         */
        initWebView(pages.getTitle(), pages.getPageid());
    }

    @Override
    public void onSearchItemClicked(RealmPage pages) {
        /**
         * Show webview for the item clicked
         */
        initWebView(pages.getTitle(), pages.getPageid());
    }

    private void initWebView(String pageTitle, int pageId) {
        Intent webviewIntent = new Intent(this, WebviewActivity.class);
        webviewIntent.putExtra(WebviewActivity.WEB_TITLE, pageTitle);
        webviewIntent.putExtra(WebviewActivity.WEB_URL, "https://en.wikipedia.org/?curid=" + pageId);
        if (checkNetworkStatus())
            startActivity(webviewIntent);
        else
            showError("Network Error!");

    }
}
