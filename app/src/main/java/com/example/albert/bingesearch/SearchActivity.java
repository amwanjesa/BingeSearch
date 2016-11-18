package com.example.albert.bingesearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    Film foundMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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
        SearchActivity.this.startActivity(movieIntent);
    }

}
