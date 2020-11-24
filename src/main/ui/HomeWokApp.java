package ui;

import model.*;
import model.exceptions.InvalidDateException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//NOTE: Class modeled based on: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//Implementation of user interface of HomeWokApp.
public class HomeWokApp {
    private static final String JSON_STORE = "./data/recipes.json";
    private HomeworkList homeworkList;
    private Scanner userInput;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private RecipeBook recipeBook;

    //Note: Code format borrowed from TellerApp project
    //EFFECTS: runs HomeWokApp application
    public HomeWokApp() {
        recipeBook = new RecipeBook("My recipes");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHomeWokApp();
    }

    //Note: Code format borrowed from TellerApp project
    //MODIFIES: this
    //EFFECTS: processes user input to continue interaction with application
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

        System.out.println("\n Thank you! Have a nice day!");
    }

    //Note: Code format borrowed from TellerApp project
    //MODIFIES: this
    //EFFECTS:  initializes ElementLists;
    //          adds a bonus recipe to existing list of recipes that can be accessed and viewed by user
    private void setup() {
        homeworkList = new HomeworkList();

        recipeBook.addRecipeToBook(new Recipe("Cookies"));

        Recipe cookies = recipeBook.get(0);
        cookies.addIngredient("Butter", 115);
        cookies.addIngredient("Brown sugar", 90);
        cookies.addIngredient("Caster sugar", 50);
        cookies.addIngredient("Vanilla", 4);
        cookies.addIngredient("Eggs", 50);
        cookies.addIngredient("Flour", 210);
        cookies.addIngredient("Baking soda", 2);
        cookies.addIngredient("Chocolate chips", 150);

        cookies.addDescription("Cream the butter and sugar together in a bowl.");
        cookies.addDescription("Add the eggs and vanilla to butter mixture. Whisk until smooth.");
        cookies.addDescription("Add flour, baking soda, and salt. Fold gently until fully incorporated."
        );
        cookies.addDescription("Add chocolate chips. Fold just until fully combined.");
        cookies.addDescription("Chill dough for an hour then place cookies in oven preheated to 350F.");

        userInput = new Scanner(System.in);
    }

    //Note: Code format borrowed from TellerApp project
    //EFFECTS: displays initial menu of application, including all options user can choose
    private void initialAppMenu() {
        System.out.println("\n Please pick one of the following:");
        System.out.println("\t h -> Homework Assignments");
        System.out.println("\t r -> Recipes");
        System.out.println("\t q -> Quit program");
    }

    //Note: Code format borrowed from TellerApp project
    //REQUIRES: user input as only as h for homework list, r for recipe list, or q for quit
    //EFFECTS: processes user command to pick one of homework list or recipe list
    private void userCommand(String userCommand) {
        if (userCommand.equals("h")) {
            nextHomeworkSelection();
        } else if (userCommand.equals("r")) {
            nextRecipeSelection();
        }
    }


    /*
     * REQUIRES: user input only as l or n
     * EFFECTS: processes user input to proceed with application;
     *           l allows user to access the existing list of recipes;
     *           n allows user to add a new recipe to list of recipes
     */
    private void nextRecipeSelection() {
        String userSelection = "";

        System.out.println("\n Please select one of the following:");
        System.out.println("\t a -> Access recipe list");
        System.out.println("\t n -> Add new recipe");
        System.out.println("\t s -> Save recipes");
        System.out.println("\t l -> Load recipes");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("a")) {
            recipeListOptions();
        } else if (userSelection.equals("s")) {
            saveRecipes();
        } else if (userSelection.equals("l")) {
            loadRecipes();
        } else {
            addNewRecipeAndIngredients();
        }
    }

    /*
     * REQUIRES: user input only as y for yes or n for no
     * EFFECTS: prints current recipes in program's list of recipes;
     *          y allows user to choose to view a current recipe in detail;
     *          n redirects user back to initial application menu
     */
    private void recipeListOptions() {
        String userSelection = "";
        System.out.println("\n Here are your current recipes: " + recipeBook.getRecipeNames() + "\n");
        System.out.println("\n Would you like to view a recipe?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("y")) {
            viewRecipe();
        }
    }


    /*
     * REQUIRES: user input as y for yes or n for no
     * EFFECTS: y allows user to add an ingredient to a new recipe;
     *          n redirects user back to initial application menu;
     *          returns user's selection
     */
    private String optionProviderIngredients() {
        String userSelection = "";

        System.out.println("\n Would you like to add a new ingredient to your recipe?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        return userSelection;
    }

    /*
     * MODIFIES: this
     * EFFECTS: allows user to initialize a new recipe by typing in the name of the recipe;
     *          allows user to either add one or more ingredients to recipe along with their associated amounts in
     *          grams, or skip this step;
     *          adds user's recipe to existing list of recipes
     */
    private void addNewRecipeAndIngredients() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of the recipe?");

        String recipeName = input.nextLine();

        Recipe newRecipe = new Recipe(recipeName);
        recipeBook.addRecipeToBook(newRecipe);
        int indexOfRecipe = recipeBook.indexOfRecipe(newRecipe);

        String userOption = optionProviderIngredients();

        while (userOption.equals("y")) {
            System.out.println("\n What is the name of the ingredient?");
            String ingredientName = input.nextLine();

            System.out.println("\n How much of this ingredient (in grams) is needed for this recipe?");
            int ingredientAmount = userInput.nextInt();

            Recipe userRecipe = recipeBook.get(indexOfRecipe);
            Ingredient ingredient = new Ingredient(ingredientName, ingredientAmount);
            recipeBook.addIngredientToRecipe(ingredient, userRecipe);

            if (optionProviderIngredients().equals("n")) {
                System.out.println("\n Ingredients added to recipe!" + "\n");
                addRecipeDescription();
                break;
            }
        }

    }


    //REQUIRES: user input only as y for yes or n for no
    // EFFECTS: returns user input
    private String optionProviderDescription() {
        String userSelection = "";

        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        return userSelection;
    }


    /*
     * REQUIRES: one or more ingredients have been added to recipe;
     *           selected recipe already exists in program's recipe list;
     *           user input exactly matches desired recipe name
     * MODIFIES: this
     * EFFECTS:  allows user to either add one or more directions describing how to make selected recipe,
     *           or to skip this step
     */
    private void addRecipeDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n Would you like to add a new direction to a recipe?");
        Recipe recipe = new Recipe("");
        String userOption = optionProviderDescription();

        if (userOption.equals("y")) {
            System.out.println("\n Please select recipe by typing in its name: " + recipeBook.getRecipeNames());
            String chosenRecipe = input.nextLine();

            int index = recipeBook.getRecipeNames().indexOf(chosenRecipe);
            recipe = recipeBook.get(index);
        }

        while (userOption.equals("y")) {
            System.out.println("\n Please enter direction in recipe:");
            String description = input.nextLine();

            recipeBook.addDescriptionToRecipe(recipe, description);

            System.out.println("\n Direction added to recipe!");
            System.out.println("\n Would you like to add another direction?");

            if (optionProviderDescription().equals("n")) {
                break;
            }
        }
    }


    /*
     * REQUIRES: recipe name selected by user is already in existing list of recipes;
     *           user input exactly matches recipe name
     * EFFECTS: allows user to select a recipe to view in detail;
     *          prints selected recipe's name, ingredients + associated amounts, and list descriptions (if available);
     *          if recipe does not include an associated list of descriptions, no description is printed
     */
    private void viewRecipe() {
        System.out.println("\n Please select the recipe you would like to view by typing in its name: "
                + recipeBook.getRecipeNames());

        String userSelection = userRecipeSelection();

        int index = recipeBook.getRecipeNames().indexOf(userSelection);
        Recipe userChoice = recipeBook.get(index);

        System.out.println("\n Recipe: " + userChoice.getName() + "\n");

        System.out.println("Here are the ingredients:");
        List<Ingredient> ingredientList = userChoice.getIngredients();
        for (Ingredient i : ingredientList) {
            System.out.println(i.getIngredientName() + "\t" + i.getAmountNeeded() + "g");
        }

        if (!((userChoice.getDescription().size()) == 0)) {
            System.out.println("\n Here's how you make it:");
            List<Description> descriptions = userChoice.getDescription();
            List<String> directions = new ArrayList<>();
            for (Description d: descriptions) {
                directions.add(d.getDescription());
            }

            for (String s : directions) {
                System.out.println((directions.indexOf(s) + 1) + ") " + s);
            }
        }
    }

    private String userRecipeSelection() {
        Scanner input = new Scanner(System.in);
        String userSelection;
        userSelection = input.nextLine();

        return userSelection;

    }


    //EFFECTS: if existing homework list is empty, returns motivating message to user;
    //         otherwise, user is taken to next step to view existing homework assignments
    private void homeworkListOptions() {
        if (0 == homeworkList.size()) {
            System.out.println("\n Great work! You have no homework assignments due!");
        } else {
            returnHomeworkList();
        }
    }


    /*
     * REQUIRES: user input only as l or n
     * EFFECTS: processes user input to proceed with application:
     *          "l" allows user to access the existing list of homework assignments;
     *          "n" allows user to add a new homework assignment to list of homework
     */
    private void nextHomeworkSelection() {
        System.out.println("\n" + "Would you like to access your homework list or add a new assignment?");
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

    /*
     * REQUIRES:  user input only as y for yes or n for no
     * EFFECTS:   prints current list of homework assignment subjects and names;
     *            allows user to choose whether or not they would like to view the details of a certain homework
     *            assignment
     */
    private void returnHomeworkList() {
        String userSelection = "";
        List<String> subjects = homeworkList.getListOfElementSubjects();
        List<String> names = homeworkList.getListOfElementTitles();
        System.out.println("Here are your current homework assignments: ");
        for (int i = 0; i < subjects.size(); i++) {
            System.out.println((i + 1) + ") " + subjects.get(i) + ": " + names.get(i));
        }
        System.out.println("\n Would you like to view the details of an assignment?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("y")) {
            viewAssignments();
        }
    }

    /*
     * REQUIRES: homework selected by user already exists in program's homework list;
     *           user input exactly matches homework name
     * EFFECTS: prints selected homework assignment's subject, name, due date, and description (if available);
     *          if homework assignment does not include an associated list of descriptions, no description is printed
     */
    private void viewAssignments() {
        System.out.println("\n Please select the homework you would like to view by typing in its name: "
                + homeworkList.getListOfElementTitles());

        String userSelection = userAssignmentChoice();

        int index = homeworkList.getListOfElementTitles().indexOf(userSelection);
        Homework userChoice = homeworkList.get(index);

        System.out.println("\n Subject: " + userChoice.getSubject());
        System.out.println("\n Name: " + userChoice.getName());

        DueDate date = userChoice.getDueDate();
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        System.out.println("\n Due date (month/day/year): " + month + "/" + day + "/" + year);

        if (!((userChoice.getDescription().size()) == 0)) {
            System.out.println("\n Description of assignment:");
            List<Description> descriptions = userChoice.getDescription();
            List<String> directions = new ArrayList<>();
            for (Description d: descriptions) {
                directions.add(d.getDescription());
            }
            for (String s : directions) {
                System.out.println(s);
            }
        }
    }

    private String userAssignmentChoice() {
        Scanner input = new Scanner(System.in);
        String userSelection;

        userSelection = input.nextLine();
        return userSelection;
    }


    /*
     * MODIFIES: this
     * EFFECTS: allows user to initialize a new homework assignment and enter the assignment's subject, name,
     *          and due date, including the day, month, and year assignment is due, then adds it to existing homework
     *          list along with associated details
     */
    private void addHomeworkAssignment() {
        Scanner input = new Scanner(System.in);
        String subjectInput = "";
        String nameInput = "";

        System.out.println("\n Please insert the subject of the homework assignment: ");
        subjectInput = input.nextLine();

        System.out.println("\n Please insert the title of the homework assignment: ");
        nameInput = input.nextLine();

        System.out.println("\n Please enter day assignment is due: ");
        int day = input.nextInt();

        System.out.println("\n Please enter month assignment is due: ");
        int month = input.nextInt();

        System.out.println("\n Please enter year assignment is due: ");
        int year = input.nextInt();

        homeworkList.addHomework(subjectInput, nameInput);
        int indexOfHomework = homeworkList.getListOfElementSubjects().indexOf(subjectInput);
        Homework newHomework = homeworkList.get(indexOfHomework);
        try {
            newHomework.setDueDate(day, month, year);
        } catch (InvalidDateException e) {
            System.out.println("Invalid date!");
        }

        System.out.println("\n Homework added to assignments!");

        addHomeworkDescription();
    }


    /*
     * REQUIRES: name of homework selected by user is contained within list of homework assignment names;
     *           user input exactly matches name of selected homework assignment
     * MODIFIES: this
     * EFFECTS:  allows user to either skip this step or add a description to enter additional details
     *           about selected homework assignment
     */
    private void addHomeworkDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n Would you like to add a homework description?");
        String userOption = optionProviderDescription();

        if (userOption.equals("y")) {
            System.out.println("\n Please select assignment by typing its name: "
                    + homeworkList.getListOfElementTitles());
            String selection = input.nextLine();

            if (homeworkList.getListOfElementTitles().contains(selection)) {
                int index = homeworkList.getListOfElementTitles().indexOf(selection);
                Homework chosen = homeworkList.get(index);

                System.out.println("\n Please enter description of assignment: ");
                String description = input.nextLine();

                chosen.addDescription(description);
                System.out.println("\n Description added to homework!");

            }
        }
    }

    //NOTE: Method modeled based on: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //EFFECTS: saves recipes to file
    private void saveRecipes() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeBook);
            jsonWriter.close();
            System.out.println("\n Saved recipes to file!");
        } catch (FileNotFoundException e) {
            System.out.println("\n Unable to write to file" + JSON_STORE);
        }
    }

    //NOTE: Method modeled based on: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //MODIFIES: this
    //EFFECTS: loads recipes from file
    private void loadRecipes() {
        try {
            recipeBook = jsonReader.read();
            System.out.println("\n Recipes successfully loaded!");
        } catch (IOException e) {
            System.out.println("\n Unable to read from file: " + JSON_STORE);

        }
    }


}
