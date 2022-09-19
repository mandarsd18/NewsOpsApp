package com.example.newsops;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    Context context;

    public Adapter(Context context, ArrayList<modelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    ArrayList<modelClass> modelClassArrayList;
    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,null,false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Viewholder holder,int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,webView.class);
                i.putExtra("url",modelClassArrayList.get(position).getUrl());
                context.startActivity(i);
            }
        });
        holder.time.setText("Published At :- "+modelClassArrayList.get(position).getPublishedAt());
        holder.author.setText(modelClassArrayList.get(position).getAuthor());
        holder.heading.setText(modelClassArrayList.get(position).getTitle());
        holder.content.setText(modelClassArrayList.get(position).getDescription());

        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView heading,content,author,time;
        CardView cardView;
        ImageView imageView;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            heading=itemView.findViewById(R.id.mainHeading);
            content=itemView.findViewById(R.id.content);
            author=itemView.findViewById(R.id.author);
            time=itemView.findViewById(R.id.publishedAt);
            imageView=itemView.findViewById(R.id.imageView);
            cardView=itemView.findViewById(R.id.cardView);


        }
    }
}
