package fr.wildcodeschool.atelierdesignpatterns;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bastienwcs on 26/11/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsModel> mNewsList;
    private NewsClickListener mListener;

    public NewsAdapter(NewsClickListener listener) {
        mNewsList = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final NewsModel newsModel = mNewsList.get(position);
        holder.headline.setText(newsModel.getHeadline());
        holder.content.setText(newsModel.getNewsContent());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(newsModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    void updateAdapter(List<NewsModel> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }

    public interface NewsClickListener {

        void onClick(NewsModel newsModel);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container;
        TextView headline, content;

        NewsViewHolder(View view) {
            super(view);
            container = view.findViewById(R.id.newsitem_container);
            headline = view.findViewById(R.id.newsitem_headline);
            content = view.findViewById(R.id.newsitem_content);
        }
    }
}
