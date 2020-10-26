package model;

import org.json.JSONObject;

//Represents a due date for homework assignments with a day/month/year date format
public class DueDate {
    private int day;
    private int month;
    private int year;

    //REQUIRES: day, month, and year > 0
    //          day <= 31
    //          month <= 12
    //EFFECTS: constructs a due date of given day, month, and year
    public DueDate(int day, int month, int year) {
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


    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("due date day", day);
        json.put("due date month", month);
        json.put("due date year", year);
        return json;
    }
}
