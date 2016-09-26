package com.ajemian.cs175homework1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


public class MealsActivity  extends AppCompatActivity implements View.OnClickListener{


    private TextView dayLabel;
    private Button prevButton;
    private Button nextButton;
    private Button nutritionButton;

    private Spinner breakfastSpinner;
    private Spinner lunchSpinner;
    private Spinner dinnerSpinner;

    private List<String> recipes;

    private int currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        recipes = WFDSingleton.getInstance().getRecipes();

        currentDay = WFDSingleton.getInstance().getDay();

        dayLabel = (TextView)findViewById(R.id.dayOfTheWeek);


        prevButton = (Button)findViewById(R.id.prevButton);
        nextButton = (Button)findViewById(R.id.nextButton);
        nutritionButton = (Button) findViewById(R.id.activity_nutrition_manager);

        breakfastSpinner = (Spinner) findViewById(R.id.breakfastSpinner);
        lunchSpinner = (Spinner) findViewById(R.id.lunchSpinner);
        dinnerSpinner = (Spinner) findViewById(R.id.dinnerSpinner);

        breakfastSpinner.setOnItemSelectedListener(selectionListener);
        lunchSpinner.setOnItemSelectedListener(selectionListener);
        dinnerSpinner.setOnItemSelectedListener(selectionListener);
        updateDay();

        prevButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        nutritionButton.setOnClickListener(this);
    }

    protected String getCurrentDay(int index){
        return getResources().getStringArray(R.array.days)[currentDay];
    }

    protected AdapterView.OnItemSelectedListener selectionListener = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            if(parentView.getId() == R.id.breakfastSpinner){
                WFDSingleton.getInstance().setMeal(WFDSingleton.TypeEat.Breakfast, getCurrentDay(currentDay), recipes.get(position));
            }else if (parentView.getId() == R.id.lunchSpinner){
                WFDSingleton.getInstance().setMeal(WFDSingleton.TypeEat.Lunch, getCurrentDay(currentDay), recipes.get(position));
            }else if (parentView.getId() == R.id.dinnerSpinner){
                WFDSingleton.getInstance().setMeal(WFDSingleton.TypeEat.Dinner, getCurrentDay(currentDay), recipes.get(position));
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }

    };

    protected int getIndexOfRecipe(String recipe){
        for(int i=0; i<recipes.size(); i++){
            if(recipes.get(i).equals(recipe)) return i;
        }
        return 0;
    }
    /* Call this with every change of the day */
    protected void updateDay(){

        dayLabel.setText(getResources().getStringArray(R.array.days)[currentDay]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, recipes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        breakfastSpinner.setAdapter(adapter);
        lunchSpinner.setAdapter(adapter);
        dinnerSpinner.setAdapter(adapter);

        WFDSingleton singleton = WFDSingleton.getInstance();
        /* Just get him OUT HERE */

        breakfastSpinner.setSelection(getIndexOfRecipe(singleton.getBreakfastWithDay(getCurrentDay(currentDay))));
        lunchSpinner.setSelection(getIndexOfRecipe(singleton.getLunchWithDay(getCurrentDay(currentDay))));
        dinnerSpinner.setSelection(getIndexOfRecipe(singleton.getDinnerWithDay(getCurrentDay(currentDay))));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        currentDay = savedInstanceState.getInt("DAY", 0); /* Monday by default */



    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("DAY", currentDay);
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.nextButton){
            if(currentDay < getResources().getStringArray(R.array.days).length - 1){
                currentDay++;
            }else{
                currentDay = 0;
            }

        }else if(v.getId() == R.id.prevButton){
            if(currentDay > 0){
                currentDay--;
            }else{
                currentDay = getResources().getStringArray(R.array.days).length - 1;
            }
        }
        if(v.getId() == R.id.activity_nutrition_manager) {
            Intent nutritionIntent = new Intent(this, NutritionManager.class);
            startActivity(nutritionIntent);
        }
        WFDSingleton.getInstance().setDay(currentDay);
        updateDay();
    }
}
