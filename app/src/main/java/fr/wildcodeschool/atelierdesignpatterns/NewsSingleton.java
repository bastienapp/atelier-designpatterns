package fr.wildcodeschool.atelierdesignpatterns;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by adphi on 27/11/17.
 */

public class NewsSingleton extends Observable {
    private final String TAG = getClass().getSimpleName();

    private static NewsSingleton mInstance;
    private static final String NEWS_ENTRY = "news";

    private NewsModel mSelectedNew = null;

    private ArrayList<NewsModel> mNewsList = new ArrayList<>();

    private NewsSingleton() {
        DatabaseReference newsRef = FirebaseDatabase.getInstance().getReference(NEWS_ENTRY);
        newsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    mNewsList.add(data.getValue(NewsModel.class));
                }
                setChanged();
                notifyObservers(mNewsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static NewsSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new NewsSingleton();
        }
        return mInstance;
    }

    public void setSelectedNews(NewsModel news) {
        mSelectedNew = news;
    }

    public NewsModel getSelectedNews() {
        return mSelectedNew;
    }
}
