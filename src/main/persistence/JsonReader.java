package persistence;


import model.Description;
import model.Ingredient;
import model.Recipe;
import model.RecipeBook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

//NOTE: Class modeled based on: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads recipe from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads recipe book from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }



    // EFFECTS: parses book of recipes from JSON object and returns it
    private RecipeBook parseRecipeBook(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        RecipeBook book = new RecipeBook(name);
        addRecipes(book, jsonObject);
        return book;
    }

    // MODIFIES: recipe book
    // EFFECTS: parses recipes from JSON object and adds them to recipe
    private void addRecipes(RecipeBook book, JSONObject jsonObject) {
        JSONArray recipes = jsonObject.getJSONArray("recipes");
        for (Object json : recipes) {
            JSONObject element = (JSONObject) json;
            addRecipe(book, element);
        }
    }

    // MODIFIES: recipe book
    // EFFECTS: parses recipe from JSON object and adds it to recipe book
    private void addRecipe(RecipeBook book, JSONObject jsonObject) {
        String name = jsonObject.getString("recipe name");
        Recipe recipe = new Recipe(name);
        book.addRecipeToBook(recipe);
        addIngredients(recipe, book, jsonObject);
        addDirections(recipe, book, jsonObject);
    }

    // MODIFIES: recipe book
    // EFFECTS: parses ingredients from JSON object and adds them to recipe book
    private void addIngredients(Recipe r, RecipeBook book, JSONObject jsonObject) {
        JSONArray ingredients = jsonObject.getJSONArray("ingredients");
        for (Object json : ingredients) {
            JSONObject element = (JSONObject) json;
            addIngredient(r, book, element);
        }
    }

    // MODIFIES: recipe book
    // EFFECTS: parses ingredient from JSON object and adds it to recipe book
    private void addIngredient(Recipe r, RecipeBook book, JSONObject jsonObject) {
        String name = jsonObject.getString("ingredient name");
        int amount = jsonObject.getInt("amount");
        Ingredient ingredient = new Ingredient(name, amount);
        book.addIngredientToRecipe(ingredient, r);
    }

    // MODIFIES: recipe book
    // EFFECT: parses directions from JSON object and adds them to recipe book
    private void addDirections(Recipe r, RecipeBook book, JSONObject jsonObject) {
        JSONArray directions = jsonObject.getJSONArray("descriptions");
        for (Object json : directions) {
            JSONObject element = (JSONObject) json;
            addDirection(r, book, element);
        }

    }

    // MODIFIES: recipe book
    // EFFECT: parses direction from JSON object and adds it to recipe book
    private void addDirection(Recipe r, RecipeBook book, JSONObject jsonObject) {
        String name = jsonObject.getString("description");
        Description description = new Description(name);
        book.addDescriptionToRecipe(r, description);

    }


}
