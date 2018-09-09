package com.aapps.wikisearch.web.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import com.aapps.wikisearch.R;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {

    public static String WEB_URL = "WEB_URL";
    public static String WEB_TITLE = "WEB_TITLE";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.webview_title)
    TextView mTitle;

    @BindView(R.id.webview)
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initWebView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initWebView() {
        String title = getIntent().getStringExtra(WEB_TITLE);
        String url = getIntent().getStringExtra(WEB_URL);

        mTitle.setText(title);

        mWebView.loadUrl(url);
    }
}
