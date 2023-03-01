package com.project.m_ajira.Employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.project.m_ajira.Activities.MainActivity;
import com.project.m_ajira.Activities.ProfileActivity;
import com.project.m_ajira.Authentication.SignInActivity;
import com.project.m_ajira.Employer.categories.CreateJob;
import com.project.m_ajira.Employer.categories.JobSeekers;
import com.project.m_ajira.Employer.categories.ManageApplication;
import com.project.m_ajira.Employer.categories.ManageJob;
import com.project.m_ajira.R;

public class EmployerActivity extends AppCompatActivity {
    private CardView card1, card2, card3, card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer);
        card1 =findViewById(R.id.c1);
        card2 = findViewById(R.id.c2);
        card3 = findViewById(R.id.c3);
        card4 = findViewById(R.id.c4);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployerActivity.this, CreateJob.class));

            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployerActivity.this, ManageJob.class));

            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployerActivity.this, ManageApplication.class));

            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployerActivity.this, JobSeekers.class));

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_app){

            return true;
        }
        else if (id == R.id.action_profile){
            startActivity(new Intent(EmployerActivity.this, ProfileActivity.class));
            return true;
        }
        else if (id == R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(EmployerActivity.this, SignInActivity.class));
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}