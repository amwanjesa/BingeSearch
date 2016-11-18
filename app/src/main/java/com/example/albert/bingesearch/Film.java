package com.example.albert.bingesearch;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by albert on 15/11/2016.
 */

public class Film implements Serializable{

    String movieTitle;
    String genre;
    String synopsis;
    String posterURL;

    public Film(String movieTitle, String genres, String synopsis, String posterURL){
        Log.d("trying movie", movieTitle);
        this.movieTitle = movieTitle;
        this.genre = genres;
        this.synopsis = synopsis;
        this.posterURL = posterURL;
    }

    public Film(String jsonString){
        try {
            JSONObject obj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String toString(){
        return movieTitle + "###" + genre + "###" + synopsis + "###" + posterURL;
    }
}
