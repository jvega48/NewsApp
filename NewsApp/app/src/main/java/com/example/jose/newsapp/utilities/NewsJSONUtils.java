package com.example.jose.newsapp.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class NewsJSONUtils {
        public static ArrayList<NewsItem> parseJSON(String json) throws JSONException{
            ArrayList<NewsItem> rsl = new ArrayList<>();
            JSONObject object = new JSONObject(json);
            JSONArray newsItems = object.getJSONArray("articles");

            for(int i = 0; i < newsItems.length(); i++){
                JSONObject item = newsItems.getJSONObject(i);
                String author = item.getString("author");
                String title = item.getString("title");
                String description = item.getString("description");
                String url = item.getString("url");
                String img = item.getString("urlToImage");
                String publishedAt = item.getString("publishedAt");
                NewsItem news = new NewsItem(author,title,description,url,img,publishedAt);
                rsl.add(news);
            }
            return rsl;
        }
}
