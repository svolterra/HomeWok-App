package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeworkListTest {
    private HomeworkList homeworkList;


    @Test
    public void testConstructor() {
        homeworkList = new HomeworkList();
        assertEquals(0, homeworkList.size());
    }

    @BeforeEach
    public void setup() {
        homeworkList = new HomeworkList();
        homeworkList.addHomework("CPSC 210", "Term Project Phase One");

    }


    @Test
    public void testAddHomework() {
        Homework project = homeworkList.get(0);

        assertEquals(1, homeworkList.size());
        assertEquals("CPSC 210", project.getSubject());
        assertEquals("Term Project Phase One", project.getName());

        homeworkList.addHomework("PSYC 304", "Study Chapters 2 and 3");
        Homework study = homeworkList.get(1);

        assertEquals(2, homeworkList.size());
        assertEquals("PSYC 304", study.getSubject());
        assertEquals("Study Chapters 2 and 3", study.getName());
    }

    @Test
    public void testRemove() {
        assertEquals(1, homeworkList.size());
        Homework project = homeworkList.get(0);

        homeworkList.remove(project);
        assertEquals(0, homeworkList.size());
    }

    @Test
    public void testContain() {
        homeworkList.addHomework("PSYC 301", "Listen to Lecture");
        assertEquals(2, homeworkList.size());
        Homework project = homeworkList.get(0);
        Homework lectureRecording = homeworkList.get(1);

        assertTrue(homeworkList.contain(project));
        assertTrue(homeworkList.contain(lectureRecording));
    }


    @Test
    public void testRemoveAtIndex() {
        homeworkList.addHomework("PSYC 301", "Listen to Lecture");
        homeworkList.addHomework("COGS 303", "Read Required Material");

        Homework project = homeworkList.get(0);
        Homework lectureRecording = homeworkList.get(1);
        Homework reading = homeworkList.get(2);


        homeworkList.removeAtIndex(0);
        assertFalse(homeworkList.contain(project));
        assertEquals(2, homeworkList.size());
        homeworkList.removeAtIndex(0);

        assertFalse(homeworkList.contain(lectureRecording));
        assertEquals(1, homeworkList.size());

        homeworkList.removeAtIndex(0);
        assertFalse(homeworkList.contain(reading));
        assertEquals(0, homeworkList.size());
    }

    @Test
    public void testGetListOfElementTitles() {
        homeworkList.addHomework("PSYC 301", "Listen to Lecture");
        homeworkList.addHomework("COGS 303", "Read Required Material");

        List<String> homework = homeworkList.getListOfElementTitles();
        assertEquals(3, homework.size());
        assertEquals("CPSC 210", homework.get(0));
        assertEquals("PSYC 301", homework.get(1));
        assertEquals("COGS 303", homework.get(2));

    }


}
