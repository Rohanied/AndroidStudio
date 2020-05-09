package com.example.news;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.news.Element;
import com.example.news.MainActivity;
import com.example.news.QueryUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ElementLoader extends AsyncTask<Void, Void, List<Element>> {
    final static String HEADLINES_REQUEST = "https://newsapi.org/v2/top-headlines?country=in&apiKey=84d8b3715ff8473cb205b26cdfd6eeac";
    public static final String LOG_TAG= MainActivity.class.getName();

    String mUrl;
    public List<Element> elements;
    private MainActivity activity;

    //ElementLoader(MainActivity activity){
    //    this.activity=activity;
   // }


    @Override
    protected List<Element> doInBackground(Void... voids) {
        try {
            elements=QueryUtils.fetchdata(HEADLINES_REQUEST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return elements;
    }

    @Override
    protected void onPostExecute(List<Element> elements) {
        super.onPostExecute(elements);
        elements=this.elements;
}
}
