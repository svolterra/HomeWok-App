package model;

import model.exceptions.InvalidDateException;
import org.json.JSONObject;

//Represents a homework assignment with a subject, name, description, and whether it has been completed or not
public class Homework extends Element {
    private String subject;
    private DueDate dueDate;

    //EFFECTS: Constructs a homework assignment with given name, due date,
    //         and that is not done yet
    public Homework(String subject, String name) {
        super(name);
        this.subject = subject;
    }

    //MODIFIES: this
    //EFFECTS: sets assignment due date
    public void setDueDate(int day, int month, int year) throws InvalidDateException {
        dueDate = new DueDate(day, month, year);
    }


    //EFFECTS: returns subject of homework assignment
    public String getSubject() {
        return subject;
    }


    //EFFECTS: returns due date
    public DueDate getDueDate() {
        return dueDate;
    }


}
