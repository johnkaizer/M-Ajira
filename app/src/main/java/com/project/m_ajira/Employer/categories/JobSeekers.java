package com.project.m_ajira.Employer.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.m_ajira.Adapters.LabourAdapter;
import com.project.m_ajira.Adapters.UsersAdapter;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.Model.ProfileModel;
import com.project.m_ajira.R;

import java.util.ArrayList;

public class JobSeekers extends AppCompatActivity {
    UsersAdapter usersAdapter;
    ArrayList<ProfileModel>list1;
    RecyclerView usersRv;
    Query databaseReference;
    ShimmerFrameLayout shimmerFrameLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_sekers);
        usersRv= findViewById(R.id.usersRv);
        ////USERS
        shimmerFrameLayout1 = findViewById(R.id.shimmer1);
        shimmerFrameLayout1.startShimmer();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserProfiles");
        usersRv.setHasFixedSize(true);
        usersRv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
        list1 = new ArrayList<>();
        usersAdapter = new UsersAdapter(this, list1);
        usersRv.setAdapter(usersAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    ProfileModel profileModel= dataSnapshot.getValue(ProfileModel.class);
                    list1.add(profileModel);
                }
                shimmerFrameLayout1.stopShimmer();
                shimmerFrameLayout1.setVisibility(View.GONE);
                usersRv.setVisibility(View.VISIBLE);
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}