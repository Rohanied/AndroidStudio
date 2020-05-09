package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.jar.JarInputStream;


public class MainActivity extends AppCompatActivity  {

    public static final String LOG_TAG=MainActivity.class.getName();
    final static String HEADLINES_REQUEST = "https://newsapi.org/v2/top-headlines?country=in&apiKey=84d8b3715ff8473cb205b26cdfd6eeac";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Element> elements = null;
        try {
            elements = new ElementLoader().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mAdapter = new MyAdapter(elements);
        recyclerView.setAdapter(mAdapter);
    }

}
