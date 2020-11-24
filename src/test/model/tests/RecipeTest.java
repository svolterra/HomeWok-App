package model.tests;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

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
        testRecipe.setRepeat(false);
        assertFalse(testRecipe.willMakeAgain());
    }

    @Test
    public void testSetMakeAgainTrue() {
        testRecipe.setRepeat(true);
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


        assertEquals(4, testIngredients.size());

        List<String> ingredientNames = testRecipe.getIngredientNames();

        assertEquals("Butter", ingredientNames.get(0));
        assertEquals("Flour", ingredientNames.get(1));
        assertEquals("Granulated sugar", ingredientNames.get(2));
        assertEquals("Chocolate chips", ingredientNames.get(3));

        List<Integer> ingredientAmount = testRecipe.getIngredientAmount();

        assertEquals(227, ingredientAmount.get(0));
        assertEquals(380, ingredientAmount.get(1));
        assertEquals(100, ingredientAmount.get(2));
        assertEquals(340, ingredientAmount.get(3));

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

        List<String> recipeDescriptionString = testRecipe.getDescriptionString();


        assertEquals("Cream butter with granulated and brown sugar", recipeDescriptionString.get(0));
        assertEquals("Add two eggs and vanilla",  recipeDescriptionString.get(1));
        assertEquals("Add flour",  recipeDescriptionString.get(2));
        assertEquals("Add chocolate chips and mix",  recipeDescriptionString.get(3));
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