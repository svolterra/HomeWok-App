package ui;

import model.*;

import java.util.ArrayList;
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

    //Code format borrowed from TellerApp project
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

    //Code format borrowed from TellerApp project
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
        cookies.addDescription("3)"
                + "Add flour, baking soda, and salt. Fold gently until fully incorporated.");
        cookies.addDescription("4) Add chocolate chips. Fold just until fully combined.");
        cookies.addDescription("5)"
                + "Chill dough for an hour then place cookies in oven preheated to 350F.");

        userInput = new Scanner(System.in);
    }

    //Code format borrowed from TellerApp project
    //EFFECTS: displays initial menu of application, including all options user can choose
    private void initialAppMenu() {
        System.out.println("\n Please pick one of the following:");
        System.out.println("\t h -> Homework Assignments");
        System.out.println("\t r -> Recipes");
        System.out.println("\t q -> Quit program");
    }

    //Code format borrowed from TellerApp project
    private void userCommand(String userCommand) {
        if (userCommand.equals("h")) {
            nextHomeworkSelection();
        } else if (userCommand.equals("r")) {
            nextRecipeSelection();
        }
    }

    private void nextRecipeSelection() {
        String userSelection = "";

        System.out.println("\n Would you like to access your recipe list or add a new recipe?");
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
        System.out.println("\n Here are your current recipes: " + recipeList.getListOfElementTitles() + "\n");
        System.out.println("\n Would you like to view a recipe?");
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

        System.out.println("\n Would you like to add a new ingredient to your recipe?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        return userSelection;
    }

    private void addNewRecipeAndIngredients() {
        Scanner input = new Scanner(System.in);
        System.out.println("What is the name of the recipe?");

        String recipeName = input.nextLine();

        Recipe newRecipe = new Recipe(recipeName);
        recipeList.add(newRecipe);
        int indexOfRecipe = recipeList.getIndexOf(newRecipe);

        String userOption = optionProviderIngredients();

        while (userOption.equals("y")) {
            System.out.println("\n What is the name of the ingredient?");
            String ingredientName = input.nextLine();

            System.out.println("\n How much of this ingredient (in grams) is needed for this recipe?");
            int ingredientAmount = userInput.nextInt();

            Recipe userRecipe = recipeList.get(indexOfRecipe);
            userRecipe.addIngredient(ingredientName, ingredientAmount);

            if (optionProviderIngredients().equals("n")) {
                System.out.println("\n Ingredients added to recipe!" + "\n");
                addRecipeDescription();
                break;
            }
        }

    }

    private String optionProviderDescription() {
        String userSelection = "";

        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        return userSelection;
    }


    //REQUIRES: one or more ingredients have been added to recipe
    private void addRecipeDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n Would you like to add a new direction to a recipe?");
        Recipe recipe = new Recipe("");
        String userOption = optionProviderDescription();

        if (userOption.equals("y")) {
            System.out.println("\n Please select recipe by typing in its name: " + recipeList.getListOfElementTitles());
            String chosenRecipe = input.nextLine();

            int index = recipeList.getListOfElementTitles().indexOf(chosenRecipe);
            recipe = recipeList.get(index);
        }

        while (userOption.equals("y")) {
            System.out.println("\n Please enter direction in recipe:");
            String description = input.nextLine();

            recipe.addDescription(description);

            System.out.println("\n Direction added to recipe!");
            System.out.println("\n Would you like to add another direction?");

            if (optionProviderDescription().equals("n")) {
                break;
            }
        }
    }


    //REQUIRES: recipe name chosen is already in list of recipes
    private void viewRecipe() {
        Scanner input = new Scanner(System.in);
        String userSelection;


        System.out.println("\n Please select the recipe you would like to view by typing in its name: "
                + recipeList.getListOfElementTitles());

        userSelection = input.nextLine();

        int index = recipeList.getListOfElementTitles().indexOf(userSelection);
        Recipe userChoice = recipeList.get(index);

        System.out.println("\n Recipe: " + userChoice.getName() + "\n");

        System.out.println("Here are the ingredients:");
        List<Ingredient> ingredientList = userChoice.getIngredients();
        for (Ingredient i : ingredientList) {
            System.out.println(i.getIngredientName() + "\t" + i.getAmountNeeded() + "g");
        }

        System.out.println("\n Here's how you make it:");
        int size = userChoice.getDescription().size();

        List<String> descriptions = userChoice.getDescription();

        for (String s : descriptions) {
            System.out.println((descriptions.indexOf(s) + 1) + ") " + s);
        }
    }

    private void homeworkListOptions() {
        if (0 == homeworkList.size()) {
            System.out.println("\n Great work! You have no homework assignments due!");
        } else {
            returnHomeworkList();
        }
    }


    private void returnHomeworkList() {
        String userSelection = "";
        System.out.println("\n Here are your current homework assignments subjects: "
                + homeworkList.getListOfElementSubjects());
        System.out.println("\n Here are your current homework assignment names: "
                + homeworkList.getListOfElementTitles() + "\n");
        System.out.println("Would you like to view the details of an assignment?");
        System.out.println("\t y -> yes");
        System.out.println("\t n -> no");

        userSelection = userInput.next();
        userSelection = userSelection.toLowerCase();

        if (userSelection.equals("y")) {
            viewAssignments();
        }
    }

    private void viewAssignments() {
        Scanner input = new Scanner(System.in);
        String userSelection;

        System.out.println("\n Please select the homework you would like to view by typing in its name: "
                + homeworkList.getListOfElementTitles());

        userSelection = input.nextLine();

        int index = homeworkList.getListOfElementTitles().indexOf(userSelection);
        Homework userChoice = homeworkList.get(index);

        System.out.println("\n Subject:" + userChoice.getSubject());
        System.out.println("\n Name: " + userChoice.getName());

        DueDate date = userChoice.getDueDate();
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        System.out.println("\n Due date in month/day/year format: " + month + "/" + day + "/" + year);

        if (!((userChoice.getDescription().size()) == 0)) {
            System.out.println("\n Description of assignment:");
            List<String> descriptions = userChoice.getDescription();
            for (String s : descriptions) {
                System.out.println(s);
            }
        }
    }


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
        newHomework.setDueDate(day, month, year);

        System.out.println("\n Homework added to assignments!");

        addHomeworkDescription();
    }


    //REQUIRES: name of homework chosen is contained within list of homework assignments
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

}
