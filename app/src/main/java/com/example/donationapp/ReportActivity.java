package com.example.donationapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    TextView msgText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        String msg = getIntent().getExtras().getString("myMSG");

        msgText = findViewById(R.id.thankyou_msg);
        msgText.setText(msg);
    }
}