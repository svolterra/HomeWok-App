package model.tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;
import sun.security.krb5.internal.crypto.Des;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;


//Tests for the recipe class
public class RecipeTest {
    private Recipe testRecipe;
    private List<Ingredient> testIngredients;
    private List<Description> testDescription;


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
        Ingredient butter = new Ingredient("Butter", 227);
        Ingredient flour = new Ingredient("Flour", 380);
        testRecipe.addGivenIngredient(butter);
        testRecipe.addGivenIngredient(flour);
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
        Description description = testDescription.get(0);

        assertEquals("Preheat oven to 350 degrees fahrenheit", description.getDescription());

    }

    @Test
    public void testAddSeveralDescriptions() {
        testRecipe.addDescription("Cream butter with granulated and brown sugar");
        testRecipe.addDescription("Add two eggs and vanilla");
        testRecipe.addDescription("Add flour");
        testRecipe.addDescription("Add chocolate chips and mix");

        assertEquals(4, testDescription.size());
        Description description0 = testDescription.get(0);
        Description description1 = testDescription.get(1);
        Description description2 = testDescription.get(2);
        Description description3 = testDescription.get(3);

        assertEquals("Cream butter with granulated and brown sugar", description0.getDescription());
        assertEquals("Add two eggs and vanilla", description1.getDescription());
        assertEquals("Add flour", description2.getDescription());
        assertEquals("Add chocolate chips and mix", description3.getDescription());
    }

    @Test
    public void testToJson() {
        try {
            RecipeBook book = new RecipeBook("My recipes");
            Recipe cookies = new Recipe("Cookies");
            Recipe scones = new Recipe("Scones");
            Ingredient flour = new Ingredient("Flour", 100);
            Description addFlour = new Description("Add flour");

            book.addRecipeToBook(cookies);
            book.addRecipeToBook(scones);
            book.addIngredientToRecipe(flour, cookies);
            book.addDescriptionToRecipe(cookies, "Add chocolate chips");
            book.addIngredientToRecipe("Sugar", 25, scones);
            book.addDescriptionToRecipe(scones, addFlour);

            JsonWriter writer = new JsonWriter("./data/testRecipe.json");
            writer.open();
            writer.write(book);
            writer.close();

            JsonReader reader = new JsonReader("./data/testRecipe.json");
            book = reader.read();
            RecipeList recipes = book.getRecipes();
            List<Ingredient> cookieIngredients = cookies.getIngredients();
            List<Ingredient> sconeIngredients = scones.getIngredients();
            List<Description> cookieDirections = cookies.getDescription();
            List<Description> sconesDirections = scones.getDescription();

            assertEquals("My recipes", book.getBookName());
            assertEquals(2, recipes.size());
            assertEquals("Cookies", recipes.get(0).getName());
            assertEquals("Scones", recipes.get(1).getName());
            assertEquals(1, cookieIngredients.size());
            assertEquals(1, sconeIngredients.size());
            assertEquals("Flour", cookieIngredients.get(0).getIngredientName());
            assertEquals("Sugar", sconeIngredients.get(0).getIngredientName());
            assertEquals("Add chocolate chips", cookieDirections.get(0).getDescription());
            assertEquals("Add flour", sconesDirections.get(0).getDescription());

        } catch (IOException e) {
            fail("Unexpected exception");
        }


    }
}