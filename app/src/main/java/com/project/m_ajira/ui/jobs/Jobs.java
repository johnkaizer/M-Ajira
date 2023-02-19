package com.project.m_ajira.ui.jobs;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Activities.MainActivity;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentJobsBinding;

import java.text.DateFormat;
import java.util.Calendar;

public class Jobs extends Fragment {

   FragmentJobsBinding binding;
    EditText nameEt,phoneEt,ratesEt,roleEt,locationEt;
    Spinner titleSpinner;
    AppCompatButton submit;
    TextView tDate;
    ProgressBar progressBar;
    private FirebaseUser user;
    DatabaseReference reference;
    DatabaseReference dataRef;
    String userID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentJobsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Edit texts
        nameEt = root.findViewById(R.id.owner);
        phoneEt = root.findViewById(R.id.phone);
        ratesEt = root.findViewById(R.id.rates);
        roleEt = root.findViewById(R.id.description);
        locationEt = root.findViewById(R.id.location);
        //Spinner
        titleSpinner = root.findViewById(R.id.title_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext() ,R.array.jobs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        titleSpinner.setAdapter(adapter);
        //submit btn
        submit = root.findViewById(R.id.appCompatButton2);
        //loading progress bar
        progressBar = root.findViewById(R.id.progressBar1);
        //Today's Date textview
        tDate = root.findViewById(R.id.textView9);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        tDate.setText(currentDate);
        //Firebase Paths
        dataRef= FirebaseDatabase.getInstance().getReference().child("JobsPosted");
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        return root;
    }

    private void saveData() {
        String Title =titleSpinner.getSelectedItem().toString();
        String Description =roleEt.getText().toString();
        String Rates =ratesEt.getText().toString();
        String Owner =nameEt.getText().toString();
        String OwnerPhone =phoneEt.getText().toString();
        String OwnerId =userID;
        String Place =locationEt.getText().toString();
        String Created =tDate.getText().toString();

        LabourModel labourModel = new LabourModel(Title,Description,Rates,Owner,OwnerPhone,OwnerId,Place,Created);
        dataRef.push().setValue(labourModel);
        Toast.makeText(getContext(),"Successfully created job post",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}