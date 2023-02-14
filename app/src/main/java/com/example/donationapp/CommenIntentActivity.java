package com.example.donationapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CommenIntentActivity extends AppCompatActivity {


    ImageView photoFromTheCamera;
    ActivityResultLauncher<Intent> cameraResultLauncher;
    Button takeAPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commen_intent);

        cameraResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    Bitmap thumbnail  = (Bitmap) data.getExtras().get("data");
                    photoFromTheCamera.setImageBitmap(thumbnail);
                }
            }
        });


        photoFromTheCamera =   findViewById(R.id.photoFromCamera);
        takeAPhoto = findViewById(R.id.takephoto);

        takeAPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraResultLauncher.launch(i);



//                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                intent.putExtra(SearchManager.QUERY, "Canada");
//                startActivity(intent);
            }
        });
    }
}