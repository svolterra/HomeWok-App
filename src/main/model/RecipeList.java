package model;

import java.util.ArrayList;
import java.util.List;

//A list of recipes
public class RecipeList implements ElementList {
    private List<Recipe> recipeList;

    public RecipeList() {
        recipeList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds homework assignment to list of assignments
    public void addRecipe(String recipeName) {
        recipeList.add(new Recipe(recipeName));
    }


    //EFFECTS: returns size of homework list
    public int size() {
        return recipeList.size();
    }

    //EFFECTS: returns element at given index
    public Recipe get(int index) {
        Recipe atIndex = recipeList.get(index);
        return atIndex;
    }

    //MODIFIES: this
    //EFFECTS: removes given element from list of list elements
    public void remove(Element e) {
        recipeList.remove(e);
    }


    //EFFECTS: returns true if list  contains given element, false otherwise
    public boolean contain(Element e) {
        return recipeList.contains(e);
    }


    //REQUIRES: i > 0
    //MODIFIES: this
    //EFFECTS: removes element at given index from list
    public void removeAtIndex(int i) {
        Recipe recipeToRemove = recipeList.get(i);
        recipeList.remove(recipeToRemove);
    }

    //EFFECTS: returns list of homework assignments so far
    public List<String> getListOfElementTitles() {
        List<String> finalElementList = new ArrayList<>();
        for (Recipe r: recipeList) {
            finalElementList.add(r.getName());
        }
        return finalElementList;
    }




}

