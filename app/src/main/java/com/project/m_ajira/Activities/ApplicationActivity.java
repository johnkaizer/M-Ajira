package com.project.m_ajira.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.Activities.MainActivity;
import com.project.m_ajira.Model.ApplicationModel;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;

public class ApplicationActivity extends AppCompatActivity {
    TextView jobTxt,ownerEmail;
    EditText nameEt,phoneEt,ratesEt,descEt,locationEt,reasonEt;
    DatabaseReference reference;
    DatabaseReference dataRef;
    String userID;
    private FirebaseUser user;
    AppCompatButton btnApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        nameEt = findViewById(R.id.owner);
        phoneEt = findViewById(R.id.phone);
        ratesEt = findViewById(R.id.rates);
        descEt = findViewById(R.id.description);
        locationEt = findViewById(R.id.location);
        reasonEt = findViewById(R.id.reason);
        jobTxt = findViewById(R.id.jobTxt);
        ownerEmail = findViewById(R.id.uidTvt);
        btnApplication = findViewById(R.id.jobAppBtn);
        dataRef= FirebaseDatabase.getInstance().getReference().child("JobApplication");
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        jobTxt.setText(getIntent().getExtras().getString("title"));
        ownerEmail.setText(getIntent().getExtras().getString("uid"));
        btnApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        String JobTitle =jobTxt.getText().toString();
        String OwnerEmail =ownerEmail.getText().toString();
        String AppName =nameEt.getText().toString();
        String AppEmail =phoneEt.getText().toString();
        String AppRates =ratesEt.getText().toString();
        String AppDesc =descEt.getText().toString();
        String AppLoc =locationEt.getText().toString();
        String AppReason =reasonEt.getText().toString();
        String AppId =userID;
        ApplicationModel applicationModel = new ApplicationModel(JobTitle,OwnerEmail,AppName,AppEmail,AppRates,AppDesc,AppLoc,AppReason,AppId);
        dataRef.push().setValue(applicationModel);
        Toast.makeText(this,"Successfully applied job post",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}