package com.project.m_ajira.ui.bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.m_ajira.Adapters.AcceptedAdapter;
import com.project.m_ajira.Adapters.LabourAdapter;
import com.project.m_ajira.Model.AcceptedModel;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentBookingsBinding;

import java.util.ArrayList;


public class BookingsFragment extends Fragment {

    private FragmentBookingsBinding binding;
    Query databaseReference;
    RecyclerView accepted;
    AcceptedAdapter acceptedAdapter;
    ArrayList<AcceptedModel> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBookingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        accepted= root.findViewById(R.id.acceptedRV);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Confirmed");
        accepted.setHasFixedSize(true);
        accepted.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        acceptedAdapter = new AcceptedAdapter(getContext(), list);
        accepted.setAdapter(acceptedAdapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    AcceptedModel accepted= dataSnapshot.getValue(AcceptedModel.class);
                    list.add(accepted);
                }

                acceptedAdapter.notifyDataSetChanged();
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