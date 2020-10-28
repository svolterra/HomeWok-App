package persistence.tests;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


//NOTE: Class modeled based on: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            RecipeBook book = reader.read();
            fail("IOException expected");
        } catch (IOException e){
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeBook() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyRecipeBook.json");
        try {
            RecipeBook book = reader.read();
            assertEquals("My recipes", book.getBookName());
            assertEquals(0, book.getRecipes().size());
        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }



    @Test
    void testReaderNotEmptyRecipeBook() {
        JsonReader reader = new JsonReader("./data/testWriteNotEmptyRecipeBook.json");
        try {
            RecipeBook book = reader.read();
            assertEquals("My recipes", book.getBookName());

            RecipeList recipes = book.getRecipes();
            Recipe cookie = recipes.get(0);
            Recipe brownie = recipes.get(1);

            assertEquals(2, recipes.size());
            assertEquals("Cookies", cookie.getName());
            assertEquals("Brownies", brownie.getName());

            List<Ingredient> cookieIngredients = book.getIngredients(cookie);
            List<Ingredient> brownieIngredients = book.getIngredients(brownie);
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
