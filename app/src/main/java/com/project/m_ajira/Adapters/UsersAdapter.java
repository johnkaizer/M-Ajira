package com.project.m_ajira.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.project.m_ajira.Model.ProfileModel;
import com.project.m_ajira.R;
import com.squareup.picasso.Picasso;

public class UsersAdapter extends FirebaseRecyclerAdapter<ProfileModel,UsersAdapter.ViewHolder> {
    public UsersAdapter(@NonNull FirebaseRecyclerOptions<ProfileModel> options, Context context) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int i, @NonNull ProfileModel model) {
        getRef(i).getKey();
        holder.skill.setText(model.getSkill());
        holder.rates.setText(model.getUserRates());
        holder.name.setText(model.getUserName());
        holder.phone.setText(model.getUserPhone());
        holder.location.setText(model.getCurrentHome());
        Picasso.get().load(model.getImageUrl()).into(holder.image);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.labourer_item, parent, false));
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
