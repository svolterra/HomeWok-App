package model.tests;

import model.Description;
import model.Ingredient;
import model.Recipe;
import model.RecipeList;
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

        List<Description> recipeDescription = brownies.getDescription();
        Description description0 = recipeDescription.get(0);
        Description description1 = recipeDescription.get(1);
        Description description2 = recipeDescription.get(2);

        assertEquals(3, recipeDescription.size());
        assertEquals("Preheat oven to 350 degrees", description0.getDescription());
        assertEquals("Cream butter and sugar together", description1.getDescription());
        assertEquals("Add eggs to butter mixture", description2.getDescription());

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

    @Test
    public void testGetIndexOf(){
        recipeList.addRecipe("Cookies");
        recipeList.addRecipe("Cake");
        recipeList.addRecipe("Pudding");

        Recipe cookies = recipeList.get(1);
        Recipe cake = recipeList.get(2);
        Recipe pudding = recipeList.get(3);

        assertEquals(0, recipeList.getIndexOf(brownies));
        assertEquals(1, recipeList.getIndexOf(cookies));
        assertEquals(2, recipeList.getIndexOf(cake));
        assertEquals(3, recipeList.getIndexOf(pudding));
    }

    @Test
    public void testAdd(){
        Recipe cookies = new Recipe("Cookies");
        Recipe pavlova = new Recipe("Pavlova");
        Recipe scones = new Recipe("Scones");

        recipeList.add(cookies);
        assertEquals(2, recipeList.size());
        assertEquals(brownies, recipeList.get(0));
        assertEquals("Brownies", recipeList.get(0).getName());
        assertEquals(cookies, recipeList.get(1));
        assertEquals("Cookies", recipeList.get(1).getName());

        recipeList.add(pavlova);
        assertEquals(3, recipeList.size());
        assertEquals(pavlova, recipeList.get(2));
        assertEquals("Pavlova", recipeList.get(2).getName());

        recipeList.add(scones);
        assertEquals(4, recipeList.size());
        assertEquals(scones, recipeList.get(3));
        assertEquals("Scones", recipeList.get(3).getName());
    }


}
