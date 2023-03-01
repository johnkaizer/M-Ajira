package com.project.m_ajira.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class LabourAdapterManage extends FirebaseRecyclerAdapter<LabourModel, LabourAdapterManage.LabourHolder> {
    Context context;
    public LabourAdapterManage(@NonNull FirebaseRecyclerOptions<LabourModel> options, Context context) {
        super(options);
        this.context= context;
    }

    @Override
    protected void onBindViewHolder(@NonNull LabourHolder holder, @SuppressLint("RecyclerView") int i, @NonNull LabourModel model) {
        getRef(i).getKey();

        holder.dateC.setText(model.getDateCreated());
        holder.rates.setText(model.getRate());
        holder.title.setText(model.getTitle());
        holder.location.setText(model.getPlace());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("JobsPosted")
                        .child(getRef(i).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Deleted Successfully..", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialogPlus = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.edit_job))
                        .setExpanded(false)
                        .create();

                View holderView = dialogPlus.getHolderView();
                EditText name = holderView.findViewById(R.id.owner);
                EditText phone = holderView.findViewById(R.id.phone);
                EditText rates = holderView.findViewById(R.id.rates);
                EditText description = holderView.findViewById(R.id.description);
                EditText location = holderView.findViewById(R.id.location);
                Spinner skills = holderView.findViewById(R.id.title_spinner);

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context ,R.array.jobs, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                skills.setAdapter(adapter);

                name.setText(model.getOwner());
                phone.setText(model.getOwnerPhone());
                description.setText(model.getDescription());
                rates.setText(model.getRate());
                location.setText(model.getPlace());

                Button Update= holderView.findViewById(R.id.editbtn);
                Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("description",description.getText().toString());
                        map.put("owner",name.getText().toString());
                        map.put("ownerPhone",phone.getText().toString());
                        map.put("place",location.getText().toString());
                        map.put("rate",rates.getText().toString());
                        map.put("title",skills.getSelectedItem().toString());

                        FirebaseDatabase.getInstance().getReference().child("JobsPosted")
                                .child(getRef(i).getKey())
                                .updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialogPlus.dismiss();
                                    }
                                });


                    }
                });
                dialogPlus.show();

            }
        });

    }

    @NonNull
    @Override
    public LabourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabourHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.labour_item_manage, parent, false));

    }

    public class LabourHolder extends RecyclerView.ViewHolder {
        TextView title, rates, location,dateC;
        AppCompatButton editBtn, deleteBtn;
        public LabourHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.desc_txt);
            rates =itemView.findViewById(R.id.rates_txt);
            location =itemView.findViewById(R.id.textView14);
            dateC =itemView.findViewById(R.id.textView16);
            editBtn =itemView.findViewById(R.id.btn1);
            deleteBtn =itemView.findViewById(R.id.btn2);

        }
    }
}
