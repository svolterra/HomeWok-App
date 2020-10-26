package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a "book", or collection, of user's recipes
public class RecipeBook implements Writable {
    private String name;
    private RecipeList recipes;

    //EFFECTS: constructs a new recipe book with given name and an empty list of recipes
    public RecipeBook(String name) {
        this.name = name;
        recipes = new RecipeList();

    }

    //EFFECTS: return name of recipe book
    public String getBookName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds given recipe to list of recipes in recipe book
    public void addRecipeToBook(Recipe r) {
        recipes.add(r);
    }

    //REQUIRES: chosen recipe name already exists in recipe book
    //MODIFIES: recipe
    //EFFECTS: adds given ingredient to recipe of choice
    public void addIngredientToRecipe(Ingredient i, Recipe recipe) {
        int index = recipes.getIndexOf(recipe);
        Recipe chosen = recipes.get(index);
        chosen.addGivenIngredient(i);
    }

    //REQUIRES: chosen recipe name already exists in recipe book
    //MODIFIES: recipe
    //EFFECTS: adds ingredient of given name and amount to recipe of choice with
    public void addIngredientToRecipe(String ingredientName, int ingredientAmount,Recipe recipe) {
        int index = recipes.getIndexOf(recipe);
        Recipe chosenRecipe = recipes.get(index);
        chosenRecipe.addIngredient(ingredientName,ingredientAmount);
    }

    //EFFECTS: returns list od recipes
    public RecipeList getRecipes() {
        return recipes;
    }


    //EFFECTS: returns chosen recipe's list of ingredients
    public List<Ingredient> getIngredients(Recipe recipe) {
        int index = recipes.getIndexOf(recipe);
        Recipe chosenRecipe = recipes.get(index);
        List<Ingredient> ingredients = chosenRecipe.getIngredients();
        return ingredients;
    }

    //REQUIRES: chosen recipe name already exists in recipe book
    //MODIFIES: recipe
    //EFFECTS: adds given description to chosen recipe
    public void addDescriptionToRecipe(Recipe recipe, Description description) {
        String d = description.getDescription();
        recipe.addDescription(d);
    }

    //REQUIRES: chosen recipe name already exists in recipe book
    //MODIFIES: recipe
    //EFFECTS: adds given description to chosen recipe as a string
    public void addDescriptionToRecipe(Recipe recipe, String description) {
        recipe.addDescription(description);
    }


    //EFFECTS: returns size of recipe list in book of recipes
    public int getRecipeListSize() {
        return recipes.size();
    }

    //EFFECTS: returns names of recipes in book of recipes
    public List<String> getRecipeNames() {
        return recipes.getListOfElementTitles();
    }

    //EFFECTS: returns index of given index
    public int indexOfRecipe(Recipe r) {
        return recipes.getIndexOf(r);
    }

    //EFFECTS: returns recipe at given index
    public Recipe get(int i) {
        return recipes.get(i);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("recipes", recipes.recipesToJson());
        return json;
    }

}
