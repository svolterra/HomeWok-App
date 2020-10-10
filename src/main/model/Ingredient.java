package model;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private String ingredientName;
    private int amount;

    //EFFECTS: constructs an ingredient with given name and amount needed (in grams)
    public Ingredient(String name, int amountNeeded) {
        ingredientName = name;
        amount = amountNeeded;
    }

    //EFFECTS: returns name of ingredient
    public String getIngredientName() {
        return ingredientName;
    }


    //EFFECTS: returns amount of ingredient needed (in grams)
    public int getAmountNeeded() {
        return amount;
    }





}
