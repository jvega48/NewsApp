package com.example.jose.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jose.newsapp.utilities.NetworkUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private EditText nSearchBoxEditText;
    private TextView nURLDisplayTextView;
    private TextView nSearchResultsTextView;
    private  TextView errorMessage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);
        nURLDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        nSearchResultsTextView = (TextView) findViewById(R.id.tv_newssearch_result_json);
        errorMessage = (TextView) findViewById(R.id.tv_error_message_display);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemChoose = item.getItemId();
        if(itemChoose == R.id.action_search){
            makeNewsSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showNewsJsonData(){
        nSearchResultsTextView.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.INVISIBLE);
    }

    public void showError(){
        nSearchResultsTextView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    private void makeNewsSearchQuery(){
        String newsQuery = nSearchBoxEditText.getText().toString();
        URL newsSearchUrl = NetworkUtils.buildURL(newsQuery);
        nURLDisplayTextView.setText(newsSearchUrl.toString());
        new newsQueryRask().execute(newsSearchUrl);
    }
    public class newsQueryRask extends AsyncTask<URL,Void,String>{
        @Override
        protected String doInBackground(URL... params) {
            URL lookingURL = params[0];
            String newsSearchRslt = null;
            try {

                newsSearchRslt = NetworkUtils.getResponsedFromHttpUrl(lookingURL);

            }catch (IOException e){
                e.printStackTrace();
            }
            return newsSearchRslt;
        }

        @Override
        protected void onPostExecute(String newSearchRslt) {
            progressBar.setVisibility(View.INVISIBLE);
            if(newSearchRslt != null && !newSearchRslt.equals("")){
                showNewsJsonData();
                nSearchResultsTextView.setText(newSearchRslt);
            }else{
                showError();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
