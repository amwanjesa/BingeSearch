package com.example.albert.bingesearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.InputStream;

/**
 * Created by albert on 18/11/2016.
 */

class PosterAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    Context context;
    MovieViewActivity activity;


    public PosterAsyncTask(MovieViewActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    protected void onPreExecute() {
        Toast.makeText(context, "Loading poster...", Toast.LENGTH_SHORT).show();
    }

    // doInBackGround()
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap poster = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            poster = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return poster;
    }

    // onPostExecute()
    protected void onPostExecute(Bitmap poster) {
        this.activity.setPoster(poster);
    }
}

