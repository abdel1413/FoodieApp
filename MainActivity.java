package com.example.foodieapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecycleView;
    List<MealItem> myMealItemList;
    MealItem mMealItem;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = (RecyclerView)findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecycleView.setLayoutManager(gridLayoutManager);

        myMealItemList = new ArrayList<>();

        //===========================================================================================================


        addDefaultMeal("Spicy Peanut Chicken", "This colorful stir-fry gets its kick from a dash of red pepper.", R.drawable.spicy_rice,"For more information visit: https://www.bettycrocker.com/recipes/spicy-peanut-chicken/4d432df6-a1a8-499e-8a43-b0703ffe8800" );

       addDefaultMeal("Santa Fe Chicken Skillet","With zesty spiced chicken breasts, black beans and a rainbow of vegetables, including corn, green chiles, onion, peppers and zucchini, this skillet makes for a satisfying and well-balanced family meal. The double dose of protein, from beans and chicken, ensures even hearty eaters will get their fill, while those watching their calories can enjoy this meal without a second thought. For a perfect finish, top this meal with a squeeze of fresh lime, sprinkle of chopped cilantro and a crumble of queso fresco!",
               R.drawable.santa_fe, "For more information visit: https://www.bettycrocker.com/recipes/santa-fe-chicken-skillet/3a0a60dd-1881-4799-a910-dc4b57d6920d" );


        addDefaultMeal("Classic Meatballs", "Why reinvent the wheel? For generations, home cooks have relied on this classic meatball recipe for its infallibility and ease—and when we say they’re easy, we really mean it. ", R.drawable.meat_ball,"For more information visit: https://www.bettycrocker.com/recipes/classic-meatballs/2959910f-1b27-438a-9085-d40b1950db20" );
       addDefaultMeal("Strawberry Frosted Layer Cake", "The star of this beautiful cake is the fresh strawberry buttercream frosting that’s bursting with summer flavor. As impressive as it looks, this cake is made easy with Betty Crocker™ Super Moist™ white cake mix.", R.drawable.cake,"For more information visit: https://www.bettycrocker.com/recipes/strawberry-frosted-layer-cake/3d687c23-1c56-49d4-85ed-bd190441a284" );



        try {
            addMealReciever();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }


        MealItemAdapter mealItemAdapter = new MealItemAdapter(MainActivity.this, myMealItemList);
        mRecycleView.setAdapter(mealItemAdapter);

    }

    public void addDefaultMeal(String name, String description, int imageRidResource, String link){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageRidResource);
        Drawable drawable = new BitmapDrawable(getResources(), bitmap);

        myMealItemList.add(new MealItem(name,description,drawable,link));

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void addMealReciever() throws FileNotFoundException, URISyntaxException, MalformedURLException {

        try {

            Intent intent = getIntent();
            String description_path = intent.getStringExtra("DESCRIPTION");
            String name_path = intent.getStringExtra("NAME");
            String image_path = intent.getStringExtra("IMG");
            String link_path = intent.getStringExtra("LINK");
            Uri fileUri = Uri.parse(image_path);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
            Drawable drawableTest = new BitmapDrawable(getResources(), bitmap);

            myMealItemList.add(new MealItem(name_path,description_path,
                    drawableTest,link_path));




        }catch (NullPointerException | IOException e){

            Log.d("tagname","string you want to execute");
        }
    }

    public void btn_addActivity(View view) {

        startActivity(new Intent(this, AddMeal.class));
    }
}
