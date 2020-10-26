package persistence.tests;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.Ingredient;
import model.Recipe;
import model.RecipeBook;
import model.RecipeList;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

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

            book.addRecipeToBook(cookies);
            book.addRecipeToBook(brownies);

            JsonWriter writer = new JsonWriter("./data/testWriteNotEmptyRecipeBook.json");
            writer.open();
            writer.write(book);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteNotEmptyRecipeBook.json");
            book = reader.read();
            assertEquals("My recipes", book.getBookName());

            RecipeList recipes = book.getRecipes();
            assertEquals(2, recipes.size());
            assertEquals("Cookies", recipes.get(0).getName());
            assertEquals("Brownies", recipes.get(1).getName());
        } catch (IOException e) {
            fail("Unexpected exception");
        }
    }


}



