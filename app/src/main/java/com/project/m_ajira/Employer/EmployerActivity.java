package com.project.m_ajira.Employer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}