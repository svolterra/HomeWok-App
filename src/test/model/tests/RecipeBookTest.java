package model.tests;

import model.Description;
import model.Ingredient;
import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;



public class RecipeBookTest {
    private RecipeBook book;
    private Recipe cookies;


    @Test
    public void testConstructor() {
        book = new RecipeBook("My recipes");
        assertEquals("My recipes", book.getBookName());
        assertEquals(0, book.getRecipeListSize());
    }

    @BeforeEach
    public void setup() {
        book = new RecipeBook("My recipes");
        cookies = new Recipe("Cookies");
        book.addRecipeToBook(cookies);

    }

    @Test
    public void testAddOneRecipeToBook() {
        assertEquals(cookies, book.get(0));
        assertEquals(1, book.getRecipeListSize());
    }

    @Test
    public void testAddSeveralRecipesToBook() {
        Recipe brownies = new Recipe("Brownies");
        Recipe pudding = new Recipe("Pudding");

        book.addRecipeToBook(brownies);
        book.addRecipeToBook(pudding);

        assertEquals(3, book.getRecipeListSize());
        assertEquals(cookies, book.get(0));
        assertEquals(brownies, book.get(1));
        assertEquals(pudding, book.get(2));

        assertEquals("Cookies", book.getRecipeNames().get(0));
        assertEquals("Brownies", book.getRecipeNames().get(1));
        assertEquals("Pudding", book.getRecipeNames().get(2));
    }

    @Test
    public void addOneIngredientToOneRecipe() {
        Ingredient flour = new Ingredient("Flour", 50);
        book.addIngredientToRecipe(flour, cookies);
        Ingredient ingredient = book.getIngredients(cookies).get(0);

        assertEquals(1, book.getIngredients(cookies).size());
        assertEquals("Flour", ingredient.getIngredientName());
        assertEquals(50, ingredient.getAmountNeeded());

    }

    @Test
    public void addSeveralIngredientsToSeveralRecipes() {
        Recipe brownie = new Recipe("Brownies");
        book.addRecipeToBook(brownie);
        Ingredient flour = new Ingredient("Flour", 50);
        Ingredient chips = new Ingredient("Chocolate chips", 25);

        book.addIngredientToRecipe(flour, cookies);
        book.addIngredientToRecipe(chips, cookies);
        book.addIngredientToRecipe("Sugar", 30, brownie);
        book.addIngredientToRecipe("Eggs", 25, brownie);

        Ingredient f = book.getIngredients(cookies).get(0);
        Ingredient c = book.getIngredients(cookies).get(1);
        Ingredient s = book.getIngredients(brownie).get(0);
        Ingredient e = book.getIngredients(brownie).get(1);

        assertEquals(2, book.getIngredients(cookies).size());
        assertEquals(2, book.getIngredients(brownie).size());
        assertEquals(2, book.getRecipeListSize());

        assertEquals("Flour", f.getIngredientName());
        assertEquals(50, f.getAmountNeeded());
        assertEquals("Chocolate chips", c.getIngredientName());
        assertEquals(25, c.getAmountNeeded());
        assertEquals("Sugar", s.getIngredientName());
        assertEquals(30, s.getAmountNeeded());
        assertEquals("Eggs", e.getIngredientName());
        assertEquals(25, e.getAmountNeeded());
    }

    @Test
    public void testIndexOfRecipe() {
        Recipe brownie = new Recipe("Brownies");
        book.addRecipeToBook(brownie);

        assertEquals(0, book.indexOfRecipe(cookies));
        assertEquals(1, book.indexOfRecipe(brownie));
    }

    @Test
    public void testGet() {
        Recipe brownies = new Recipe("Brownies");
        Recipe pudding = new Recipe("Pudding");
        Recipe hotChocolate = new Recipe("Hot chocolate");

        book.addRecipeToBook(brownies);
        book.addRecipeToBook(pudding);
        book.addRecipeToBook(hotChocolate);

        Recipe recipe0 = book.get(0);
        Recipe recipe1= book.get(1);
        Recipe recipe2 = book.get(2);
        Recipe recipe3 = book.get(3);

        assertEquals("Cookies", recipe0.getName());
        assertEquals("Brownies", recipe1.getName());
        assertEquals("Pudding", recipe2.getName());
        assertEquals("Hot chocolate", recipe3.getName());

    }

    @Test
    public void testAddDescriptionToRecipe() {
        Description flour = new Description("Add flour to bowl");
        Description chips = new Description("Add chocolate chips to bowl");

        book.addDescriptionToRecipe(cookies, flour);
        book.addDescriptionToRecipe(cookies, "Add sugar to flour");
        book.addDescriptionToRecipe(cookies, chips);
        book.addDescriptionToRecipe(cookies, "Add melted butter and fold");

        List<Description> descriptionList = cookies.getDescription();
        assertEquals(4, descriptionList.size());
        assertEquals("Add flour to bowl", descriptionList.get(0).getDescription());
        assertEquals("Add sugar to flour", descriptionList.get(1).getDescription());
        assertEquals("Add chocolate chips to bowl", descriptionList.get(2).getDescription());
        assertEquals("Add melted butter and fold", descriptionList.get(3).getDescription());

    }

}
