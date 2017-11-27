package fr.wildcodeschool.atelierdesignpatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer{
    private final String TAG = getClass().getSimpleName();

    private NewsAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO : init singleton then load news
        final NewsSingleton newsSingleton = NewsSingleton.getInstance();
        newsSingleton.addObserver(this);

        // setup the adapter
        RecyclerView newsListView = findViewById(R.id.news_list);
        newsListView.setHasFixedSize(true);
        newsListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(new NewsAdapter.NewsClickListener() {
            @Override
            public void onClick(NewsModel newsModel) {
               newsSingleton.setSelectedNews(newsModel);
               startActivity(new Intent(MainActivity.this, NewsActivity.class));
            }
        });
        newsListView.setAdapter(mAdapter);

        Button addNews = findViewById(R.id.add_news);
        addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, JournalistActivity.class));
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof NewsSingleton) {
            mAdapter.updateAdapter( (ArrayList<NewsModel>) arg );
            Log.d(TAG, "update() called with: o = [" + o + "], arg = [" + arg + "]");
        }
    }
}
