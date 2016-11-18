package com.example.albert.bingesearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    Film foundMovie;
    ArrayList<String> watchList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if(watchList.size() > 0){
            Button watchListBtn = (Button) findViewById(R.id.watchlist_btn);
            watchListBtn.setEnabled(true);
            watchListBtn.setVisibility(View.VISIBLE);
        }

        SharedPreferences moviePrefs = this.getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = moviePrefs.edit();
        JSONArray movieList = new JSONArray(watchList);
        movieList.toString()
        editor.putString("movieList", movieList.toString());
        editor.commit();
    }

    public void searchMovieData(View view){
        EditText movieTitle = (EditText) findViewById(R.id.search_text);
        MovieAsyncTask movieTask = new MovieAsyncTask(this);
        String title = movieTitle.getText().toString();
        movieTask.execute(getString(R.string.movieAPI_1) + title + getString(R.string.movieAPI_2));
    }

    public void setData(Film foundData){
        foundMovie = foundData;
        Intent movieIntent = new Intent(SearchActivity.this, MovieViewActivity.class);
        movieIntent.putExtra("theMovie", foundMovie);
        SearchActivity.this.startActivityForResult(movieIntent, 1);
    }

    public void checkWatchList(View view){
        Intent watchListIntent = new Intent(SearchActivity.this, WatchListActivity.class);
        watchListIntent.putExtra("movies", watchList);
        SearchActivity.this.startActivity(watchListIntent);
    }

}
