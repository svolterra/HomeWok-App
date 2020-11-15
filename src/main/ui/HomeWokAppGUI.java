package ui;


import model.Recipe;
import model.RecipeBook;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.sound.SoundUtils;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//Main class to run the HomeWokApp
//Class modeled based on:
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TextSamplerDemoProject/src/components/TextSamplerDemo.java
// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ButtonDemoProject/src/components/ButtonDemo.java
// https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete.git
// https://stackoverflow.com/questions/3780406/how-to-play-a-sound-alert-in-a-java-application
public class HomeWokAppGUI extends JFrame implements ActionListener {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 600;

    private static final String JSON_STORE = "./data/recipesGUI.json";

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private JPanel textPanel;

    private JLabel recipeLabel;
    private JLabel subjectLabel;
    private JLabel dueDateLabel;
    private JLabel homeworkSubjectLabel;

    private JTextField nameField;
    private JTextField amountField;
    private JTextField ingredientField;
    private JTextField directionText;
    private JTextField subjectTextField;

    protected JTextArea centerArea;
    private JTextArea ingredientTextArea;
    private JTextArea directionTextArea;

    private JButton setName;
    private JButton setIngredients;
    private JButton setSubjectButton;
    private JButton setDueDateButton;

    private JFormattedTextField dueDate;

    private Recipe recipe;
    public RecipeBook recipeBook;

    // EFFECTS: Constructs a graphical user interface (gui) for the HomeWok Application with a new title, recipe book,
    //          JsonReader, JsonWriter, and initializes graphics
    public HomeWokAppGUI() {
        super("HomeWok App");
        recipeBook = new RecipeBook("My recipes");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        initializeGraphics();
    }


    // MODIFIES: this, textPanel
    // EFFECTS: initializes the gui graphics, panels, and buttons
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        textPanel = new JPanel();
        add(textPanel, BorderLayout.CENTER);

        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setSize(new Dimension(0, 10));

        addTextArea();
        addButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    // MODIFIES: centreArea
    // EFFECTS: adds a center text area to the gui
    private void addTextArea() {
        centerArea = new JTextArea();
        centerArea.setColumns(20);
        centerArea.setLineWrap(true);
        centerArea.setRows(5);
        centerArea.setWrapStyleWord(true);
        centerArea.setVisible(true);

        textPanel.add(centerArea);
    }



