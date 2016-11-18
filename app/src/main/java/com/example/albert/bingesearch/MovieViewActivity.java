package com.example.albert.bingesearch;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by albert on 18/11/2016.
 */

public class MovieViewActivity extends AppCompatActivity{
    Bitmap moviePoster;
    Film theMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Bundle extras = getIntent().getExtras();

        theMovie = (Film) extras.getSerializable("theMovie");
        boolean added = extras.getBoolean("added");

        if(added){
            Button addButton = (Button) findViewById(R.id.add_to_wachtlist);
            addButton.setEnabled(false);
            addButton.setVisibility(View.INVISIBLE);
        }
        TextView title = (TextView) findViewById(R.id.movie_title);
        TextView genre = (TextView) findViewById(R.id.movie_genre);
        TextView synopsis = (TextView) findViewById(R.id.movie_synopsis);
        PosterAsyncTask posterTask = new PosterAsyncTask(this);

        if (theMovie != null) {
            title.setText(theMovie.movieTitle);
            title.setText(theMovie.movieTitle);
            genre.setText(theMovie.genre);
            synopsis.setText(theMovie.synopsis);
            posterTask.execute(theMovie.posterURL);
        }
    }

    public void setPoster(Bitmap poster){
        moviePoster = poster;
        ImageView posterView = (ImageView) findViewById(R.id.movie_poster);
        posterView.setImageBitmap(moviePoster);
    }

    public void addToWatchlist(View view){
        SharedPreferences moviePrefs = getSharedPreferences("settings", getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = moviePrefs.edit();
        String movie = theMovie.toString();
        Log.d("added_Movie", movie);
        String[] splitted = movie.split(getString(R.string.split_char));
        editor.putString(splitted[0], movie);
        editor.commit();

        finish();


    }
}

