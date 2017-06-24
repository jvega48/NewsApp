package com.example.jose.newsapp.utilities;

import android.net.Uri;

import com.example.jose.newsapp.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Jose on 6/15/2017.
 */

public class NetworkUtils extends MainActivity {

	//Add your unique API key for it to work, removed mine for security purposes
    final static String APIurl = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=" +
            "top&apiKey=";

    public static URL buildURL(String newsSearchQuery){
        Uri buildUri = Uri.parse(APIurl).buildUpon().build();
        URL url = null;
        try{
            url = new URL(buildUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponsedFromHttpUrl(URL url) throws IOException {
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
