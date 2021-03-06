# The HomeWok Application

## About this Application

#### Project Proposal

The ***HomeWok*** application , whose name is derived from the combination of the 
 words *homework* and the cooking pan *wok*, is an application primarily geared towards 
 students with a passion for cooking or baking (or both)! 
 
 This application allows users to both access and modify a list of homework assignments and a list of recipes. 
 The user can either choose to view the pre-existing items in each list or customize new items to add to either list.
 
 By allowing users to keep track of both their assignments and recipes in one place, the ***HomeWok*** application
 encourages the user to pursue their culinary hobbies while simultaneously pursuing their academic success.
 
 This project is of particular interest to me because I, myself, am a university student and thoroughly enjoy baking 
 during my free time. However, I often find that I neglect my hobbies while working on assignments, and an application
 like this would help me stay on top of homework while also encouraging me to pursue my hobbies by 
 combining my assignments and recipes in one place! 
 

 ## User Stories
 

As a user of this application, I want to be able to:
- Access both of my existing lists of homework assignments and recipes, and view each assignment and recipe in detail
- Add a new homework assignment to my list along with its subject, title, due date, and an optional description to 
provide additional details
- Add a new recipe to my list and have the option to add multiple ingredients to the recipe along with their
 associated amounts
- Have the option to add multiple directions to a certain recipe, and have them automatically numbered instead of 
manually entering the numbers.
- Be able to see my customized items appear in the program's list of homework assignments and recipes, and be able 
to access and view them as desired.
- Save my customized list of recipes to file, along with their associated ingredients and directions
- Load my customized list of recipes from file

## Phase 4: Task 2

Tested and designed a robust class in the model package :

- Robust class: DueDate class 
- Robust method in DueDate class: DueDate constructor
- Exception thrown: InvalidDateException
- Exception tested in: DueDateTest class 
- Exception caught in: HomeWokApp class


## Phase 4: Task 3

To improve the design of my code I could have: 

- designed and implemented a superclass that contained the methods common to both RecipeList and HomeworkList, and 
made RecipeList and HomeworkList subclasses that extended it, to increase the level of abstraction in my code.
-  combined RecipeList and RecipeBook into one class, as opposed to creating two separate classes. By doing so, the 
list of recipes would inherit the methods of the List superclass instead of needing to be re-implemented, and the 
coupling between classes would decrease.
 - improved the robustness of my HomeWokApp class methods such that the program does not crash in the event of an 
 invalid user input, and such that the user would be aware of invalid inputs.  
 -  further refactored any duplicated or similar code in the HomeWokApp and HomeWokAppGUI class' methods into separate 
 helper methods such that the code would be more concise and easier to read and understand.
  



