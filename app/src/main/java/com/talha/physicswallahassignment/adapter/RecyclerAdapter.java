package com.talha.physicswallahassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.talha.physicswallahassignment.R;
import com.talha.physicswallahassignment.models.TeacherModel;

import java.util.LinkedList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;

    public RecyclerAdapter(Context context) {
        this.context = context;
        this.list = new LinkedList<>();
    }

    Context context;
    public void setTeacherList(List<TeacherModel> list) {
        this.list = list;
    }

    List<TeacherModel> list;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout, parent, false);
                holder = new MyViewHolder(view);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_loading, parent, false);
                holder = new LoadingHolder(viewLoading);
                break;
        }
        assert holder != null;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case  ITEM:
                MyViewHolder h = (MyViewHolder) holder;
                h.name.setText(list.get(position).getName());
                h.subQualif.setText(list.get(position).getSubjects().get(0)+" \u2022 "+list.get(position).getQualification().get(0));
                h.viewMore.setOnClickListener(view -> {

                });
                Picasso.get().load(list.get(position).getProfileImage()).into(h.imageView);
                break;
            case LOADING:
                LoadingHolder loadingViewHolder = (LoadingHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0: list.size();
    }
    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new TeacherModel());
    }
    public void addAll(List<TeacherModel> teachers) {
        for (TeacherModel result : teachers) {
            add(result);
        }
    }
    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = list.size() - 1;
        TeacherModel result = getItem(position);

        if (result != null) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }
    public TeacherModel getItem(int position) {
        return list.get(position);
    }

    public void add(TeacherModel movie) {
        list.add(movie);
        notifyItemInserted(list.size() - 1);
    }
    @Override
    public int getItemViewType(int position) {
        return (position == list.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public static class LoadingHolder extends  RecyclerView.ViewHolder{
    ProgressBar progressBar;
        public LoadingHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.loading_progress);
        }
    }

    public static class MyViewHolder  extends  RecyclerView.ViewHolder{
        TextView name;
        TextView subQualif;
        ImageView imageView;
        Button viewMore;
        public MyViewHolder(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.name);
            subQualif = v.findViewById(R.id.subject_qualification);
            imageView = v.findViewById(R.id.teacher_image);
            viewMore = v.findViewById(R.id.btn_view_more);
        }
    }
}
