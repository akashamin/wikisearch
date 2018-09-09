package com.aapps.wikisearch.search.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aapps.wikisearch.R;
import com.aapps.wikisearch.search.model.Pages;
import com.aapps.wikisearch.search.model.Search;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context mContext;
    private List<Pages> mSearchList;
    private SearchAdapterListener mSearchAdapterListener;

    public SearchAdapter(Context mContext, List<Pages> mSearchList, SearchAdapterListener mSearchAdapterListener) {
        this.mContext = mContext;
        this.mSearchList = mSearchList;
        this.mSearchAdapterListener = mSearchAdapterListener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View searchView = inflater.inflate(R.layout.search_result_item, parent, false);
        return new SearchViewHolder(searchView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        final Pages mPage = mSearchList.get(position);
        if (mPage.getThumbnail() != null)
            ImageLoader.getInstance().displayImage(mPage.getThumbnail().getSource(), holder.mSearchImage);
        holder.mPrimaryText.setText(mPage.getTitle());
        if (mPage.getTerms() != null && mPage.getTerms().getDescription() != null)
            holder.mSecondaryText.setText(mPage.getTerms().getDescription().get(0));

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchAdapterListener.onSearchItemClicked(mPage);
            }
        });
    }

    public interface SearchAdapterListener {
        void onSearchItemClicked(Pages pages);
    }

    @Override
    public int getItemCount() {
        return mSearchList != null ? mSearchList.size() : 0;
    }

    public void updateSearchList(List<Pages> mSearchList) {
        this.mSearchList = mSearchList;
        notifyDataSetChanged();
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
