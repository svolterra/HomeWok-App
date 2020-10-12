package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests for the recipe class
public class RecipeTest {
    private Recipe testRecipe;
    private List<Ingredient> testIngredients;
    private List<String> testDescription;


    @BeforeEach
    public void setup() {
        testRecipe = new Recipe("Cookies");
        testIngredients = testRecipe.getIngredients();
        testDescription = testRecipe.getDescription();
    }

    @Test
    public void testRecipeConstructor() {

        assertEquals("Cookies", testRecipe.getName());
        assertEquals(0, testIngredients.size());
        assertEquals(0, testDescription.size());
    }

    @Test
    public void testSetMakeAgainFalse() {
        testRecipe.setMakeAgain(false);
        assertFalse(testRecipe.willMakeAgain());
    }

    @Test
    public void testSetMakeAgainTrue() {
        testRecipe.setMakeAgain(true);
        assertTrue(testRecipe.willMakeAgain());
    }

    @Test
    public void testAddOneIngredient() {
        testRecipe.addIngredient("Butter", 227);
        Ingredient ingredient0 = testIngredients.get(0);

        assertEquals(1, testIngredients.size());
        assertEquals("Butter", ingredient0.getIngredientName());
        assertEquals(227, ingredient0.getAmountNeeded());

    }

    @Test
    public void testAddSeveralIngredients() {
        testRecipe.addIngredient("Butter", 227);
        testRecipe.addIngredient("Flour", 380);
        testRecipe.addIngredient("Granulated sugar", 100);
        testRecipe.addIngredient("Chocolate chips", 340);

        Ingredient ingredient0 = testIngredients.get(0);
        Ingredient ingredient1 = testIngredients.get(1);
        Ingredient ingredient2 = testIngredients.get(2);
        Ingredient ingredient3 = testIngredients.get(3);

        assertEquals(4, testIngredients.size());

        assertEquals("Butter", ingredient0.getIngredientName());
        assertEquals("Flour", ingredient1.getIngredientName());
        assertEquals("Granulated sugar", ingredient2.getIngredientName());
        assertEquals("Chocolate chips", ingredient3.getIngredientName());

        assertEquals(227, ingredient0.getAmountNeeded());
        assertEquals(380, ingredient1.getAmountNeeded());
        assertEquals(100, ingredient2.getAmountNeeded());
        assertEquals(340, ingredient3.getAmountNeeded());

    }

    @Test
    public void testAddOneDirection() {
        testRecipe.addDescription("Preheat oven to 350 degrees fahrenheit");

        assertEquals(1, testDescription.size());
        assertEquals("Preheat oven to 350 degrees fahrenheit", testDescription.get(0));

    }

    @Test
    public void testAddSeveralDescriptions() {
        testRecipe.addDescription("Cream butter with granulated and brown sugar");
        testRecipe.addDescription("Add two eggs and vanilla");
        testRecipe.addDescription("Add flour");
        testRecipe.addDescription("Add chocolate chips and mix");


        assertEquals(4, testDescription.size());

        assertEquals("Cream butter with granulated and brown sugar", testDescription.get(0));
        assertEquals("Add two eggs and vanilla", testDescription.get(1));
        assertEquals("Add flour", testDescription.get(2));
        assertEquals("Add chocolate chips and mix", testDescription.get(3));


    }


}