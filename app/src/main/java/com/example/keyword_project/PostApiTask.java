package com.example.keyword_project;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostApiTask extends AsyncTask<String, Void, String> {

    private OnPostRequestListener listener;
    private int responseCode;

    public PostApiTask(OnPostRequestListener listener) {
        this.listener = listener;
    }
    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        String postData = params[1];
        String result = "";

        OkHttpClient client = new OkHttpClient();

        try {
            // RequestBody를 생성합니다.
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(postData, JSON);

            // Request 객체를 생성합니다.
            Request request = new Request.Builder()
                    .url(urlString)
                    .post(requestBody)
                    .build();

            // 요청을 보내고 응답을 받습니다.
            Response response = client.newCall(request).execute();
            responseCode = response.code();
            result = response.body().toString();
            // 응답 결과를 문자열로 변환하여 반환합니다.
        } catch (IOException e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onPostRequestComplete(responseCode,result);
    }

    public interface OnPostRequestListener {
        void onPostRequestComplete(int responseCode,String result);
    }
}

