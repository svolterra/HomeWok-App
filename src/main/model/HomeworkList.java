package model;

import java.util.ArrayList;
import java.util.List;

public class HomeworkList {
    private List<Homework> homeworkList;

    //EFFECTS: Constructs an empty list of homework
    public HomeworkList() {
        homeworkList = new ArrayList<>();
    }

    //EFFECTS: returns list of homework assignments so far
    public List<String> getListOfHomeworkNames() {
        List<String> finalHomeworkList = new ArrayList<>();
        for (Homework h: homeworkList) {
            finalHomeworkList.add(h.getName());
        }
        return finalHomeworkList;
    }

    //MODIFIES: this
    //EFFECTS: adds homework assignment to list of assignments
    public void addHomework(Homework h) {
        homeworkList.add(h);
    }

    //MODIFIES: this
    //EFFECTS: removes given homework assignment from list of assignments
    public void removeHomework(Homework h) {
        homeworkList.remove(h);
    }

    //MODIFIES:this
    //EFFECTS: removes homework assignment at given index from homework list
    public void removeHomeworkAtIndex(int i) {
        Homework homeworkToRemove = homeworkList.get(i);
        homeworkList.remove(homeworkToRemove);
    }

    //EFFECTS: returns true if list of homework assignments contains given list, false otherwise
    public boolean doesListContainHomework(Homework h) {
        boolean doesListContain = homeworkList.contains(h);
        return doesListContain;

    }


}
