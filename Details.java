package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {


    TextView mealDescription, mealLink;
    ImageView mealImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mealDescription = (TextView)findViewById(R.id.txtDescription);
        mealImage = (ImageView)findViewById(R.id.ivImage);
        mealLink = (TextView)findViewById(R.id.txtLink);


        Bundle mBundle = getIntent().getExtras();

//        Intent intent = getIntent();
//
//
//        byte[] bytes = intent.getByteArrayExtra("Image");
//        assert bytes != null;
//        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        mealImage.setImageBitmap(Global.img);


        if(mBundle != null){

            mealLink.setText(mBundle.getString("LINK"));
            mealDescription.setText(mBundle.getString("Description"));

        }


    }
}
