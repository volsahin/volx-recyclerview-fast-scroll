package com.volcaniccoder.volxfastscrollsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private final String[] common = {"the","of","and","a","to","in","is","you","that","it","he","was","for","on","are","as","with","his","they","I","at","be","this","have","from","or","one","had","by","word","but","not","what","all","were","we","when","your","can","said","there","use","an","each","which","she","do","how","their","if","will","up","other","about","out","many","then","them","these","so","some","her","would","make","like","him","into","time","has","look","two","more","write","go","see","number","no","way","could","people","my","than","first","water","been","call","who","oil","its","now","find","long","down","day","did","get","come","made","may","part"};

    private List<String> stringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringList = Arrays.asList(common);

        Collections.sort(stringList,
                new Comparator<String>()
                {
                    public int compare(String f1, String f2)
                    {
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

        new Volx.Builder()
                .setUserRecyclerView(mRecyclerView)
                .setParentLayout(parentLayout)
//                 .setActiveColor(Color.RED)
//                 .setBackgroundColor(Color.YELLOW)
//                 .setTextColor(Color.WHITE)
//                 .setBarWidth(96)
//                 .setTextSize(26)
//                 .setMiddleTextSize(32)
//                 .setMiddleLayoutSize(96)
//                 .setMiddleBackgroundColor(Color.GREEN)
//                 .setMiddleTextColor(Color.CYAN)
                .build();

    }

}
