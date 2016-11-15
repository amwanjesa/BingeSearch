package com.example.albert.bingesearch;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        Toast.makeText(context, R.string.pre_exec_toast, Toast.LENGTH_LONG);
    }

    // doInBackGround()
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    // onPostExecute()
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.length() == 0) {
            Toast.makeText(context, "No data was found", Toast.LENGTH_LONG);
        }
        else {
            ArrayList<TrackData> trackData = new ArrayList<>();
            try {
                JSONObject respObj = new JSONObject(result);
                JSONObject topTracksObj = respObj.getJSONObj("tracks");
                JSONArray tracks = topTracksObj.getJSONObj("track");

                for (int i = 0; i < tracks.length(); i++) {
                    JSONObject track = tracks.getJSONObject(i);
                    String trackName = track.getString("name");
                    JSONObject artistObj = track.getJSONObject("artist");
                    String artistName = artistObj.getString("artist");
                    trackData.add(new TrackData(trackName, artistName));
                }
            }
            catch (JSONException e) {

            }
            this.activity.setData(trackData);
        }
    }
}
}
