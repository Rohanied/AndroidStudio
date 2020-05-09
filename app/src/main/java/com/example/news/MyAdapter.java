package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Element> elements;



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
       ImageView imageView;
        public MyViewHolder(View v){
        super(v);
        this.imageView=(ImageView) v.findViewById(R.id.image);
        this.textView1=(TextView) v.findViewById(R.id.head_text);
        this.textView2=(TextView) v.findViewById(R.id.sub_text);
    }
    }

    public MyAdapter(List<Element> Dataset){
        elements= Dataset;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
       MyViewHolder vh = new MyViewHolder(v);
       return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Element element=getItem(position);
        holder.textView1.setText(element.getHeatText());
        holder.textView2.setText(element.getSubText());
        Picasso.get().load(element.getImgid()).resize(50,50).into(holder.imageView);

    }

    private Element getItem(int position) {
        return elements.get(position);
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }



}
