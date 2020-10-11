package model;

import java.util.ArrayList;
import java.util.List;

// Provides a specification of methods common to both HomeworkList and RecipeList
public interface ElementList {


    //EFFECTS: returns size of homework list
    public int size();


    //MODIFIES: this
    //EFFECTS: removes given element from list of list elements
    public void remove(Element e);

    //EFFECTS: returns true if list  contains given element, false otherwise
    public boolean contain(Element e);


    //REQUIRES: i > 0
    //MODIFIES: this
    //EFFECTS: removes element at given index from list
    public void removeAtIndex(int i);



}
