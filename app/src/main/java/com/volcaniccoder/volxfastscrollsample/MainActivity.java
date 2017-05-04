package com.volcaniccoder.volxfastscrollsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.volcaniccoder.volxfastscroll.Volx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<UserModel> model = new ArrayList<>();

    private FrameLayout parentLayout;

    private final String[] common = {"the", "of", "and", "a", "to", "in", "is", "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", "his", "they", "I", "at", "be", "this", "have", "from", "or", "one", "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", "your", "can", "said", "there", "use", "an", "each", "which", "she", "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", "then", "them", "these", "so", "some", "her", "would", "make", "like", "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", "number", "no", "way", "could", "people", "my", "than", "first", "water", "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", "did", "get", "come", "made", "may", "part"};

    private List<String> stringList;

    private Volx volx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringList = Arrays.asList(common);

        Collections.sort(stringList,
                new Comparator<String>() {
                    public int compare(String f1, String f2) {
                        return f1.compareTo(f2);
                    }
                });

        for (String s : stringList) {
            UserModel userModel = new UserModel();
            userModel.setName(s);
            userModel.setCounter(0);
            model.add(userModel);
        }

        parentLayout = (FrameLayout) findViewById(R.id.activity_use);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcx);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new UsersOwnAdapter(model);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setNestedScrollingEnabled(false);

        volx = new Volx.Builder()
                .setUserRecyclerView(mRecyclerView)
                .setParentLayout(parentLayout)
                .build();

        /*
        volx = new Volx.Builder()
                .setUserRecyclerView(mRecyclerView)
                .setParentLayout(parentLayout)
                .setActiveColor(Color.CYAN) // the lightened color of right bar
                .setBackgroundColor(Color.BLACK) // the color of right bar
                .setTextColor(Color.WHITE) // the color of right bar letters
                .setBarWidth(24) // the width of right bar in dp
                .setBarHeightRatio(1f) // the height of right bar between 0 and 1 according to screen height
                .setTextSize(0) // the size of the letters in right bar , default zero handles it nicely , specific values are also ok
                .setMiddleTextSize(16) // the size of the letter in center circle
                .setMiddleLayoutSize(48) // the size of the center circle in dp
                .setMiddleBackgroundColor(Color.rgb(67, 67, 67)) // the color of the center circle
                .setMiddleTextColor(Color.WHITE)  // the color of the letter in center circle
                .setMiddleStrokeWidth(4) // the width of center circle stroke in dp
                .setMiddleStrokeColor(Color.BLACK) // the color of right bar stroke
                .setRightStrokeWidth(3) // the width of right bar stroke in dp
                .setRightStrokeColor(Color.rgb(204, 204, 204)) // the color of middle circle stroke
                .setMinItem(0) // the min amount of item required to show right bar
                .setDelayMillis(3000) // the amount of time in ms that closes right bar if there are no interaction
                .build();
                */

        /*
        if (volx.isInactive())
            volx.setInactive(false);
            */
    }

}
