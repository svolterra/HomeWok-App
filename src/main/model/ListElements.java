package model;

import java.util.ArrayList;
import java.util.List;

public abstract class ListElements {
    protected String name;
    protected List<String> description;

    public ListElements(String name) {
        this.name = name;
        description = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds direction to list of recipe directions
    public void addDescription(String description) {
        this.description.add(description);
    }

    //EFFECTS: returns name of list element
    public String getName() {
        return name;
    }


    //EFFECTS: returns description of list element
    //         description of homework assignment for Homework class
    //         description of steps needed to make recipe for Recipe class
    public List<String> getDescription() {
        return description;
    }


}
