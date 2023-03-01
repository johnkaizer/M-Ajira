package com.project.m_ajira.Employer.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Adapters.ManageApplicationAdapter;
import com.project.m_ajira.Model.ApplicationModel;
import com.project.m_ajira.R;

public class ManageApplication extends AppCompatActivity {
    ManageApplicationAdapter manageApplicationAdapter;
    RecyclerView jobRecycler;
    String userIdentity;
    private FirebaseUser user;
    DatabaseReference reference;
    ShimmerFrameLayout shimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_application);
        jobRecycler =findViewById(R.id.jobsRv);
        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userIdentity = user.getUid();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.getStackFromEnd();
        jobRecycler.setLayoutManager(layoutManager);
        FirebaseRecyclerOptions<ApplicationModel> context = new FirebaseRecyclerOptions.Builder<ApplicationModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("JobApplication").orderByChild("ownerEmail").startAt(userIdentity).endAt(userIdentity),ApplicationModel.class)
                .build();

        manageApplicationAdapter = new ManageApplicationAdapter(context,this);

        jobRecycler.setAdapter(manageApplicationAdapter);

    }
    @Override
    public void onStart() {
        super.onStart();
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        jobRecycler.setVisibility(View.VISIBLE);
        manageApplicationAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        manageApplicationAdapter.stopListening();
    }
}