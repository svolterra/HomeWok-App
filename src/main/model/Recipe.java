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
    private boolean makeAgain;

    //EFFECTS: Constructs a recipe with given name, empty description and ingredient list
    public Recipe(String name) {
        super(name);
        ingredients = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: sets makeAgain to true if b is true, false otherwise
    //         true: will make again, false: will not make again
    public void setMakeAgain(boolean b) {
        makeAgain = b;
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

    //EFFECTS: returns whether user will make recipe again
    public boolean willMakeAgain() {
        return makeAgain;
    }

    //EFFECTS: returns list of ingredients
    public List<Ingredient> getIngredients() {
        return ingredients;
    }


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
