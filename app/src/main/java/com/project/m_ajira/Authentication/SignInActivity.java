package com.project.m_ajira.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.m_ajira.Activities.MainActivity;
import com.project.m_ajira.R;

public class SignInActivity extends AppCompatActivity {
    ProgressBar progressBar;
    EditText EditTextEmail,EditTextPassword;
    boolean passwordVisible;

    private FirebaseAuth mAuth;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        loginBtn = findViewById(R.id.appCompatButton2);
        EditTextEmail = findViewById(R.id.editText2);
        EditTextPassword = findViewById(R.id.editText3);
        progressBar = findViewById(R.id.progressBar2);
        preferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        if (preferences.contains("saved_email")){
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }else {

            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = EditTextEmail.getText().toString().trim();
                    String password = EditTextPassword.getText().toString().trim();

                    if (email.isEmpty()) {
                        EditTextEmail.setError(" email is required!!");
                        EditTextEmail.requestFocus();
                        return;
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        EditTextEmail.setError("Please provide a valid email address!");
                        EditTextEmail.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        EditTextPassword.setError(" password is required!!");
                        EditTextPassword.requestFocus();
                        return;
                    }
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (mAuth.getCurrentUser().isEmailVerified()) {
                                    editor.putString("saved_email", email);
                                    editor.putString("saved_pass", password);
                                    editor.commit();
                                    startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                    Toast.makeText(SignInActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(SignInActivity.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignInActivity.this, "Failed to log in check your credentials and Internet connection", Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    });

                }
            });
        }

        // Hiding password and showing
        EditTextPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if (event.getAction()==MotionEvent.ACTION_UP){
                    if (event.getRawX()>=EditTextPassword.getRight()-EditTextPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=EditTextPassword.getSelectionEnd();
                        if (passwordVisible){
                            //show password
                            EditTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_off_24,0);
                            //hide password
                            EditTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;

                        }else {
                            //show password
                            EditTextPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visibility_24,0);
                            //show password
                            EditTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;

                        }
                        EditTextPassword.setSelection(selection);
                        return  true;

                    }
                }
                return false;
            }
        });
    }

    public void register(View view) {
        Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(i);
        finish();

    }

    public void password(View view) {
        AlertDialog.Builder builder= new AlertDialog.Builder(SignInActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot,null);
        EditText emailBox = dialogView.findViewById(R.id.emailBox);


        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = emailBox.getText().toString();
                if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    Toast.makeText(SignInActivity.this,"Enter your registered email address",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignInActivity.this,"Check your email",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(SignInActivity.this,"Unable to send, Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        dialogView.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (dialog.getWindow() != null){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        dialog.show();
    }
}