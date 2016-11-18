package com.example.albert.bingesearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by albert on 16/11/2016.
 */

public class WatchListActivity extends AppCompatActivity {

    ArrayList<Film> moviesInList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        Bundle extras = getIntent().getExtras();
        moviesInList = (ArrayList<Film>) extras.getSerializable("movies");

        ListView mListView = (ListView) findViewById(R.id.watch_listview);
// 1
// 2
        String[] listItems = new String[moviesInList.size()];
// 3
        for(int i = 0; i < moviesInList.size(); i++){
            Film movie = moviesInList.get(i);
            listItems[i] = movie.movieTitle;
        }
// 4
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);

    }
}
