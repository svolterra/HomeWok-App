package persistence.tests;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//NOTE: Class modeled based on: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest {
    private RecipeBook book;

    @BeforeEach
    public void setup() {
        book = new RecipeBook("My recipes");
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyRecipeBook() {
        try {
            RecipeBook book = new RecipeBook("My recipes");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRecipeBook.json");
            writer.open();
            writer.write(book);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeBook.json");
            book = reader.read();
            assertEquals("My recipes", book.getBookName());
            assertEquals(0, book.getRecipes().size());
        } catch (IOException e) {
            fail("Unexpected exception");
        }

    }



    @Test
    public void testWriterNotEmptyRecipeBook() {
        try {
            RecipeBook book = new RecipeBook("My recipes");

            Recipe cookies = new Recipe("Cookies");
            Recipe brownies = new Recipe("Brownies");
            Ingredient sugar = new Ingredient("Sugar", 50);
            Description addSugar = new Description("Add sugar");

            book.addRecipeToBook(cookies);
            book.addRecipeToBook(brownies);

            book.addIngredientToRecipe("Flour", 100, cookies);
            book.addIngredientToRecipe(sugar, cookies);
            book.addIngredientToRecipe(sugar, brownies);

            book.addDescriptionToRecipe(cookies, "Add sugar to bowl");
            book.addDescriptionToRecipe(brownies, addSugar);

            JsonWriter writer = new JsonWriter("./data/testWriteNotEmptyRecipeBook.json");
            writer.open();
            writer.write(book);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteNotEmptyRecipeBook.json");
            book = reader.read();
            assertEquals("My recipes", book.getBookName());

            RecipeList recipes = book.getRecipes();
            Recipe cookie = recipes.get(0);
            Recipe brownie = recipes.get(1);
            List<Ingredient> cookieIngredients = cookie.getIngredients();
            List<Ingredient> brownieIngredients = brownie.getIngredients();
            List<Description> cookieDirections = cookie.getDescription();
            List<Description> brownieDirections = brownie.getDescription();

            assertEquals(2, recipes.size());
            assertEquals("Cookies", recipes.get(0).getName());
            assertEquals("Brownies", recipes.get(1).getName());
            assertEquals(2, cookieIngredients.size());
            assertEquals(1, brownieIngredients.size());
            assertEquals("Flour", cookieIngredients.get(0).getIngredientName());
            assertEquals("Sugar", cookieIngredients.get(1).getIngredientName());
            assertEquals("Sugar", brownieIngredients.get(0).getIngredientName());
            assertEquals("Add sugar to bowl", cookieDirections.get(0).getDescription());
            assertEquals("Add sugar", brownieDirections.get(0).getDescription());

        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }


}



