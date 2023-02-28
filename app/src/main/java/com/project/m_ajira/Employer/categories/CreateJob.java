package com.project.m_ajira.Employer.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.text.DateFormat;
import java.util.Calendar;

public class CreateJob extends AppCompatActivity {
    EditText nameEt,phoneEt,ratesEt,roleEt,locationEt;
    Spinner titleSpinner;
    AppCompatButton submit;
    TextView tDate;
    ProgressBar progressBar;
    private FirebaseUser user;
    DatabaseReference reference;
    DatabaseReference dataRef;
    String userID;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        //Edit texts
        nameEt = findViewById(R.id.owner);
        phoneEt = findViewById(R.id.phone);
        ratesEt = findViewById(R.id.rates);
        roleEt = findViewById(R.id.description);
        locationEt = findViewById(R.id.location);
        //Spinner
        titleSpinner = findViewById(R.id.title_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this ,R.array.jobs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        titleSpinner.setAdapter(adapter);
        //submit btn
        submit = findViewById(R.id.appCompatButton2);
        //loading progress bar
        progressBar =findViewById(R.id.progressBar1);
        //Today's Date textview
        tDate = findViewById(R.id.textView9);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        tDate.setText(currentDate);
        //Firebase Paths
        dataRef= FirebaseDatabase.getInstance().getReference().child("JobsPosted");
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        userEmail = user.getEmail();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String Title =titleSpinner.getSelectedItem().toString();
        String Description =roleEt.getText().toString();
        String Rates =ratesEt.getText().toString();
        String Owner =nameEt.getText().toString();
        String OwnerPhone =phoneEt.getText().toString();
        String OwnerId =userID;
        String OwnerEmail = userEmail;
        String Place =locationEt.getText().toString();
        String Created =tDate.getText().toString();

        LabourModel labourModel = new LabourModel(Title,Description,Rates,Owner,OwnerEmail,OwnerPhone,OwnerId,Place,Created);
        dataRef.push().setValue(labourModel);
        Toast.makeText(this,"Successfully created job post",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ManageJob.class);
        startActivity(intent);
    }

}