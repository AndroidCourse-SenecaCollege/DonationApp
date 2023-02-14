package com.example.donationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

// multiple inhiretence
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener { // oop345
// change of device configration means recreate the activity
// ==> losing the activity state
    // any logic should be removed from the activity class

    RadioButton payPalButton;
    RadioButton creditButton;
    EditText amoutText;
    CheckBox sharingBox;
    Button donateButton;
    String paymentMethod;
    Spinner paymentMethod_spinner;
    double amount;
    boolean isPublic = false;

    Button toCommonIntentActivity;
    int donationButtonClicked = 0;

    // linked list, queue.

    // This is not a good practice
    // You have to usee an Application class for globale variables
   // ArrayList<Donation> allDonations = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null ){
            donationButtonClicked = savedInstanceState.getInt("numOfClicks");
        }

        String[] paymentMethods = {"PayPal", "Credit Card"};// data source is a string array


        paymentMethod_spinner = findViewById(R.id.paymentMethodSpinner);
        payPalButton = findViewById(R.id.paypal);
        creditButton = findViewById(R.id.credit_card);
        amoutText = findViewById(R.id.donation_amount);
        sharingBox = findViewById(R.id.sharing_checkbox);
        donateButton = findViewById(R.id.donate_btn);
        donateButton.setOnClickListener(this);
        toCommonIntentActivity = findViewById(R.id.tocommonIntent);
        toCommonIntentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CommenIntentActivity.class);
                startActivity(i);
            }
        });
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_row,
                R.id.spinner_paymentMethod_text,
                paymentMethods);

        paymentMethod_spinner.setAdapter(spinnerAdapter);

        paymentMethod_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "The selected Payment Method is " + paymentMethods[i] , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "No Selection" , Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.donate_btn){
            donationButtonClicked++;
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
                   else
                       isPublic = false;

                   Donation lastDonation = new Donation(paymentMethod, amount,isPublic);
                    ((MyApp)getApplication()).allDonations.add(lastDonation);
                   String thanksMsg = "Thank you for your" + amount + "$ donation; You used " + paymentMethod + " to finish your donation ";
                    if (sharingBox.isChecked()) {
                        isPublic = true;
                        thanksMsg += " Thanks for sharing your donation with others!!";
                    }

                    Intent intent = new Intent(this, ReportActivity.class);


                    intent.putExtra("myMSG", thanksMsg);
                    intent.putExtra("lastDonation", lastDonation );
                    intent.putExtra("allDonation",((MyApp)getApplication()).allDonations );
                   // startActivityFo
                    startActivity(intent);

                }else {
                    Toast.makeText(MainActivity.this, "You Have to enter an amount", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "You Have to select the payment method", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // I can save some values of my app state
        outState.putInt("numOfClicks", donationButtonClicked);
    }
}