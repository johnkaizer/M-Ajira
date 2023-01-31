package com.project.m_ajira.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.project.m_ajira.MainActivity;
import com.project.m_ajira.Model.User;
import com.project.m_ajira.R;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextName,editTextEmail,editTextPhone, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = findViewById(R.id.editText2);
        editTextEmail = findViewById(R.id.editText3);
        editTextPhone = findViewById(R.id.editText4);
        editTextPassword = findViewById(R.id.editText5);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    public void home(View view) {
        String email =editTextEmail.getText().toString().trim();
        String fullName =editTextName.getText().toString().trim();
        String phone =editTextPhone.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();

        if (fullName.isEmpty()){
            editTextName.setError("Full name is required!!");
            editTextName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Email is required!!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email address!");
            editTextEmail.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            editTextPhone.setError("Phone number is required!");
            editTextPhone.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName,email,phone,password);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                                        Toast.makeText(SignUpActivity.this,"Registered successfully!, Please verify email",Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }
                                                });
                                            }else {
                                                Toast.makeText(SignUpActivity.this,"failed to register user please try again",Toast.LENGTH_SHORT).show();
                                            }
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                        }else {
                            Toast.makeText(SignUpActivity.this,"failed to register user ",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
    }

    public void login(View view) {
        Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(i);
        finish();

    }
}