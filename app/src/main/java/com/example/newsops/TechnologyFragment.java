package com.example.newsops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TechnologyFragment extends Fragment {

    String api="10563b0d64834fc395f01cffae040b09";
    ArrayList<modelClass> modelClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView technoRecyclerView;
    private String category="Technology";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.technofragment,null);
        technoRecyclerView=view.findViewById(R.id.technoRecyclerView);
        modelClassArrayList=new ArrayList<>();
        technoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter =new Adapter(getContext(),modelClassArrayList);
        technoRecyclerView.setAdapter(adapter);

        findnews();
        return view;






    }

    private void findnews() {
        Apiutilities.getApiInterface().getCategoryNews(country,category,100, api).enqueue(new Callback<mainClass>() {
            @Override
            public void onResponse(Call<mainClass> call, Response<mainClass> response) {
                if (response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<mainClass> call, Throwable t) {

            }
        });

    }
}
