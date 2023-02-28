package com.project.m_ajira.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Model.ProfileModel;
import com.project.m_ajira.R;
import com.squareup.picasso.Picasso;

public class UsersAdapterManage extends FirebaseRecyclerAdapter<ProfileModel, UsersAdapterManage.ViewHolder> {
    Context context;
    public UsersAdapterManage(@NonNull FirebaseRecyclerOptions<ProfileModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int i, @NonNull ProfileModel model) {
        getRef(i).getKey();
        holder.skill.setText(model.getSkill());
        holder.rates.setText(model.getUserRates());
        holder.name.setText(model.getUserName());
        holder.phone.setText(model.getUserPhone());
        holder.location.setText(model.getCurrentHome());
        Picasso.get().load(model.getImageUrl()).into(holder.image);
        holder.optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.optionsBtn);
                popupMenu.inflate(R.menu.flow_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_menu:
                                FirebaseDatabase.getInstance().getReference().child("UserProfiles")
                                        .child(getRef(i).getKey())
                                        .removeValue()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(context, "Deleted Successfully..", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                            case R.id.edit_menu:

                        }
                        return false;
                    }
                });
                popupMenu.show();

            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.labourer_item_manage, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView skill,rates,name,phone,location;
        ImageView image;
        ImageButton optionsBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skill =itemView.findViewById(R.id.speciality_txt);
            rates =itemView.findViewById(R.id.rates_txt);
            name =itemView.findViewById(R.id.textView18);
            phone =itemView.findViewById(R.id.textView20);
            location =itemView.findViewById(R.id.textView22);
            optionsBtn =itemView.findViewById(R.id.more);
            image =itemView.findViewById(R.id.labour_icon);

        }
    }
}
