package persistence.tests;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
            assertEquals(2, recipes.size());
            assertEquals("Cookies", recipes.get(0).getName());
            assertEquals("Brownies", recipes.get(1).getName());

//            List<Ingredient> cookieIngredients = recipes.get(0).getIngredients();
//            List<Ingredient> brownieIngredients = recipes.get(1).getIngredients();
//            List<Description> cookieDirections = recipes.get(0).getDescription();
//            List<Description> brownieDirections = recipes.get(1).getDescription();
//
//            assertEquals("Flour", cookieIngredients.get(0));
//            assertEquals("Chocolate chips", cookieIngredients.get(1));
//            assertEquals("Flour", brownieIngredients.get(0));
//            assertEquals("Sugar", brownieIngredients.get(1));

        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }


}
