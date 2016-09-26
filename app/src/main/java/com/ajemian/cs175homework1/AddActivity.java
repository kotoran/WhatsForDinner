package com.ajemian.cs175homework1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.LinkedList;


public class AddActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{
    private Spinner[] ingredientWidgets;

    private Button addButton;
    private EditText recipeName;
    private EditText recipeDirection;

    private WFDSingleton singleton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ingredientWidgets = new Spinner[10];

        ingredientWidgets[0] = (Spinner) findViewById(R.id.ingredient_one);
        ingredientWidgets[1] = (Spinner) findViewById(R.id.ingredient_two);
        ingredientWidgets[2] = (Spinner) findViewById(R.id.ingredient_three);
        ingredientWidgets[3] = (Spinner) findViewById(R.id.ingredient_four);
        ingredientWidgets[4] = (Spinner) findViewById(R.id.ingredient_five);
        ingredientWidgets[5] = (Spinner) findViewById(R.id.ingredient_six);
        ingredientWidgets[6] = (Spinner) findViewById(R.id.ingredient_seven);
        ingredientWidgets[7] = (Spinner) findViewById(R.id.ingredient_eight);
        ingredientWidgets[8] = (Spinner) findViewById(R.id.ingredient_nine);
        ingredientWidgets[9] = (Spinner) findViewById(R.id.ingredient_ten);


        for (int i = 0; i < ingredientWidgets.length; i++)
        {
            ingredientWidgets[i].setOnTouchListener(this);
        }
        recipeName = (EditText) findViewById(R.id.recipeNameEditText);
        recipeDirection = (EditText) findViewById(R.id.recipeDirections);

        addButton = (Button) findViewById(R.id.addButton);;
        singleton = WFDSingleton.getInstance();


        addButton.setOnClickListener(this);
        LinearLayout outerLayout = (LinearLayout) findViewById(R.id.outerLayout);
        outerLayout.setOnClickListener(this);

        String editItem = getIntent().getStringExtra("Edit");
        /* Now make it an editing item */
        if (editItem != null)
        {
            recipeName.setText(editItem);
            recipeDirection.setText(WFDSingleton.getInstance().getDirections(editItem));
            LinkedList<String> ingredients = WFDSingleton.getInstance().getIngredients(editItem);
            /* Yucky algorithm! */
            String[] availableIngredients = getResources().getStringArray(R.array.ingredients_list);
            for (int i = 0; i < ingredients.size(); i++)
            {
                for (int j = 0; j < availableIngredients.length; j++)
                {
                    if (ingredients.get(i).equals(availableIngredients[j]))
                    {
                        ingredientWidgets[i].setSelection(j);
                        continue;
                    }
                }
            }
        }
    }
    protected void dismissKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(recipeName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(recipeDirection.getWindowToken(), 0);
    }
    public static boolean isEmptyString(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_UP){
            dismissKeyboard();
        }
        return false;
    }
    @Override
    public void onClick(View v){
        if(v.getId() == R.id.addButton){
            String recipe = recipeName.getText().toString();
            String direction = recipeDirection.getText().toString();
            boolean oneSelected = false;
            for(int i=0; i<ingredientWidgets.length; i++) {
                if((String)ingredientWidgets[i].getSelectedItem() != "None") {
                    oneSelected = true;
                    break;
                }
            }
            if(!isEmptyString(recipeName.getText().toString()) && !isEmptyString(recipeDirection.getText().toString())
                    && oneSelected && recipeDirection.getText().length() <= 250){
                LinkedList<String> ingredients = new LinkedList<String>();

                for(int i=0; i<ingredientWidgets.length; i++){
                    if(!((String)ingredientWidgets[i].getSelectedItem()).equals("None")){
                        ingredients.add((String)ingredientWidgets[i].getSelectedItem());
                    }
                }
                singleton.addRecipe(recipe, direction, "", ingredients);
                Toast.makeText(getBaseContext(), "Recipe added!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(), "Please follow the instructions on adding a recipe", Toast.LENGTH_SHORT).show();
            }

        }
        dismissKeyboard();




    }
}
