package model;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a non-primitive element of a list with a description and name
public class Element  {
    protected String name;
    protected List<Description> descriptions;

    //EFFECTS: constructs an element with given name and empty list of descriptions
    public Element(String name) {
        this.name = name;
        descriptions = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds description to list of element descriptions
    public void addDescription(String recipeDescription) {
        Description d = new Description(recipeDescription);
        descriptions.add(d);
    }



    //EFFECTS: returns name of list element
    public String getName() {
        return name;
    }


    //EFFECTS: returns description of list element
    //         description of homework assignment for Homework class
    //         description of steps needed to make recipe for Recipe class
    public List<Description> getDescription() {
        return descriptions;
    }


}



