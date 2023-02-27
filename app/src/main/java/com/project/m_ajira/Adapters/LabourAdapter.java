package com.project.m_ajira.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;

import java.util.ArrayList;

public class LabourAdapter extends RecyclerView.Adapter<LabourAdapter.ViewHolder> {

    Context context;
    ArrayList<LabourModel>list;

    public LabourAdapter(Context context, ArrayList<LabourModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LabourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.labour_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LabourAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTile());
        holder.rates.setText(list.get(position).getRate());
        holder.location.setText(list.get(position).getPlace());
        holder.dateC.setText(list.get(position).getDateCreated());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, rates, location,dateC;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.desc_txt);
            rates =itemView.findViewById(R.id.rates_txt);
            location =itemView.findViewById(R.id.textView14);
            dateC =itemView.findViewById(R.id.textView16);
        }
    }
}
