package com.example.albert.bingesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    MovieData foundMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void searchMovieData(View view){
        EditText movieTitle = (EditText) findViewById(R.id.search_text);
        MovieAsyncTask myTask = new MovieAsyncTask(this);
        myTask.execute(movieTitle.getText().toString());
    }

    public void setData(MovieData foundData){
        foundMovie = foundData;
    }

}
