package com.example.keyword_project;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetApiTask extends AsyncTask<String, Void, String> {

    private OnGetRequestListener listener;

    public GetApiTask(OnGetRequestListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString = params[0];
        String result = "";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 응답 받기
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            result = "Error: " + e.getMessage();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onGetRequestComplete(result);
    }

    // GET 요청 결과를 리스너를 통해 전달하기 위한 인터페이스
    public interface OnGetRequestListener {
        void onGetRequestComplete(String result);
    }
}
