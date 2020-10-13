package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

//Implementation of user interface of HomeWokApp.
/*
NOTE: Some code has been borrowed from TellerApp project provided in the Phase 1 description on edX.
 */
public class HomeWokApp {
    private HomeworkList homeworkList;
    private RecipeList recipeList;
    private Scanner userInput;

    public HomeWokApp() {
        runHomeWokApp();
    }

    private void runHomeWokApp() {
        boolean continueProgram = true;
        String userCommand = null;

        setup();

        while (continueProgram) {
            initialAppMenu();
            userCommand = userInput.next();
            userCommand = userCommand.toLowerCase();

            if (userCommand.equals("q")) {
                continueProgram = false;
            } else {
                userCommand(userCommand);
            }
        }

        System.out.println("\n Bye! Have a nice day!");
    }

    private void setup() {
        homeworkList = new HomeworkList();

        recipeList = new RecipeList();
        recipeList.addRecipe("Cookies");

        Recipe cookies = recipeList.get(0);
        cookies.addIngredient("Butter", 115);
        cookies.addIngredient("Brown sugar", 90);
        cookies.addIngredient("Caster sugar", 50);
        cookies.addIngredient("Vanilla", 4);
        cookies.addIngredient("Eggs", 50);
        cookies.addIngredient("Flour", 210);
        cookies.addIngredient("Baking soda", 2);
        cookies.addIngredient("Chocolate chips", 150);

        cookies.addDescription("1) Cream the butter and sugar together in a bowl.");
        cookies.addDescription("2) Add the eggs and vanilla to butter mixture. Whisk until smooth.");
        cookies.addDescription("3) Add flour, baking soda, and salt. Fold gently until fully incorporated.");
        cookies.addDescription("4) Add chocolate chips. Fold just until fully combined.");
        cookies.addDescription("5) Chill dough for an hour then place cookies in oven preheated to 350F.");


        userInput = new Scanner(System.in);
    }

    private void initialAppMenu() {
        System.out.println("\n Please pick one of the following:");
        System.out.println("\t h -> Homework Assignments");
        System.out.println("\t r -> Recipes");
        System.out.println("\t q -> Quit program");
    }

    private void userCommand(String userCommand) {
        if (userCommand.equals("h")) {
            nextHomeworkSelection();
        } else if (userCommand.equals("r")) {
            nextRecipeSelection();
        }
    }

    private void homeworkListOptions() {
        if (0 == homeworkList.size()) {
            System.out.println("Great work! You have no homework assignments due!");
        } else {
            returnHomeworkList();
        }
    }


    private void returnHomeworkList() {
        String userSelection = "";
        System.out.println("Here are your current homework assignments: " + homeworkList.getListOfElementTitles()
                + "\n");
        System.out.println("Would you like to view an assignemnt?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("y")) {
            viewAssignments();
        }
    }

    private void viewAssignments() {
        String userSelection;

        System.out.println("Please pick the homework you would like to view: " + homeworkList.getListOfElementTitles());

        userSelection = userInput.next();

        int index = homeworkList.getListOfElementTitles().indexOf(userSelection);
        Homework userChoice = homeworkList.get(index);

        System.out.println("\n" + "Homework: " + userChoice.getName() + "\n");
        System.out.println("Here is the subject and name:");
        System.out.println(userChoice.getSubject() + "\t" + userChoice.getName());
        System.out.println("\n" + "Here's the description:");
        List<String> descriptions = userChoice.getDescription();
        for (String s : descriptions) {
            System.out.println(s);
        }
    }


    private void addHomeworkAssignment() {
        String subjectInput = "";
        String nameInput = "";

        System.out.println("\n Please insert the subject of the homework assignment: ");
        subjectInput = userInput.next();

        System.out.println("\n Please insert the name of the homework assignment");
        nameInput = userInput.next();

        homeworkList.addHomework(subjectInput, nameInput);
        System.out.println("Homework added to assignments!");
    }


    private void nextHomeworkSelection() {
        System.out.println("Would you like to access your homework list or add a new assignment?");
        System.out.println("\t l -> Access homework list");
        System.out.println("\t n -> Add new homework assignment");

        String userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("l")) {
            homeworkListOptions();
        } else {
            addHomeworkAssignment();
        }
    }

    private void nextRecipeSelection() {
        String userSelection = "";

        System.out.println("Would you like to access your recipe list or add a new recipe?");
        System.out.println("\t l -> Access recipe list");
        System.out.println("\t n -> Add new recipe");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("l")) {
            recipeListOptions();
        } else {
            addNewRecipeAndIngredients();
        }
    }

    private void recipeListOptions() {
        String userSelection = "";
        System.out.println("Here are your current recipes: " + recipeList.getListOfElementTitles() + "\n");
        System.out.println("Would you like to view a recipe?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("y")) {
            viewRecipe();
        }
    }


    private String optionProviderIngredients() {
        String userSelection = "";

        System.out.println("Would you like to add a new ingredient to your recipe?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        return userSelection;

    }

    private void addNewRecipeAndIngredients() {
        System.out.println("What is the name of the recipe?");

        Recipe recipeName = new Recipe(userInput.next());
        recipeList.add(recipeName);
        int indexOfRecipe = recipeList.getIndexOf(recipeName);
        String userOption = optionProviderIngredients();


        while (userOption.equals("y")) {
            System.out.println("What is the name of the ingredient?");
            String ingredientName = userInput.next();

            System.out.println("How much of this ingredient (in grams) is needed for this recipe?");
            int ingredientAmount = userInput.nextInt();

            Recipe userRecipe = recipeList.get(indexOfRecipe);
            userRecipe.addIngredient(ingredientName, ingredientAmount);

            if (optionProviderIngredients().equals("n")) {
                System.out.println("\n" + "Ingredients added to recipe!" + "\n");
                addRecipeDescription();
                break;
            }
        }
    }

    private String optionProviderDescription() {
        String userSelection = "";

        System.out.println("Would you like to add a new description to your recipe?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        return userSelection;
    }


    private void addRecipeDescription() {
        String userSelection = "";
        String userOption = optionProviderDescription();
        Recipe chosenRecipe = new Recipe("");

        if (userOption.equals("y")) {
            System.out.println("Please enter recipe name:");
            userSelection = userInput.next();
            int index = recipeList.getListOfElementTitles().indexOf(userSelection);
            chosenRecipe = recipeList.get(index);

            while (userOption.equals("y")) {
                System.out.println("Please enter description:");
                String description = userInput.next();
                chosenRecipe.addDescription(description);

                if (optionProviderDescription().equals("n")) {
                    System.out.println("Directions added to recipe!");
                    break;
                }
            }
        }
    }


    private void viewRecipe() {
        String userSelection;

        System.out.println("Please pick the recipe you would like to view: " + recipeList.getListOfElementTitles());

        userSelection = userInput.next();

        int index = recipeList.getListOfElementTitles().indexOf(userSelection);
        Recipe userChoice = recipeList.get(index);

        System.out.println("\n" + "Recipe: " + userChoice.getName() + "\n");
        System.out.println("Here are the ingredients:");
        List<Ingredient> ingredientList = userChoice.getIngredients();
        for (Ingredient i : ingredientList) {
            System.out.println(i.getIngredientName() + "\t" + i.getAmountNeeded());
        }
        System.out.println("\n" + "Here's how you make it:");
        List<String> descriptions = userChoice.getDescription();
        for (String s : descriptions) {
            System.out.println(s);
        }
    }


}
