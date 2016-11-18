package com.example.albert.bingesearch;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by albert on 15/11/2016.
 */

class MovieAsyncTask extends AsyncTask<String, Integer, String>{

    Context context;
    SearchActivity activity;

    public MovieAsyncTask(SearchActivity activity){
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    protected void onPreExecute() {
        Toast.makeText(context, R.string.pre_exec_toast, Toast.LENGTH_LONG).show();
    }

    // doInBackGround()
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    // onPostExecute()
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.length() == 0) {
            Toast.makeText(context, "Movie not found.", Toast.LENGTH_SHORT).show();
        }
        else {
            try {
                JSONObject respObj = new JSONObject(result);
                String title = respObj.getString("Title");
                String genres = respObj.getString("Genre");
                String synopsis = respObj.getString("Plot");
                String posterURL = respObj.getString("Poster");
                Film movie = new Film(title, genres, synopsis, posterURL);

                this.activity.setData(movie);

            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

