package com.example.mybooksfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera extends AppCompatActivity {

    //variables
    ImageView image;
    Button takePicbtn, backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        image = findViewById(R.id.cameraImage);
        takePicbtn = findViewById(R.id.takePicturebtn);
        backbtn= findViewById(R.id.backButton1);


        takePicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(Camera.this, AddBookScreen.class);
                startActivity(backIntent);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        try{
            super.onActivityResult(requestCode, resultCode,data);

            //image type
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bm);
            Toast.makeText(this, "Your Image has been Saved", Toast.LENGTH_SHORT).show();

        }catch(Exception ex){

            Toast.makeText(this, "Unable To Take Picture", Toast.LENGTH_SHORT).show();
        }
    }
}
