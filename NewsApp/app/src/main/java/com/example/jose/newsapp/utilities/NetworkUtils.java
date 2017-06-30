package com.example.jose.newsapp.utilities;

import android.net.Credentials;
import android.net.Uri;
import android.util.Log;

import com.example.jose.newsapp.MainActivity;
import com.example.jose.newsapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Jose on 6/15/2017.
 */

public class NetworkUtils {

	//Add your unique API key for it to work, removed mine for security purposes
    //final static String APIurl = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=" +
           // "top&apiKey=";
    public static final String TAG ="NetworkUtils";
    public static final String MAIN_URL = "https://newsapi.org/v1/articles";
    public static final String PARAM_SOURCE ="source";
    public static final String PARAM_SORT_BY ="sortBy";
    public  static final String PARAM_KEY= "apiKey";
    public static URL buildURL(String source, String sortBy, String key){
        Uri buildUri = Uri.parse(MAIN_URL).buildUpon().appendQueryParameter(PARAM_SOURCE, source).appendQueryParameter(PARAM_SORT_BY, sortBy).
                appendQueryParameter(PARAM_KEY, key).build();
        URL url = null;
        try{
            url = new URL(buildUri.toString());
            Log.d(TAG,"URL: " + url);
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner sc = new Scanner(in);
            sc.useDelimiter("\\A");
            boolean hasInput = sc.hasNext();
            if(hasInput){
                return sc.next();
            }else{
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
