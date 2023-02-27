package com.project.m_ajira.ui.manage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Adapters.LabourAdapterManage;
import com.project.m_ajira.Adapters.UsersAdapterManage;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.Model.ProfileModel;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentManageBinding;

public class ManageFragment extends Fragment {
    LabourAdapterManage labourAdapterManage;
    UsersAdapterManage usersAdapterManage;
    RecyclerView labourRv, usersRv;
    private FragmentManageBinding binding;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        labourRv= root.findViewById(R.id.labourRvM);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        labourRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        labourRv.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<LabourModel> context = new FirebaseRecyclerOptions.Builder<LabourModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("JobsPosted"),LabourModel.class)
                .build();

        labourAdapterManage = new LabourAdapterManage(context,getContext());
        labourRv.setAdapter(labourAdapterManage);

        //Users Profile
        usersRv= root.findViewById(R.id.usersRvM);
        LinearLayoutManager layoutManager1 =new LinearLayoutManager(getContext());
        layoutManager1.setReverseLayout(true);
        usersRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        usersRv.setLayoutManager(layoutManager1);

        FirebaseRecyclerOptions<ProfileModel> profile = new FirebaseRecyclerOptions.Builder<ProfileModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("UserProfiles"),ProfileModel.class)
                .build();

        usersAdapterManage = new UsersAdapterManage(profile,getContext());
        usersRv.setAdapter(usersAdapterManage);


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
        labourAdapterManage.startListening();
        usersAdapterManage.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        labourAdapterManage.stopListening();
        usersAdapterManage.stopListening();
    }
}