package com.project.m_ajira.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m_ajira.Activities.DetailsActivity;
import com.project.m_ajira.Activities.UserMoreDetails;
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
    public void onBindViewHolder(@NonNull LabourAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.rates.setText(list.get(position).getRate());
        holder.location.setText(list.get(position).getPlace());
        holder.dateC.setText(list.get(position).getDateCreated());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("date",list.get(position).getDateCreated());
                intent.putExtra("desc",list.get(position).getDescription());
                intent.putExtra("owner",list.get(position).getOwner());
                intent.putExtra("phone",list.get(position).getOwnerPhone());
                intent.putExtra("place",list.get(position).getPlace());
                intent.putExtra("rate",list.get(position).getRate());
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("email",list.get(position).getOwnerId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, rates, location,dateC;
        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.desc_txt);
            rates =itemView.findViewById(R.id.rates_txt);
            location =itemView.findViewById(R.id.textView14);
            dateC =itemView.findViewById(R.id.textView16);
            card =itemView.findViewById(R.id.cardView2);
        }
    }
}
