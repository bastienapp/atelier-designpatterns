package fr.wildcodeschool.atelierdesignpatterns;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsModel> mNewsList;
    private View mItemView;
    private NewsClickListener mListener;

    public NewsAdapter(NewsClickListener listener) {
        mNewsList = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new NewsViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        final NewsModel newsModel = mNewsList.get(position);
        holder.headline.setText(newsModel.getHeadline());
        holder.content.setText(newsModel.getNewsContent());
        mItemView.setOnClickListener(new View.OnClickListener() {
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

    public interface NewsClickListener {

        void onClick(NewsModel newsModel);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView headline, content;

        NewsViewHolder(View view) {
            super(view);
            headline = view.findViewById(R.id.newsitem_headline);
            content = view.findViewById(R.id.newsitem_content);
        }
    }

    void updateAdapter(List<NewsModel> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }
}
