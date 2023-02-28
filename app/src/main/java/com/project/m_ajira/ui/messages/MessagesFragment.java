package com.project.m_ajira.ui.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.project.m_ajira.Adapters.ApplicationAdapter;
import com.project.m_ajira.Model.ApplicationModel;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentMessagesBinding;

import java.util.ArrayList;


public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;
    RecyclerView appRV;
    ArrayList<ApplicationModel>list;
    ApplicationAdapter applicationAdapter;
    Query databaseReference;
    private FirebaseUser user;
    DatabaseReference reference;
    String userIdentity;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        appRV = root.findViewById(R.id.applicationRv);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userIdentity = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("JobApplication").orderByChild("userUid").startAt(userIdentity);
        appRV.setHasFixedSize(true);
        appRV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
        list = new ArrayList<>();
        applicationAdapter = new ApplicationAdapter(getContext(),list);
        appRV.setAdapter(applicationAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    ApplicationModel  applicationModel = dataSnapshot.getValue(ApplicationModel.class);
                    list.add(applicationModel);
                }
                applicationAdapter.notifyDataSetChanged();
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