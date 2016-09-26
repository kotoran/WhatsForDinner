package com.ajemian.cs175homework1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class WFDSingleton {

    public enum TypeEat{
        Breakfast,
        Lunch,
        Dinner
    }

    private static String DEFAULT_RECIPE = "Eating out";
    private static WFDSingleton instance;

    private LinkedHashMap<String, LinkedList<String>> recipes;
    private LinkedHashMap<String, String> directions;
    private LinkedHashMap<String, String> pictures;

    private HashMap<String, String> dayBreakfast;
    private HashMap<String, String> dayLunch;
    private HashMap<String, String> dayDinner;

    private int currentDay;

    private WFDSingleton(){
        recipes = new LinkedHashMap<>();
        directions = new LinkedHashMap<>();
        pictures = new LinkedHashMap<>();

        dayBreakfast = new HashMap<>();
        dayLunch = new HashMap<>();
        dayDinner = new HashMap<>();
        currentDay = 0;
    }

    public static WFDSingleton getInstance(){
        if(instance == null){
            instance = new WFDSingleton();
        }
        return instance;
    }
    public void setDay(int index){
        currentDay = index;
    }
    public int getDay(){
        return currentDay;
    }

    public void addRecipe(String recipeName, String strDirections, String photo, LinkedList<String> ingredients){
        recipes.put(recipeName, ingredients);
        directions.put(recipeName, strDirections);
        pictures.put(recipeName, photo);

    }

    public String getBreakfastWithDay(String day){
        if(dayBreakfast.get(day) == null) return DEFAULT_RECIPE;
        return dayBreakfast.get(day);
    }
    public String getLunchWithDay(String day){
        if(dayLunch.get(day) == null) return DEFAULT_RECIPE;
        return dayLunch.get(day);
    }
    public String getDinnerWithDay(String day){
        if(dayDinner.get(day) == null) return DEFAULT_RECIPE;
        return dayDinner.get(day);
    }


    public void setMeal(TypeEat type, String day, String recipe){
        if(type == TypeEat.Breakfast){
            dayBreakfast.put(day, recipe);
        }else if (type == TypeEat.Lunch){
            dayLunch.put(day, recipe);
        }else if (type == TypeEat.Dinner){
            dayDinner.put(day, recipe);
        }
    }

    public LinkedList<String> getRecipes(){
        LinkedList<String> recipeReturn = new LinkedList<>();
        recipeReturn.add(DEFAULT_RECIPE);
        for(String recipe : recipes.keySet()){
            recipeReturn.add(recipe);
        }
        return recipeReturn;
    }

    public LinkedList<String> getIngredients(String recipeName){
        LinkedList<String> ingredients = recipes.get(recipeName);
        if(ingredients == null) return new LinkedList<String>();
        return ingredients;
    }
    public String getDirections(String recipeName){
        String direction = directions.get(recipeName);
        if(direction == null) direction = "";
        return direction;
    }

    public LinkedHashMap<String, Integer> getIngredientsCount(){
        LinkedHashMap<String, Integer> ingredientsRequired = new LinkedHashMap<>();
        for(String recipe : recipes.keySet()){
            LinkedList<String> ingredientsInRecipe = recipes.get(recipe);
            for(int i=0; i<ingredientsInRecipe.size(); i++){
                int count = 0;
                if(ingredientsRequired.get(ingredientsInRecipe.get(i)) != null){
                    count = ingredientsRequired.get(ingredientsInRecipe.get(i));
                }
                count++;
                if((String)ingredientsInRecipe.get(i) != "None") ingredientsRequired.put(ingredientsInRecipe.get(i), count);
            }

        }
        return ingredientsRequired;
    }

    public String getPhoto(String recipeName){
        String photo = pictures.get(recipeName);
        if(photo == null) photo = "";
        return photo;
    }

    public LinkedList<String> getAllRecipeNames(){
        LinkedList<String> r = new LinkedList<String>();
        for(String recipe : recipes.keySet()){
            r.add(recipe);
        }
        return r;
    }
}
