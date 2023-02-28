package com.project.m_ajira.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.project.m_ajira.R;

public class DetailsActivity extends AppCompatActivity {
    TextView placeTxt,phoneTxt,ratesTxt,dateTxt,titleTxt,ownerTtx,descTxt,emailTxt;
    AppCompatButton applyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        placeTxt =findViewById(R.id.place_tv);
        phoneTxt =findViewById(R.id.phone_tv);
        ratesTxt =findViewById(R.id.rates_tv);
        dateTxt =findViewById(R.id.date_tv);
        titleTxt =findViewById(R.id.title_tv);
        ownerTtx =findViewById(R.id.owner_tv);
        descTxt =findViewById(R.id.desc_tv);
        emailTxt =findViewById(R.id.email_tv);
        applyBtn = findViewById(R.id.applicationBt);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, ApplicationActivity.class);
                intent.putExtra("title",titleTxt.getText().toString());
                intent.putExtra("uid",emailTxt.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        placeTxt.setText(getIntent().getExtras().getString("place"));
        phoneTxt.setText(getIntent().getExtras().getString("phone"));
        ratesTxt.setText(getIntent().getExtras().getString("rate"));
        dateTxt.setText(getIntent().getExtras().getString("date"));
        titleTxt.setText(getIntent().getExtras().getString("title"));
        ownerTtx.setText(getIntent().getExtras().getString("owner"));
        descTxt.setText(getIntent().getExtras().getString("desc"));
        emailTxt.setText(getIntent().getExtras().getString("email"));
    }
}