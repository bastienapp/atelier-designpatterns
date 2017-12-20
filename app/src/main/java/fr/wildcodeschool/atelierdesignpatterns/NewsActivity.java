package fr.wildcodeschool.atelierdesignpatterns;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent = getIntent();
        NewsModel newsModel = intent.getParcelableExtra("news");

        TextView headline = findViewById(R.id.news_headline);
        headline.setText(newsModel.getHeadline());
        TextView content = findViewById(R.id.news_content);
        content.setText(newsModel.getNewsContent());
    }
}
