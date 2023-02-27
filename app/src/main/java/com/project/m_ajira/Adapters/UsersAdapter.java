package com.project.m_ajira.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.m_ajira.Model.ProfileModel;
import com.project.m_ajira.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    public UsersAdapter(Context context, ArrayList<ProfileModel> list) {
        this.context = context;
        this.list = list;
    }
    Context context;
    ArrayList<ProfileModel> list;
    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.labourer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {
        holder.skill.setText(list.get(position).getSkill());
        holder.rates.setText(list.get(position).getUserRates());
        holder.name.setText(list.get(position).getUserName());
        holder.phone.setText(list.get(position).getUserPhone());
        holder.location.setText(list.get(position).getUserHome());
        Picasso.get().load(list.get(position).getImageUrl()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView skill,rates,name,phone,location;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skill =itemView.findViewById(R.id.speciality_txt);
            rates =itemView.findViewById(R.id.rates_txt);
            name =itemView.findViewById(R.id.textView18);
            phone =itemView.findViewById(R.id.textView20);
            location =itemView.findViewById(R.id.textView22);
            image =itemView.findViewById(R.id.labour_icon);
        }
    }
}
