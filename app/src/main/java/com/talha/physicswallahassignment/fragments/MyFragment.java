package com.talha.physicswallahassignment.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.talha.physicswallahassignment.R;
import com.talha.physicswallahassignment.adapter.CustomRecyclerScroller;
import com.talha.physicswallahassignment.adapter.RecyclerAdapter;
import com.talha.physicswallahassignment.models.TeacherModel;
import com.talha.physicswallahassignment.retrofitclient.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyFragment extends Fragment {
    private int TOTAL_SIZE = 5;
    private static final int PAGE_START = 0;
    boolean isLoading = false;
    private int currentPage = PAGE_START;
    boolean isLastPage = false;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    List<TeacherModel> teacherModelList;
    RecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        recyclerView = v.findViewById(R.id.data_recyclerview);
        progressBar = v.findViewById(R.id.progress_circular);
        teacherModelList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new CustomRecyclerScroller(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                loadingMore();
                isLoading = true;
                currentPage++;

            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        firstLoading();

        return v;
    }
    private void firstLoading() {
        RetrofitClient.getInstance().getApi().getUserData().enqueue(new Callback<List<TeacherModel>>() {
            @Override
            public void onResponse(Call<List<TeacherModel>> call, Response<List<TeacherModel>> response) {
                isLoading = false;
                TOTAL_SIZE = response.body().size() / 10;
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onResponse: " + response.body().get(0).getName());
                teacherModelList = response.body();
                adapter.addAll(response.body());
                if (currentPage < TOTAL_SIZE) adapter.addLoadingFooter();
                else isLastPage = true;

            }

            @Override
            public void onFailure(Call<List<TeacherModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadingMore() {

        RetrofitClient.getInstance().getApi().getUserData().enqueue(new Callback<List<TeacherModel>>() {
            @Override
            public void onResponse(Call<List<TeacherModel>> call, Response<List<TeacherModel>> response) {
                adapter.removeLoadingFooter();
                isLoading = false;

                List<TeacherModel> results = response.body();
                assert results != null;
                adapter.addAll(results);


                if (currentPage != TOTAL_SIZE) adapter.addLoadingFooter();
                else isLastPage = true;
            }

            @Override
            public void onFailure(Call<List<TeacherModel>> call, Throwable t) {
                t.printStackTrace();
            }

        });
    }
}

