package com.example.donationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

// multiple inhiretence
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener { // oop345

    RadioButton payPalButton;
    RadioButton creditButton;
    EditText amoutText;
    CheckBox sharingBox;
    Button donateButton;
    String paymentMethod;
    double amount;
    boolean isPublic = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        payPalButton = findViewById(R.id.paypal);
        creditButton = findViewById(R.id.credit_card);
        amoutText = findViewById(R.id.donation_amount);
        sharingBox = findViewById(R.id.sharing_checkbox);
        donateButton = findViewById(R.id.donate_btn);
        donateButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.donate_btn){
            if (payPalButton.isChecked() || creditButton.isChecked()){
                if (!amoutText.getText().toString().isEmpty()){
                    if (payPalButton.isChecked())
                        paymentMethod = "PayPal";
                    else
                        paymentMethod = "Credit Card";

                    // the user may enter "abc"
                   amount = Double.parseDouble(amoutText.getText().toString());
                   if (sharingBox.isChecked())
                       isPublic = true;

                   String thanksMsg = "Thank you for your" + amount + "$ donation; You used " + paymentMethod + " to finish your donation ";
                    if (sharingBox.isChecked()) {
                        isPublic = true;
                        thanksMsg += " Thanks for sharing your donation with others!!";
                    }

                    Intent intent = new Intent(this, ReportActivity.class);

                    intent.putExtra("myMSG", thanksMsg);

                    startActivity(intent);



                }else {
                    Toast.makeText(MainActivity.this, "You Have to enter an amount", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "You Have to select the payment method", Toast.LENGTH_SHORT).show();
            }

        }

    }
}