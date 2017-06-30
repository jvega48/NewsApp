package com.example.jose.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.jose.newsapp.utilities.NetworkUtils;
import com.example.jose.newsapp.utilities.NewsAdapter;
import com.example.jose.newsapp.utilities.NewsItem;
import com.example.jose.newsapp.utilities.NewsJSONUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    public static String SOURCE = "techcrunch";
    public static String SORTBY = "latest";
    static  final String TAG = "MainActivity";
    //private EditText nSearchBoxEditText;
    //private TextView nURLDisplayTextView;
    //private TextView nSearchResultsTextView;
    //private  TextView errorMessage;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //nSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);
        //nURLDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        //nSearchResultsTextView = (TextView) findViewById(R.id.tv_newssearch_result_json);
        //errorMessage = (TextView) findViewById(R.id.tv_error_message_display);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        recyclerView =(RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NetworkTask task = new NetworkTask(SOURCE,SORTBY,getString(R.string.key));
        task.execute();
    }

 //   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int itemChoose = item.getItemId();
//        if(itemChoose == R.id.action_search){
//            //makeNewsSearchQuery();
//            NetworkTask task = new NetworkTask(SOURCE,SORTBY,getString(R.string.key));
//            task.execute();
//        }
//        return true;
//    }

//    public void showNewsJsonData(){
//        nSearchResultsTextView.setVisibility(View.VISIBLE);
//        errorMessage.setVisibility(View.INVISIBLE);
//    }

//    public void showError(){
//        nSearchResultsTextView.setVisibility(View.INVISIBLE);
//        errorMessage.setVisibility(View.VISIBLE);
//    }

//    private void makeNewsSearchQuery(){
//        String newsQuery = nSearchBoxEditText.getText().toString();
//        URL newsSearchUrl = NetworkUtils.buildURL(newsQuery);
//        //nURLDisplayTextView.setText(newsSearchUrl.toString());
//        new newsQueryRask().execute(newsSearchUrl);
//    }

    public class NetworkTask extends AsyncTask<URL,Void,ArrayList<NewsItem>>{
        String source,sort,key;

        NetworkTask(String SOURCE,String SORTBY,String KEY){
            SOURCE = source;
            SORTBY = sort;
            KEY = key;
        }

        public void showNewsJsonData(){
            recyclerView.setVisibility(View.VISIBLE);
            //errorMessage.setVisibility(View.INVISIBLE);
    }

        public void showError(){
        recyclerView.setVisibility(View.INVISIBLE);
        //errorMessage.setVisibility(View.VISIBLE);
    }
        @Override
        protected ArrayList<NewsItem> doInBackground(URL... params) {
//            URL lookingURL = params[0];
            String json = null;
            ArrayList<NewsItem> results = null;
            URL url = NetworkUtils.buildURL(SOURCE,SORTBY,getString(R.string.key));
            Log.d(TAG, "URL: " + url.toString());
            try {

                json = NetworkUtils.getResponseFromHttpUrl(url);
                results = NewsJSONUtils.parseJSON(json);
            }catch (Exception e){
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected void onPostExecute(final ArrayList<NewsItem> data) {
            super.onPostExecute(data);
            progressBar.setVisibility(View.GONE);
            if( data != null){
                adapter = new NewsAdapter(data, new NewsAdapter.ItemClickListener(){

                    @Override
                    public void onItemClick(int clickedItemIndex) {
                                String url = data.get(clickedItemIndex).getUrl();
                                Log.d(TAG, String.format("URL %s", url));
                                openNewWebPage(url);
                    }
                });
                recyclerView.setAdapter(adapter);
                showNewsJsonData();
            }else {
                showError();
            }

        }
        public void openNewWebPage(String params){
            Uri site = Uri.parse(params);
            Intent intent = new Intent(Intent.ACTION_VIEW, site);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
