package com.project.m_ajira.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Model.AcceptedModel;
import com.project.m_ajira.Model.ApplicationModel;
import com.project.m_ajira.R;

public class ManageApplicationAdapter extends FirebaseRecyclerAdapter<ApplicationModel, ManageApplicationAdapter.Holder> {
    Context context;

    public ManageApplicationAdapter(@NonNull FirebaseRecyclerOptions<ApplicationModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ManageApplicationAdapter.Holder holder, @SuppressLint("RecyclerView") int i, @NonNull ApplicationModel model) {
        getRef(i).getKey();

        holder.title.setText(model.getJobTitle());
        holder.rate.setText(model.getAppRates());
        holder.name.setText(model.getAppName());
        holder.phone.setText(model.getAppDesc());
        holder.email.setText(model.getAppEmail());
        holder.location.setText(model.getAppLocation());
        holder.reason.setText(model.getAppReason());
        holder.denyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("JobApplication")
                        .child(getRef(i).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Denied Successfully..", Toast.LENGTH_SHORT).show();

                            }
                        });}
        });
        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dataRef= FirebaseDatabase.getInstance().getReference().child("Confirmed");
                String JobTitle = holder.title.getText().toString();
                String AppName = holder.name.getText().toString();
                String AppEmail = holder.email.getText().toString();
                String AppRates = holder.rate.getText().toString();
                String AppUid = model.getUserUid();

                AcceptedModel acceptedModel = new AcceptedModel(JobTitle,AppName,AppEmail,AppRates,AppUid);
                dataRef.push().setValue(acceptedModel);
                FirebaseDatabase.getInstance().getReference().child("JobApplication")
                        .child(getRef(i).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
                Toast.makeText(context, "Successfully confirmed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @NonNull
    @Override
    public ManageApplicationAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.manage_application_item, parent, false));

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView title, rate, name, phone, email, location, reason;
        AppCompatButton acceptBtn, denyBtn;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.speciality_txt);
            rate = itemView.findViewById(R.id.rates_txt);
            name = itemView.findViewById(R.id.textView18);
            phone = itemView.findViewById(R.id.textView20);
            email = itemView.findViewById(R.id.textViewb);
            location = itemView.findViewById(R.id.textViewd);
            reason = itemView.findViewById(R.id.textViewf);
            acceptBtn = itemView.findViewById(R.id.accept);
            denyBtn = itemView.findViewById(R.id.deny);
        }
    }
}
