package com.project.m_ajira.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.project.m_ajira.Activities.MainActivity;
import com.project.m_ajira.R;
import com.project.m_ajira.databinding.FragmentProfileBinding;

import java.util.HashMap;

public class Profile extends Fragment {
    FragmentProfileBinding binding;
    EditText nameEt,phoneEt,ratesEt,dobEt,emailEt,currentHome, homeTown,educationEt;
    ProgressBar progressBar;
    Spinner genderSpinner, skillsSpinner;
    AppCompatButton submitBtn,uploadBtn;
    ImageView userImage;
    private FirebaseUser user;
    DatabaseReference reference;
    DatabaseReference dataRef;
    StorageReference storageRef;
    String userID;
    public static final int REQUEST_CODE_IMAGE=101;
    Uri imageUri;
    boolean isImageAdded= false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Edit texts
        nameEt = root.findViewById(R.id.full_name);
        phoneEt = root.findViewById(R.id.phone);
        ratesEt = root.findViewById(R.id.rates);
        dobEt = root.findViewById(R.id.dob);
        emailEt = root.findViewById(R.id.user_email);
        currentHome = root.findViewById(R.id.current);
        homeTown = root.findViewById(R.id.home_town);
        educationEt = root.findViewById(R.id.education);
        //loading progress bar
        progressBar = root.findViewById(R.id.progressBar2);
        //Spinners for gender and skill
        genderSpinner = root.findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext() ,R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        skillsSpinner = root.findViewById(R.id.skills_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext() ,R.array.skills, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillsSpinner.setAdapter(adapter1);
        //imageView
        userImage =root.findViewById(R.id.imageV);
        //buttons
        submitBtn = root.findViewById(R.id.submitBt);
        uploadBtn = root.findViewById(R.id.uploadBtn);
        //Firebase Paths
        dataRef= FirebaseDatabase.getInstance().getReference().child("UserProfiles");
        storageRef= FirebaseStorage.getInstance().getReference().child("UserImages");
        reference = FirebaseDatabase.getInstance().getReference("Users");
        //Getting logged in user ID
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String FullName= nameEt.getText().toString();
                final String DOB =dobEt.getText().toString();
                final String Phone =phoneEt.getText().toString();
                final String Email = emailEt.getText().toString();
                final String Home = homeTown.getText().toString();
                final String CurrentHome= currentHome.getText().toString();
                final String Gender = genderSpinner.getSelectedItem().toString();
                final String Skill = skillsSpinner.getSelectedItem().toString();
                final String Rate = ratesEt.getText().toString();
                final String Education = educationEt.getText().toString();
                final String userId = userID;

                if (isImageAdded!=false && FullName!=null && DOB!=null && Phone!=null && Email!=null  && Home!=null && CurrentHome!=null && Gender!=null && Skill!=null && Rate!=null && Education!=null && userId!=null ){
                    saveApplication(FullName,DOB,Phone,Email,Home,CurrentHome,Gender,Skill,Rate,Education,userId);
                }

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);

            }
        });


        return root;
    }

    private void saveApplication(String fullName, String dob, String phone, String email, String home, String currentHome, String gender, String skill, String rate, String education, String userId) {
        progressBar.setVisibility(View.VISIBLE);

        final String key = dataRef.push().getKey();
        storageRef.child(key +".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storageRef.child(key +".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        HashMap hashMap=new HashMap();
                        hashMap.put("UserName",fullName);
                        hashMap.put("UserDob",dob);
                        hashMap.put("UserPhone",phone);
                        hashMap.put("UserEmail",email);
                        hashMap.put("UserID", userId);
                        hashMap.put("UserHome",home);
                        hashMap.put("CurrentHome",currentHome);
                        hashMap.put("Gender",gender);
                        hashMap.put("Skill",skill);
                        hashMap.put("UserRates",rate);
                        hashMap.put("Education",education);
                        hashMap.put("ImageUrl",uri.toString());

                        dataRef.child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getContext(), "Data was successfully uploaded", Toast.LENGTH_SHORT).show();
                                emailEt.setText("");
                                phoneEt.setText("");
                                nameEt.setText("");
                                ratesEt.setText("");
                                educationEt.setText("");
                                homeTown.setText("");
                                userImage.setImageResource(R.drawable.image_24);
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(getContext(), MainActivity.class));

                            }
                        });
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress =snapshot.getBytesTransferred()*100/snapshot.getTotalByteCount();
                progressBar.setProgress((int) progress);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_IMAGE && data != null) {
            imageUri = data.getData();
            isImageAdded = true;
            userImage.setImageURI(imageUri);

        }
    }
}