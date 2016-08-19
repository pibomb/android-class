package com.example.user.simpleui;

import android.os.AsyncTask;

/**
 * Created by mcmor on 2016-08-18.
 */
public class GeoCodingTask extends AsyncTask<String, Void, double[]> {
    @Override
    protected double[] doInBackground(String... params) {
        return new double[0];
    }

    @Override
    protected void onPostExecute(double[] doubles) {
        super.onPostExecute(doubles);
    }
}