     // MODIFIES: buttonArea
     // EFFECTS:  adds buttons to gui
    private void addButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 10));
        add(buttonArea, BorderLayout.WEST);

        addRecipeButton(buttonArea);

        addSaveButton(buttonArea);

        addViewRecipeButton(buttonArea);

        addLoadButton(buttonArea, "Load Recipes", "load");

        addHomeworkButton(buttonArea);

    }

    // MODIFIES: buttonArea, addRecipeButton
    // EFFECTS: adds a button to add recipes to gui
    private void addRecipeButton(JPanel buttonArea) {
        JButton addRecipeButton = new JButton("Add Recipe");
        buttonArea.add(addRecipeButton);
        addRecipeButton.setActionCommand("add");
        addRecipeButton.addActionListener(this);
    }

    // MODIFIES: buttonArea, saveButton
    // EFFECTS: adds a button to save recipes to gui
    private void addSaveButton(JPanel buttonArea) {
        JButton saveButton = new JButton("Save Recipe");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);
        buttonArea.add(saveButton);
    }


    // MODIFIES: buttonArea, viewRecipeButton
    // EFFECTS: adds a button to view recipes to gui
    private void addViewRecipeButton(JPanel buttonArea) {
        JButton viewRecipeButton = new JButton("View Recipes");
        buttonArea.add(viewRecipeButton);
        viewRecipeButton.setActionCommand("view");
        viewRecipeButton.addActionListener(this);
    }

    // MODIFIES: buttonArea, loadButton
    // EFFECTS: adds a button to load recipes to gui
    private void addLoadButton(JPanel buttonArea, String s, String actionCommand) {
        JButton loadButton = new JButton(s);
        loadButton.setActionCommand(actionCommand);
        loadButton.addActionListener(this);
        buttonArea.add(loadButton);
    }

    // MODIFIES: buttonArea, addHomeworkButton
    // EFFECTS: adds a button to add homework to gui
    private void addHomeworkButton(JPanel buttonArea) {
        JButton addHomeworkButton = new JButton("Add Homework");
        addHomeworkButton.setActionCommand("add homework");
        addHomeworkButton.addActionListener(this);
        buttonArea.add(addHomeworkButton);
    }


    // EFFECTS: adds an associated action to each recipe-related button in gui
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            setAddRecipeButtonFunction();
        }
        if (e.getActionCommand().equals("set")) {
            setRecipeNameButtonFunction();
        }
        if (e.getActionCommand().equals("view")) {
            viewRecipesButtonFunction();
        }
        if (e.getActionCommand().equals("set ingredient")) {
            setIngredientButtonFunction();
        }
        if (e.getActionCommand().equals("add direction")) {
            setDirectionButtonFunction();
        }
        if (e.getActionCommand().equals("save")) {
            saveButtonFunction();
        }
        if (e.getActionCommand().equals("load")) {
            loadButtonFunction();
        }
        actionPerformedHomework(e);
    }

    // EFFECTS: adds an associated action to each homework-related button in gui
    private void actionPerformedHomework(ActionEvent e) {
        if (e.getActionCommand().equals("add homework")) {
            setAddHomeworkButtonFunction();
        }
        if (e.getActionCommand().equals("set subject")) {
            setSetHomeworkButtonFunction();
        }
        if (e.getActionCommand().equals("set due date")) {
            setHomeworkDueDateFunction();
        }
    }

    // MODIFIES: centerArea, setName
    // EFFECTS: creates a new set recipe name button and adds it to a gui
    private void initializeSetRecipeNameButton() {
        setName = new JButton("Set Recipe Name");
        setName.setBounds(100, 75, 150, 25);
        setName.setActionCommand("set");
        setName.addActionListener(this);
        centerArea.add(setName);
    }


    /*
     * MODIFIES: recipeName, nameField, centerArea
     * EFFECTS: adds Recipe name: text to gui
     *          adds associated text field to allow user to set recipe name
     *          initializes a recipe label to be modified following the click of Set Recipe Name button
     */
    private void setRecipeName() {
        recipeLabel = new JLabel("");
        recipeLabel.setBounds(200, 270, 200, 25);

        JLabel recipeName = new JLabel("Recipe name:");
        recipeName.setBounds(10, 1, 100, 100);

        nameField = new JTextField(5);
        nameField.setBounds(100, 41, 200, 25);

        initializeSetRecipeNameButton();

        centerArea.add(recipeLabel);
        centerArea.add(recipeName);
        centerArea.add(nameField);
    }

    // MODIFIES: centerArea, recipeLabel, recipeBook
    // EFFECTS: sets recipe name and adds it to gui following the click of Set Recipe Name button
    private void setRecipeNameButtonFunction() {
        String recipeName = nameField.getText();
        recipeLabel.setText(recipeName);
        recipeLabel.setForeground(Color.pink);
        recipeLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        recipe = new Recipe(recipeName);

        recipeBook.addRecipeToBook(recipe);
    }


    // MODIFIES: centerArea, setIngredients
    // EFFECTS: creates a add ingredient button and adds it to gui
    private void initializeAddIngredients() {
        setIngredients = new JButton("Add Ingredient");
        setIngredients.setBounds(100, 150, 150, 25);
        setIngredients.setActionCommand("set ingredient");
        setIngredients.addActionListener(this);
        centerArea.add(setIngredients);
    }


    /*
     * MODIFIES: ingredients, ingredientField, amounts, amountField, centerArea
     * EFFECTS: adds Ingredient: , Amount(g): , and Ingredients: text to gui
     *          adds associated text field to allow user to set ingredient names and amounts
     *          initializes a ingredients label to be modified following the click of Add Ingredient button
     */
    private void setIngredients() {
        JLabel ingredients = new JLabel("Ingredient:");
        ingredients.setBounds(10, 110, 200, 25);

        ingredientField = new JTextField(10);
        ingredientField.setBounds(100, 110, 200, 25);

        JLabel amounts = new JLabel("Amount (g):");
        amounts.setBounds(315, 110, 200, 25);

        amountField = new JTextField(10);
        amountField.setBounds(400, 110, 100, 25);

        ingredientTextArea = new JTextArea("Ingredients: \n \n");
        ingredientTextArea.setBounds(20, 300, 200, 100);
        ingredientTextArea.setEditable(false);

        initializeAddIngredients();

        centerArea.add(ingredients);
        centerArea.add(ingredientField);
        centerArea.add(amounts);
        centerArea.add(amountField);
    }

    // MODIFIES: centerArea, ingredientText, amountText
    // EFFECTS: adds an ingredient name and amount to gui following the click of Add Ingredient button
    private void setIngredientButtonFunction() {
        String ingredientText = ingredientField.getText();
        String amountText = amountField.getText();
        ingredientTextArea.append(ingredientText + "\t" + amountText + "g" + "\n");
        ingredientField.selectAll();

        ingredientTextArea.setCaretPosition(ingredientTextArea.getDocument().getLength());
        centerArea.add(ingredientTextArea);

        recipeBook.addIngredientToRecipe(ingredientText, Integer.parseInt(amountText), recipe);
    }


    // MODIFIES: centerArea, addDirection
    // EFFECTS: creates a add direction button and adds it to gui
    private void initializeAddDirectionButton() {
        JButton addDirection = new JButton("Add Direction");
        addDirection.setBounds(100, 225, 150, 25);
        addDirection.setActionCommand("add direction");
        addDirection.addActionListener(this);
        centerArea.add(addDirection);
    }



    /*
     * MODIFIES: direction, directionText, directionTextArea, centerArea
     * EFFECTS: adds Directions: text to gui
     *          adds associated text field to allow user to add a direction to current recipe
     *          adds a Directions title to be added following the click of Add Direction button
     */
    private void setDirections() {
        JLabel directions = new JLabel("Directions:");
        directions.setBounds(10, 190, 200, 25);

        directionText = new JTextField();
        directionText.setBounds(100, 190, 400, 25);

        directionTextArea = new JTextArea("Directions: \n \n ");
        directionTextArea.setBounds(250, 300, 300, 100);
        directionTextArea.setEditable(false);

        initializeAddDirectionButton();

        centerArea.add(directions);
        centerArea.add(directionText);
    }



    // MODIFIES: directions, amountText
    // EFFECTS: adds a new recipe direction to gui following the click of Add Ingredient button
    private void setDirectionButtonFunction() {
        String directions = directionText.getText();
        directionTextArea.append(directions + "\n");
        directionTextArea.selectAll();

        directionTextArea.setCaretPosition(directionTextArea.getDocument().getLength());
        centerArea.add(directionTextArea);

        recipeBook.addDescriptionToRecipe(recipe, directions);
    }


    // MODIFIES: centerArea, this
    // EFFECTS: adds a sound effect and save function to gui
    private void saveButtonFunction() {
        try {
            SoundUtils.tone(10000, 15);
        } catch (LineUnavailableException lineUnavailableException) {
            //
        }
        try {
            jsonWriter.open();
            jsonWriter.write(recipeBook);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n Unable to write to file" + JSON_STORE);
        }
    }


    // MODIFIES: centerArea, this
    // EFFECTS: adds a sound effect and load function to gui
    private void loadButtonFunction() {
        try {
            SoundUtils.tone(7000, 10);
        } catch (LineUnavailableException lineUnavailableException) {
            //
        }
        try {
            recipeBook = jsonReader.read();
        } catch (IOException e) {
            System.out.println("\n Unable to read from file: " + JSON_STORE);

        }
    }


    // MODIFIES: recipeFrame, recipeFrameScrollPane, recipeArea, recipeBookText
    // EFFECTS: allows user to view current recipes of the program in a new window
    //          modifies font of Recipe Book text
    public void viewRecipesButtonFunction() {
        JFrame recipeFrame = new JFrame();
        recipeFrame.setSize(700, 400);
        recipeFrame.setVisible(true);

        JScrollPane recipeFrameScrollPane = new JScrollPane();

        recipeFrame.add(recipeFrameScrollPane);

        JTextArea recipeArea = new JTextArea();
        recipeArea.setBounds(10,100, 300, 300);
        recipeFrame.add(recipeArea);

        JLabel recipeBookText = new JLabel("Recipe Book \n \n");
        recipeBookText.setFont(new Font("SansSerif", Font.BOLD, 15));
        recipeBookText.setBounds(300, 0, 150, 25);
        recipeBookText.setForeground(Color.pink);
        recipeArea.add(recipeBookText);

        recipeArea.setText("");
        for (Recipe r : recipeBook.getRecipes().getList()) {
            recipeArea.append("\n Recipe: " + r.getName());
            recipeArea.append("\n Ingredients: " + r.getIngredientNames());
            recipeArea.append("\n Amount: " + r.getIngredientAmount());
            recipeArea.append("\n Directions: " + r.getDescriptionString());
        }

    }


    /*
     * EFFECTS: initializes the set recipe name button, add ingredient button, and add direction buttons
     *          initializes buttons' associated text fields to allow user to input values
     *          initializes functions of buttons
     */
    private void setAddRecipeButtonFunction() {
        centerArea.removeAll();
        setRecipeName();
        setIngredients();
        setDirections();
    }


    // MODIFIES: centerArea, setSubjectButton
    // EFFECTS: creates a set title button and adds it to gui
    private void initializeSetSubjectButton() {
        setSubjectButton = new JButton("Set Title");
        setSubjectButton.setBounds(330, 52, 100, 25);
        setSubjectButton.setActionCommand("set subject");
        setSubjectButton.addActionListener(this);
        centerArea.add(setSubjectButton);
    }



    // MODIFIES: subjectLabel, centerArea
    // EFFECTS: adds Homework Title: text and associated text field to gui
    //          sets up set subject button
    private void setHomeworkSubject() {
        subjectLabel = new JLabel("Homework Title: ");
        subjectLabel.setBounds(10, 50, 200, 25);
        centerArea.add(subjectLabel);

        subjectTextField = new JTextField(5);
        subjectTextField.setBounds(120, 50, 200, 25);
        centerArea.add(subjectTextField);

        initializeSetSubjectButton();

    }


    // MODIFIES: dueDateLabelTextArea, dueDateText, centerArea
    // EFFECTS: adds a Due Date: text to gui
    //          sets due date following the click of the set due date button and adds it to gui
    private void setHomeworkDueDateFunction() {
        JLabel dueDateLabelTextArea = new JLabel("Due Date: ");
        dueDateLabelTextArea.setBounds(120, 310, 200, 25);

        String dueDateText = dueDate.getText();
        JLabel setDueDateText = new JLabel(dueDateText);
        setDueDateText.setBounds(250, 310, 200, 25);

        centerArea.add(dueDateLabelTextArea);
        centerArea.add(setDueDateText);

    }

    // MODIFIES: homeworkSubjectLabel, homeworkSubject, centerArea
    // EFFECTS: adds the homework title to gui following the click of Set Title button
    //          modifies font of homework title
    private void setSetHomeworkButtonFunction() {
        homeworkSubjectLabel = new JLabel("");
        homeworkSubjectLabel.setBounds(150, 270, 400, 25);
        centerArea.add(homeworkSubjectLabel);

        String homeworkSubject = subjectTextField.getText();

        homeworkSubjectLabel.setText(homeworkSubject);
        homeworkSubjectLabel.setForeground(Color.pink);
        homeworkSubjectLabel.setFont(new Font("SansSerif", Font.BOLD, 15));

    }

    // MODIFIES: setDueDateButton, centerArea
    // EFFECTS: creates a set due date button and adds it to gui
    private void initializeDueDateButton() {
        setDueDateButton = new JButton("Set Due Date");
        setDueDateButton.setBounds(320, 105, 125, 25);
        setDueDateButton.setActionCommand("set due date");
        setDueDateButton.addActionListener(this);
        centerArea.add(setDueDateButton);
    }



    // MODIFIES: dueDateLabel, dueDate, centerArea
    // EFFECTS: add Due Date: text to gui and associated text field
    private void setHomeworkDueDate() {
        dueDateLabel = new JLabel("Due Date:");
        dueDateLabel.setBounds(20, 110, 200, 25);
        centerArea.add(dueDateLabel);

        dueDate = new JFormattedTextField(java.util.Calendar.getInstance().getTime());
        dueDate.setBounds(120, 110, 100, 25);

        centerArea.add(dueDate);

        initializeDueDateButton();
    }

    // EFFECTS: initializes set title homework button, and the set due date button
    //          and adds associated text fields and functions
    private void setAddHomeworkButtonFunction() {
        centerArea.removeAll();
        setHomeworkSubject();
        setHomeworkDueDate();
    }


    // EFFECTS: runs the HomeWok application graphical user interface
    public static void main(String[] args) {
        new HomeWokAppGUI();
    }

}
