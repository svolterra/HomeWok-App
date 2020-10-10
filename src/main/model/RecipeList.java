package model;

import java.util.ArrayList;
import java.util.List;

public class RecipeList {
    private List<Recipe> recipeList;

    //EFFECTS: Constructs an empty list of homework
    public RecipeList() {
        recipeList = new ArrayList<>();

    }

    public void addHomework(Recipe r) {
        recipeList.add(r);
    }

    public void removeHomework(Recipe r) {
        recipeList.remove(r);
    }

    public void removeHomeworkAtIndex(int i) {
        Recipe recipeToRemove = recipeList.get(i);
        recipeList.remove(recipeToRemove);
    }

    public boolean doesListContainHomework(Homework h) {
        boolean doesListContain = recipeList.contains(h);
        return doesListContain;

    }


}

