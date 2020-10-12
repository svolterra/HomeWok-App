package model;

//Represents a homework assignment with a subject, name, description, and whether it has been completed or not
public class Homework extends Element {
    private boolean homeworkDone;
    private String subject;
    private DueDate dueDate;

    //EFFECTS: Constructs a homework assignment with given name, due date,
    //         and that is not done yet
    public Homework(String subject, String name) {
        super(name);
        this.subject = subject;
        homeworkDone = false;
    }

    //MODIFIES: this
    //EFFECTS: sets assignment due date
    public void setDueDate(int day, int month, int year) {
        dueDate = new DueDate(day, month, year);
    }

    //MODIFIES: this
    //EFFECTS: sets homework status to done if true, false otherwise
    public void isHomeworkDone(Boolean b) {
        homeworkDone = b;
    }

    //EFFECTS: returns subject of homework assignment
    public String getSubject() {
        return subject;
    }


    //EFFECTS: returns due date
    public DueDate getDueDate() {
        return dueDate;
    }

    //EFFECTS: returns whether homework assignment is done or not
    public boolean getHomeWorkDone() {
        return homeworkDone;
    }


}
