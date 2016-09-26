package com.ajemian.cs175homework1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.support.v7.app.ActionBar.LayoutParams;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton mealsButton;
    private ImageButton recipesButton;
    private ImageButton groceriesButton;
    private ImageButton addButton;
    private ImageButton bannerButton;


    private PopupWindow aboutWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mealsButton = (ImageButton)findViewById(R.id.mealsButton);
        recipesButton = (ImageButton)findViewById(R.id.recipesButton);
        groceriesButton = (ImageButton)findViewById(R.id.groceriesButton);
        addButton = (ImageButton)findViewById(R.id.addButton);
        bannerButton = (ImageButton)findViewById(R.id.bannerButton);


        mealsButton.setOnClickListener(this);
        recipesButton.setOnClickListener(this);
        groceriesButton.setOnClickListener(this);
        addButton.setOnClickListener(this);




        aboutWindow = new PopupWindow(this);
        LayoutInflater inflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        aboutWindow = new PopupWindow(
                inflater.inflate(R.layout.popup_about, null, false),
                100,
                100,
                true);

        bannerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup_about, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                popupView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        popupWindow.dismiss();
                    }
                });
            }
        });




    }
    @Override
    public void onClick(View v){
        int viewId = v.getId();
        if(viewId == R.id.bannerButton) {
                /* Use pop up window here */
        }else if(viewId == R.id.mealsButton ) {
            Intent mealIntent = new Intent(this, MealsActivity.class);
            startActivity(mealIntent);
        }else if(viewId == R.id.groceriesButton) {
            Intent groceriesIntent = new Intent(this, GroceriesActivity.class);
            startActivity(groceriesIntent);
        }else if (viewId == R.id.recipesButton){
            Intent recipesIntent = new Intent(this, RecipesActivity.class);
            startActivity(recipesIntent);
        }else if(viewId == R.id.addButton){
            Intent addIntent = new Intent(this, AddActivity.class);
            startActivity(addIntent);
        }else if(viewId == R.id.bannerButton){
            //aboutWindow.showAtLocation(this, Gravity.CENTER, 0 ,0);
        }

    }

}
