package com.project.m_ajira.Employer.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Adapters.LabourAdapterManage;
import com.project.m_ajira.Adapters.ManageApplicationAdapter;
import com.project.m_ajira.Model.ApplicationModel;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;

public class ManageJob extends AppCompatActivity {
    RecyclerView recyclerView;
    LabourAdapterManage labourAdapterManage;
    String userIdentity;
    private FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_job);
        recyclerView = findViewById(R.id.job);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userIdentity = user.getUid();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<LabourModel> context = new FirebaseRecyclerOptions.Builder<LabourModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("JobsPosted").orderByChild("ownerId").startAt(userIdentity), LabourModel.class)
                .build();

        labourAdapterManage = new LabourAdapterManage(context, this);

        recyclerView.setAdapter(labourAdapterManage);

    }

    @Override
    public void onStart() {
        super.onStart();

        labourAdapterManage.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        labourAdapterManage.stopListening();
    }

}