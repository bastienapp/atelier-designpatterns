package fr.wildcodeschool.atelierdesignpatterns;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class NewsSingleton extends Observable {

    private List<NewsModel> mNewsList = new ArrayList<>();
    private static volatile NewsSingleton mInstance;

    private NewsSingleton() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("news");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    NewsModel newsModel = data.getValue(NewsModel.class);
                    mNewsList.add(newsModel);
                    setChanged();
                    notifyObservers();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static NewsSingleton getInstance() {
        if (mInstance == null) {
            synchronized (NewsSingleton.class) {
                if (mInstance == null) {
                    mInstance = new NewsSingleton();
                }
            }
        }
        return mInstance;
    }

    public List<NewsModel> getListOfNews() {
        return mNewsList;
    }
}
