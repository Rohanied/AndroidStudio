package com.example.news;

import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    final static String HEADLINES_REQUEST = "https://newsapi.org/v2/top-headlines?country=in&apiKey=84d8b3715ff8473cb205b26cdfd6eeac";
    private static final String LOG_TAG = QueryUtils.class.getName();

    public static URL createURL(String link) {

        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String makehttprequest(URL url) throws IOException {
        String jsonresponse = "";

        if (url == null) return null;

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonresponse = readfromjson(inputStream);
            } else Log.e(LOG_TAG, "Error in connection");
        } catch (IOException e) {
            Log.v(LOG_TAG, "couldn't make connection", e);
        } finally {
            if (urlConnection != null) urlConnection.disconnect();
            if (inputStream != null) inputStream.close();
        }

        return jsonresponse;
    }

    public static String readfromjson(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }

        }
        return output.toString();
    }

    public static List<Element> extractfromjson(String json) throws JSONException {
        if(TextUtils.isEmpty(json)) return null;

        List<Element> elements = new ArrayList<Element>();

        try{
            JSONObject baseobject = new JSONObject(json);
            JSONArray articles = baseobject.getJSONArray("articles");

            for(int i=0; i<articles.length();i++){
                JSONObject currentarticle = articles.getJSONObject(1);

                String title = currentarticle.getString("title");
                String subtitle = currentarticle.getString("description");
                String urltoimage = currentarticle.getString("urlToImage");

                Element element = new Element(title,subtitle,urltoimage);

            }

        }
        catch (JSONException e){
            Log.v(LOG_TAG, "Error while extracting from json", e);
        }
        return elements;
    }

    public static List<Element> fetchdata(String link) throws JSONException{
        URL url = createURL(link);
        String jsonresponse=null;
        try {
            jsonresponse=makehttprequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
       List<Element> elements = extractfromjson(jsonresponse);
        return elements;
    }

}
