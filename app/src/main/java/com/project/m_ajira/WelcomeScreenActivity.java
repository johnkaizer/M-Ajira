package com.project.m_ajira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
    }

    public void Next(View view) {
        Intent i = new Intent(WelcomeScreenActivity.this,OnboardingScreensActivity.class);
        startActivity(i);
        finish();

    }
}