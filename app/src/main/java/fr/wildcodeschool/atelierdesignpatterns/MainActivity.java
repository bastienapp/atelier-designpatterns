package fr.wildcodeschool.atelierdesignpatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private NewsAdapter mAdapter = null;
    private NewsSingleton mNewsSingleton;
    private Observable mNewsObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsSingleton = NewsSingleton.getInstance();
        mNewsObservable = NewsSingleton.getInstance();
        mNewsObservable.addObserver(this);

        RecyclerView newsListView = findViewById(R.id.news_list);
        newsListView.setHasFixedSize(true);
        newsListView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(new NewsAdapter.NewsClickListener() {
            @Override
            public void onClick(NewsModel newsModel) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("headline", newsModel.getHeadline());
                intent.putExtra("content", newsModel.getNewsContent());
                startActivity(intent);
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
    public void update(Observable observable, Object o) {
        if (observable instanceof NewsSingleton) {
            mAdapter.updateAdapter(mNewsSingleton.getListOfNews());
        }
    }
}
