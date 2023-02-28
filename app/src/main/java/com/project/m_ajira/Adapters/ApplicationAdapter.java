package com.project.m_ajira.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m_ajira.Model.ApplicationModel;
import com.project.m_ajira.R;

import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

    Context context;
    ArrayList<ApplicationModel>list;

    public ApplicationAdapter(Context context, ArrayList<ApplicationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ApplicationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.application_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getJobTitle());
        holder.rate.setText(list.get(position).getAppRates());
        holder.name.setText(list.get(position).getAppName());
        holder.phone.setText(list.get(position).getAppEmail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,rate, name,phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.speciality_txt);
            rate = itemView.findViewById(R.id.rates_txt);
            name = itemView.findViewById(R.id.textView18);
            phone = itemView.findViewById(R.id.textView20);
        }
    }
}
