package com.project.m_ajira.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m_ajira.Model.AcceptedModel;
import com.project.m_ajira.R;

import java.util.ArrayList;

public class AcceptedAdapter extends RecyclerView.Adapter<AcceptedAdapter.ViewHolder> {
    Context context;
    ArrayList<AcceptedModel>list;

    public AcceptedAdapter(Context context, ArrayList<AcceptedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AcceptedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.accepted_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AcceptedAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getJobTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.speciality_txt);
        }
    }
}
