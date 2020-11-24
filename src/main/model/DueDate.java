package model;

import model.exceptions.InvalidDateException;

//Represents a due date for homework assignments with a day/month/year date format
public class DueDate {
    private int day;
    private int month;
    private int year;


    /*
     *EFFECTS: constructs a due date of given day, month, and year;
     *         if day <= 0 or day > 31, or if month <= 0 or month > 12
     *         or if year <= 0, throws InvalidDateException
     */
    public DueDate(int day, int month, int year) throws InvalidDateException {
        if (day <= 0 || month <= 0 || year <= 0 || day > 31 || month > 12) {
            throw new InvalidDateException();
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    //EFFECTS: returns day of due date
    public int getDay() {
        return day;
    }

    //EFFECTS: returns month of due date
    public int getMonth() {
        return month;
    }

    //EFFECTS: returns year of due date
    public int getYear() {
        return year;
    }

}
