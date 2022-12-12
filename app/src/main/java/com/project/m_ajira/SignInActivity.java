package com.project.m_ajira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.m_ajira.ui.home.HomeFragment;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void register(View view) {
        Intent i = new Intent(SignInActivity.this,SignUpActivity.class);
        startActivity(i);
        finish();
    }

    public void login(View view) {
        Intent i = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}