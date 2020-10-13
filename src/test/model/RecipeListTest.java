package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests for the RecipeList class
public class RecipeListTest {
    private RecipeList recipeList;
    private Recipe brownies;


    @Test
    public void testConstructor() {
        recipeList = new RecipeList();
        assertEquals(0, recipeList.size());
    }

    @BeforeEach
    public void setup() {
        recipeList = new RecipeList();
        recipeList.addRecipe("Brownies");
        brownies = recipeList.get(0);

    }

    @Test
    public void testAddOneElement() {
        assertEquals("Brownies", brownies.getName());
        assertEquals(1, recipeList.size());
        assertEquals(0, brownies.getDescription().size());
    }

    @Test
    public void testAddSeveralElements() {
        recipeList.addRecipe("Cookies");
        recipeList.addRecipe("Cake");

        assertEquals(3, recipeList.size());

        Recipe cookies = recipeList.get(1);
        Recipe cake = recipeList.get(2);

        assertEquals("Brownies", brownies.getName());
        assertEquals("Cookies", cookies.getName());
        assertEquals("Cake", cake.getName());
    }

    @Test
    public void testAddDescriptions() {
        brownies.addDescription("Preheat oven to 350 degrees");
        brownies.addDescription("Cream butter and sugar together");
        brownies.addDescription("Add eggs to butter mixture");

        List<String> recipeDescription = brownies.getDescription();

        assertEquals(3, recipeDescription.size());
        assertEquals("Preheat oven to 350 degrees", recipeDescription.get(0));
        assertEquals("Cream butter and sugar together", recipeDescription.get(1));
        assertEquals("Add eggs to butter mixture", recipeDescription.get(2));

    }

    @Test
    public void testAddIngredients() {
        brownies.addIngredient("Sugar", 249);
        brownies.addIngredient("Butter", 71);
        brownies.addIngredient("Cocoa powder", 75);

        List<Ingredient> ingredients = brownies.getIngredients();
        Ingredient sugar = ingredients.get(0);
        Ingredient butter = ingredients.get(1);
        Ingredient cocoaPowder = ingredients.get(2);

        assertEquals(3, ingredients.size());
        assertEquals("Sugar", sugar.getIngredientName());
        assertEquals("Butter", butter.getIngredientName());
        assertEquals("Cocoa powder", cocoaPowder.getIngredientName());

        assertEquals(249, sugar.getAmountNeeded());
        assertEquals(71, butter.getAmountNeeded());
        assertEquals(75, cocoaPowder.getAmountNeeded());
    }

    @Test
    public void testRemove() {
        recipeList.addRecipe("Creme Brulee");
        assertEquals(2, recipeList.size());

        recipeList.remove(brownies);
        assertEquals(1, recipeList.size());
        recipeList.remove(recipeList.get(0));
        assertEquals(0, recipeList.size());
    }

    @Test
    public void testContains() {
        assertTrue(recipeList.contain(brownies));
    }

    @Test
    public void testRemoveAtIndex() {
        recipeList.addRecipe("Cookies");
        recipeList.addRecipe("Pavlova");
        assertEquals(3, recipeList.size());

        Recipe pavlova = recipeList.get(2);
        recipeList.removeAtIndex(2);
        assertFalse(recipeList.contain(pavlova));
        assertEquals(2, recipeList.size());
    }

    @Test
    public void testGetListOfElementTitles() {
        recipeList.addRecipe("Cookies");
        recipeList.addRecipe("Cake");
        recipeList.addRecipe("Pudding");

        List<String> recipes = recipeList.getListOfElementTitles();
        assertEquals(4, recipes.size());
        assertEquals("Brownies", recipes.get(0));
        assertEquals("Cookies", recipes.get(1));
        assertEquals("Cake", recipes.get(2));
        assertEquals("Pudding", recipes.get(3));
    }


}
