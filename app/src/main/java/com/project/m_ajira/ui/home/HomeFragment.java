package com.project.m_ajira.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.m_ajira.Adapters.LabourAdapter;
import com.project.m_ajira.Adapters.LabourAdapterManage;
import com.project.m_ajira.Adapters.UsersAdapter;
import com.project.m_ajira.Adapters.UsersAdapterManage;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.Model.ProfileModel;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    LabourAdapter labourAdapter;
    UsersAdapter usersAdapter;
    ArrayList<LabourModel>list;
    ArrayList<ProfileModel>list1;
    RecyclerView labourRv, usersRv;
    private FragmentHomeBinding binding;
    ShimmerFrameLayout shimmerFrameLayout,shimmerFrameLayout1;
    Query databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        labourRv= root.findViewById(R.id.labourRv);
        usersRv= root.findViewById(R.id.usersRv);
        shimmerFrameLayout = root.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("JobsPosted");
        labourRv.setHasFixedSize(true);
        labourRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL, false));
        list = new ArrayList<>();
        labourAdapter = new LabourAdapter(getContext(), list);
        labourRv.setAdapter(labourAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    LabourModel labourModel= dataSnapshot.getValue(LabourModel.class);
                    list.add(labourModel);
                }
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                labourRv.setVisibility(View.VISIBLE);
                labourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        ////USERS
        shimmerFrameLayout1 = root.findViewById(R.id.shimmer1);
        shimmerFrameLayout1.startShimmer();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserProfiles");
        usersRv.setHasFixedSize(true);
        usersRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
        list1 = new ArrayList<>();
        usersAdapter = new UsersAdapter(getContext(), list1);
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


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}