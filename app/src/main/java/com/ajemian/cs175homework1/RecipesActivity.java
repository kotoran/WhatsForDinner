package com.ajemian.cs175homework1;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipesActivity  extends AppCompatActivity implements AdapterView.OnItemLongClickListener{

    private ListView recipeListView;
    private WFDSingleton singleton;


    private Fragment fragmentRecipe;


    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            /* Landscape Mode */
            setContentView(R.layout.activity_recipes_land);
            fragmentRecipe = getFragmentManager().findFragmentById(R.id.fragmentRecipe);

        }else{
            setContentView(R.layout.activity_recipes);
            /* Portrait mode */
        }

            recipeListView = (ListView)findViewById(R.id.recipeList);
            singleton = WFDSingleton.getInstance();



            adapter=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    singleton.getAllRecipeNames());

            recipeListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if(singleton.getAllRecipeNames().size() < 1) {
                Toast.makeText(getBaseContext(), "You don't have any recipes to display!", Toast.LENGTH_LONG).show();
            }
            recipeListView.setOnItemClickListener(selectionListener);


            recipeListView.setOnItemLongClickListener(this);



        /* Build the view depending on the requirement now */



        }

    protected AdapterView.OnItemClickListener selectionListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                String item = (String) recipeListView.getItemAtPosition(position);
                ((RecipesFragment)fragmentRecipe).setRecipe(item, WFDSingleton.getInstance().getDirections(item), WFDSingleton.getInstance().getIngredients(item));
            }

        }


    };
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View v,
                                   int index, long arg3) {

        String toEdit = adapterView.getItemAtPosition(index).toString();
        Intent editIntent = new Intent(this, AddActivity.class);
        editIntent.putExtra("Edit", toEdit);
        startActivity(editIntent);
        return true;
    }

}


