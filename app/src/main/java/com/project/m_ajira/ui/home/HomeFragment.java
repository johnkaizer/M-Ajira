package com.project.m_ajira.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Adapters.LabourAdapter;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    LabourAdapter labourAdapter;
    RecyclerView labourRv;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        labourRv= root.findViewById(R.id.labourRv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.getStackFromEnd();
        labourRv.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        labourRv.setLayoutManager(layoutManager);

        FirebaseRecyclerOptions<LabourModel> context = new FirebaseRecyclerOptions.Builder<LabourModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("JobsPosted"),LabourModel.class)
                .build();

        labourAdapter = new LabourAdapter(context,getContext());
        labourRv.setAdapter(labourAdapter);


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
    }
    @Override
    public void onStop() {
        super.onStop();
        labourAdapter.stopListening();
    }
}