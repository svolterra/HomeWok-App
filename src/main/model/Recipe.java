package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a recipe with a name, list of ingredients, list of directions to make recipe,
// and whether user will make recipe again or not
public class Recipe extends Element implements Writable {
    private List<Ingredient> ingredients;

    //EFFECTS: Constructs a recipe with given name, empty description and ingredient list
    public Recipe(String name) {
        super(name);
        ingredients = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds given ingredient to list of ingredients
    public void addGivenIngredient(Ingredient i) {
        ingredients.add(i);
    }

    //MODIFIES: this
    //EFFECTS: adds ingredient to list of ingredients
    public void addIngredient(String name, int amount) {
        ingredients.add(new Ingredient(name, amount));
    }


    //EFFECTS: returns list of ingredients
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    // EFFECTS: returns list of ingredient names
    public List<String> getIngredientNames() {
        List<String> ingredientNames = new ArrayList<>();
        for (Ingredient i : ingredients) {
            ingredientNames.add(i.getIngredientName());
        }
        return ingredientNames;
    }

    // EFFECTS: returns list of ingredient amounts
    public List<Integer> getIngredientAmount() {
        List<Integer> ingredientAmount = new ArrayList<>();
        for (Ingredient i : ingredients) {
            ingredientAmount.add(i.getAmountNeeded());
        }
        return ingredientAmount;
    }


    // EFFECTS: returns recipes as JSON objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipe name", name);
        json.put("ingredients", ingredientsToJson());
        json.put("descriptions", descriptionsToJson());
        return json;
    }

    //EFFECTS: returns ingredients in recipe as a JSON Array
    private JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : ingredients) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }


    //EFFECTS: returns directions in recipe as a JSON Array
    private JSONArray descriptionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Description d : descriptions) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }


}
