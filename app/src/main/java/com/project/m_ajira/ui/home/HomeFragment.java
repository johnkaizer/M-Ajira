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
    ArrayList<LabourModel>list;
    RecyclerView labourRv;
    private FragmentHomeBinding binding;
    ShimmerFrameLayout shimmerFrameLayout;
    Query databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        labourRv= root.findViewById(R.id.labourRv);
        shimmerFrameLayout = root.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("JobsPosted");
        labourRv.setHasFixedSize(true);
        labourRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}