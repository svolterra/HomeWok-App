package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import persistence.Writable;

//Represents an ingredient of a recipe with its name and the amount needed in grams
public class Ingredient implements Writable {
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

   //EFFECTS: converts ingredient to JSON object for data persistence
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ingredient name", ingredientName);
        json.put("amount", amount);
        return json;
    }


}
