package model;

import java.util.ArrayList;
import java.util.List;

//A list of recipes
public class RecipeList implements ElementList {
    private List<Recipe> recipeList;

    //EFFECTS: constructs an empty list of recipes
    public RecipeList() {
        recipeList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds recipe to list of recipes
    public void add(Recipe recipe) {
        recipeList.add(recipe);
    }

    //MODIFIES: this
    //EFFECTS: adds given recipe to list of recipes
    public void addRecipe(String recipeName) {
        recipeList.add(new Recipe(recipeName));
    }


    //EFFECTS: returns size of recipe list
    public int size() {
        return recipeList.size();
    }

    //EFFECTS: returns recipe at given index
    public Recipe get(int index) {
        Recipe atIndex = recipeList.get(index);
        return atIndex;
    }

    //MODIFIES: this
    //EFFECTS: removes given recipe from list of recipes
    public void remove(Element e) {
        recipeList.remove(e);
    }


    //EFFECTS: returns true if list contains given recipe, false otherwise
    public boolean contain(Element e) {
        return recipeList.contains(e);
    }

    //EFFECTS: returns true if list contains given string, false otherwise
    public boolean containString(String s) {
        return recipeList.contains(s);
    }


    //REQUIRES: i > 0
    //MODIFIES: this
    //EFFECTS: removes recipe at given index from list
    public void removeAtIndex(int i) {
        Recipe recipeToRemove = recipeList.get(i);
        recipeList.remove(recipeToRemove);
    }

    //EFFECTS: returns list of recipe names so far
    public List<String> getListOfElementTitles() {
        List<String> finalElementList = new ArrayList<>();
        for (Recipe r : recipeList) {
            finalElementList.add(r.getName());
        }
        return finalElementList;
    }

    public int getIndexOf(Recipe r) {
        int index = recipeList.indexOf(r);
        return index;
    }


}

