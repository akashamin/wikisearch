package com.aapps.wikisearch.search.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aapps.wikisearch.R;
import com.aapps.wikisearch.database.models.RealmPage;
import com.aapps.wikisearch.search.model.Pages;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class SavedSearchAdapter extends RealmRecyclerViewAdapter<RealmPage, SavedSearchAdapter.SearchViewHolder> {

    private SavedSearchAdapterListener mSearchAdapterListener;

    public SavedSearchAdapter(@Nullable OrderedRealmCollection<RealmPage> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }


    public void setSearchAdapterListener(SavedSearchAdapterListener mSearchAdapterListener) {
        this.mSearchAdapterListener = mSearchAdapterListener;
    }


    @NonNull
    @Override
    public SavedSearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View searchView = inflater.inflate(R.layout.search_result_item, parent, false);
        return new SavedSearchAdapter.SearchViewHolder(searchView);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedSearchAdapter.SearchViewHolder holder, int position) {
        final RealmPage mPage = getItem(position);
        if (mPage.getThumbnail() != null)
            ImageLoader.getInstance().displayImage(mPage.getThumbnail().getSource(), holder.mSearchImage);
        holder.mPrimaryText.setText(mPage.getTitle());
        if (mPage.getTerms() != null && mPage.getTerms().getDescription() != null)
            holder.mSecondaryText.setText(mPage.getTerms().getDescription());

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchAdapterListener.onSearchItemClicked(mPage);
            }
        });
    }

    public interface SavedSearchAdapterListener {
        void onSearchItemClicked(RealmPage pages);
    }


    class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_result_image)
        ImageView mSearchImage;

        @BindView(R.id.search_primary_text)
        TextView mPrimaryText;

        @BindView(R.id.search_secondary_text)
        TextView mSecondaryText;

        @BindView(R.id.search_result_item_container)
        RelativeLayout mContainer;


        SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
