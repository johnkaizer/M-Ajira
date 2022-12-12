package com.project.m_ajira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void home(View view) {
        Intent i = new Intent(SignUpActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void login(View view) {
        Intent i = new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(i);
        finish();

    }
}