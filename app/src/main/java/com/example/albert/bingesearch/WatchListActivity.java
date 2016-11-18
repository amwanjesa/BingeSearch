package com.example.albert.bingesearch;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by albert on 16/11/2016.
 */

public class WatchListActivity extends ListActivity {

    ArrayList<Film> moviesInList = new ArrayList<Film>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        getFavoritesMovies();


        String[] listItems = new String[moviesInList.size()];

        for(int i = 0; i < moviesInList.size(); i++){
            Film movie = moviesInList.get(i);
            listItems[i] = movie.movieTitle;
        }

        ArrayAdapter adapter;
        adapter = new ArrayAdapter(getListView().getContext(), android.R.layout.simple_list_item_1, listItems);
        getListView().setAdapter(adapter);

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent movieIntent = new Intent(WatchListActivity.this, MovieViewActivity.class);
                movieIntent.putExtra("theMovie", moviesInList.get(position));
                movieIntent.putExtra("added", true);
                startActivity(movieIntent);
                finish();
            }
        });

    }

    private Film stringToFilm(String movieString){
        String[] splitted = movieString.split(getString(R.string.split_char));
        Log.d("nextEntry", Arrays.toString(splitted));
        Film film = new Film(splitted[0], splitted[1], splitted[2], splitted[3]);
        Log.d("no. of params", film.toString());
        return film;
    }

     private void getFavoritesMovies(){
        SharedPreferences moviePrefs = getSharedPreferences("settings", getApplicationContext().MODE_PRIVATE);

        Map<String, ?> allEntries = moviePrefs.getAll();
        Log.d("no. of movies", Integer.toString(allEntries.entrySet().size()));
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String nextEntry = (String) entry.getValue();
            moviesInList.add(stringToFilm(nextEntry));
        }
    }

}
