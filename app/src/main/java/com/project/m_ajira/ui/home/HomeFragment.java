package com.project.m_ajira.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Adapters.LabourAdapter;
import com.project.m_ajira.Adapters.UsersAdapter;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.Model.ProfileModel;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    LabourAdapter labourAdapter;
    UsersAdapter usersAdapter;
    RecyclerView labourRv, usersRv;
    private FragmentHomeBinding binding;
    ProgressBar progressBar;
    ShimmerFrameLayout shimmerFrameLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        labourRv= root.findViewById(R.id.labourRv);
        progressBar=root.findViewById(R.id.progressBar2);
        shimmerFrameLayout = root.findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        labourRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        labourRv.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<LabourModel> context = new FirebaseRecyclerOptions.Builder<LabourModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("JobsPosted"),LabourModel.class)
                .build();

        labourAdapter = new LabourAdapter(context,getContext());
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        labourRv.setVisibility(View.VISIBLE);
        labourRv.setAdapter(labourAdapter);

        //Users Profile
        usersRv= root.findViewById(R.id.usersRv);
        LinearLayoutManager layoutManager1 =new LinearLayoutManager(getContext());
        layoutManager1.setReverseLayout(true);
        usersRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        usersRv.setLayoutManager(layoutManager1);

        FirebaseRecyclerOptions<ProfileModel> profile = new FirebaseRecyclerOptions.Builder<ProfileModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("UserProfiles"),ProfileModel.class)
                .build();

        usersAdapter = new UsersAdapter(profile,getContext());
        usersRv.setAdapter(usersAdapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onStart() {
        super.onStart();
        labourAdapter.startListening();
        usersAdapter.startListening();
        if (progressBar !=null){
            progressBar.setVisibility(View.GONE);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        labourAdapter.stopListening();
        usersAdapter.stopListening();
    }


}