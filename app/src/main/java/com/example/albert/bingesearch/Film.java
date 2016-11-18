package com.example.albert.bingesearch;

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
        this.movieTitle = movieTitle;
        this.genre = genres;
        this.synopsis = synopsis;
        this.posterURL = posterURL;
    }

    public String toString(){
        return movieTitle + genre + synopsis + posterURL;
    }
}
