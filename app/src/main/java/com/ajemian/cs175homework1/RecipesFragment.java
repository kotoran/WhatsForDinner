package com.ajemian.cs175homework1;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;


public class RecipesFragment extends Fragment {

    private TextView recipeName;
    private TextView recipeDirection;
    private TextView recipeIngredients;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes,
                container, false);


        recipeName = (TextView)view.findViewById(R.id.recipeNameInFragment);
        recipeDirection = (TextView)view.findViewById(R.id.recipeDirectionsInFragment);
        recipeIngredients = (TextView)view.findViewById(R.id.recipeIngredientsInFragment);

        return view;
    }

    protected void setRecipe(String name, String directions, LinkedList<String> ingredients){
        Log.d("CAT", "Should work?");
        if(name != null) recipeName.setText(name);
        if(directions != null) recipeDirection.setText(directions);
        if(ingredients != null){
            StringBuilder sb = new StringBuilder();
            sb.append("Ingredients: \n");
            for(int i=0; i<ingredients.size(); i++){
                sb.append(ingredients.get(i) + "\n");
            }
            recipeIngredients.setText(sb.toString());
        }else{
            recipeIngredients.setText("It seems there are no ingredients for this recipe!");
        }

    }
}
