package com.ajemian.cs175homework1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class GroceriesActivity extends AppCompatActivity {

    private ListView groceriesListView;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);
        groceriesListView = (ListView)findViewById(R.id.groceriesList);
        LinkedHashMap<String, Integer> count = WFDSingleton.getInstance().getIngredientsCount();


        /* This is really bad, change later */
        LinkedHashMap<String, String> matchWithUnits = new LinkedHashMap<>();
        String[] ingredientName = getResources().getStringArray(R.array.ingredients_list);
        String[] unitName = getResources().getStringArray(R.array.units_list);
        for(int i=0; i<ingredientName.length; i++){
            matchWithUnits.put(ingredientName[i], unitName[i]);
        }

        LinkedList<String> ingredientWithCount = new LinkedList<>();
        for(String ingredient : count.keySet()){
                ingredientWithCount.add(ingredient + " (" + count.get(ingredient).toString() + " " + matchWithUnits.get(ingredient) + ")");
        }

        if(ingredientWithCount.size() < 1){
                Toast.makeText(getBaseContext(), "No recipes! No shopping!", Toast.LENGTH_LONG).show();
        }

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                ingredientWithCount);

        groceriesListView.setAdapter(adapter);




    }
}
