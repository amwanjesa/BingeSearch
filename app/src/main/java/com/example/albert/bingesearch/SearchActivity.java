package com.example.albert.bingesearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

public class SearchActivity extends AppCompatActivity {

    Film foundMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        displayListButton();
    }

    private void displayListButton(){
        SharedPreferences moviePrefs = getSharedPreferences("settings", getApplicationContext().MODE_PRIVATE);
        Map<String, ?> allEntries = moviePrefs.getAll();
        if(!allEntries.isEmpty()){
            Button watchListBtn = (Button) findViewById(R.id.watchlist_btn);
            watchListBtn.setEnabled(true);
            watchListBtn.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onResume(){
        super.onResume();
        setContentView(R.layout.activity_search);
        displayListButton();
    }

    public void searchMovieData(View view){
        EditText movieTitle = (EditText) findViewById(R.id.search_text);
        MovieAsyncTask movieTask = new MovieAsyncTask(this);
        String title = movieTitle.getText().toString();
        title = handleInput(title);
        if(title.length() > 0){
            movieTask.execute(getString(R.string.movieAPI_1) + title + getString(R.string.movieAPI_2));
        }
    }

    private String handleInput(String title){
        if(title.length() > 0){
            String[] splitTitle = title.split(" ");
            if(splitTitle.length > 1){
                String result = "";
                int count = splitTitle.length;
                while(count > 1){
                    result = result + splitTitle[splitTitle.length-count] + "+";
                    count = count - 1;
                }
                result = result + splitTitle[splitTitle.length-1];
                return result;
            }
            return title;
        }else{
            Toast.makeText(getApplicationContext(), "Please enter a movie title!", Toast.LENGTH_SHORT);
            return "";
        }
    }

    public void setData(Film foundData){
        foundMovie = foundData;
        Intent movieIntent = new Intent(SearchActivity.this, MovieViewActivity.class);
        movieIntent.putExtra("theMovie", foundMovie);
        movieIntent.putExtra("added", false);
        SearchActivity.this.startActivityForResult(movieIntent, 1);
    }

    public void checkWatchList(View view){
        Intent watchListIntent = new Intent(SearchActivity.this, WatchListActivity.class);
        SearchActivity.this.startActivity(watchListIntent);
    }

}
