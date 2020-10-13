package model;

import java.util.ArrayList;
import java.util.List;

//A list of homework assignments
public class HomeworkList implements ElementList {
    private List<Homework> homeworkList;

    //EFFECTS: Constructs an empty list of homework
    public HomeworkList() {
        homeworkList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds homework assignment to list of homework
    public void addHomework(String subject, String name) {
        homeworkList.add(new Homework(subject, name));
    }


    //EFFECTS: returns size of homework list
    public int size() {
        return homeworkList.size();
    }

    //EFFECTS: returns homework at given index
    public Homework get(int index) {
        Homework atIndex = homeworkList.get(index);
        return atIndex;
    }

    //MODIFIES: this
    //EFFECTS: removes given homework assignment from list of homework
    public void remove(Element e) {
        homeworkList.remove(e);
    }


    //EFFECTS: returns true if list  contains given homework assignment, false otherwise
    public boolean contain(Element e) {
        return homeworkList.contains(e);
    }


    //REQUIRES: i > 0
    //MODIFIES: this
    //EFFECTS: removes homework assignment at given index from list
    public void removeAtIndex(int i) {
        Homework elementToRemove = homeworkList.get(i);
        homeworkList.remove(elementToRemove);
    }

    //EFFECTS: returns list of homework assignment names so far
    public List<String> getListOfElementTitles() {
        List<String> finalElementList = new ArrayList<>();
        for (Homework h : homeworkList) {
            finalElementList.add(h.getName());
        }
        return finalElementList;
    }

    //EFFECTS: returns list of homework assignment subjects so far
    public List<String> getListOfElementSubjects() {
        List<String> finalElementList = new ArrayList<>();
        for (Homework h : homeworkList) {
            finalElementList.add(h.getSubject());
        }
        return finalElementList;
    }



}

