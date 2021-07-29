package com.example.simplefragments;

import android.os.AsyncTask;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
* class handles the requests made to the server via the internet.
*/

public class AsyncNetwork extends AsyncTask<String, Integer, String> {
    String Result = "Waiting";

    @Override
    protected String doInBackground(String... myUrl) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(myUrl[0]).build();

        Call call = client.newCall(request);
        Response response = null;

        try{
            response = call.execute();
        }catch (Exception e){
            e.printStackTrace();
        }

        String ResponseData = null;

        try {
            ResponseData = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        Result = ResponseData;
        return ResponseData;
    }
}
