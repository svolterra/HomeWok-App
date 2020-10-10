package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HomeworkTest {
    private Homework testHomework;
    List<String> testDescription;


    @BeforeEach
    public void setup(){
        testHomework = new Homework("CPSC 210", "Project Phase One");
        testDescription = testHomework.getDescription();
    }

    @Test
    public void testHomeworkConstructor(){
        assertEquals("CPSC 210", testHomework.getSubject());
        assertEquals("Project Phase One", testHomework.getName());
        assertEquals(0, testDescription.size());
        assertFalse(testHomework.getHomeWorkDone());

    }

    @Test
    public void testSetDueDate(){
        testHomework.setDueDate(14,10,2020);

        DueDate updatedDueDate = testHomework.getDueDate();
        assertEquals(14, updatedDueDate.getDay());
        assertEquals(10, updatedDueDate.getMonth());
        assertEquals(2020, updatedDueDate.getYear());

    }

    @Test
    public void testIsHomeworkDoneTrue(){
        testHomework.isHomeworkDone(true);
        assertTrue(testHomework.getHomeWorkDone());
    }

    @Test
    public void testIsHomeworkDoneFalse(){
        testHomework.isHomeworkDone(false);
        assertFalse(testHomework.getHomeWorkDone());
    }

    @Test
    public void testAddOneDescription(){
        testHomework.addDescription("Finish phase one of term project by Wednesday");

        assertEquals(1, testDescription.size());
        assertEquals("Finish phase one of term project by Wednesday", testDescription.get(0));
    }

    @Test
    public void testAddSeveralDescriptions(){
        testHomework.addDescription("Finish task 1 of project");
        testHomework.addDescription("Finish task 2 of project");
        testHomework.addDescription("Finish task 3 of project");
        testHomework.addDescription("Finish task 4 of project");
        testHomework.addDescription("Finish task 5 of project");

        assertEquals(5, testDescription.size());
        assertEquals("Finish task 1 of project", testDescription.get(0));
        assertEquals("Finish task 2 of project", testDescription.get(1));
        assertEquals("Finish task 3 of project", testDescription.get(2));
        assertEquals("Finish task 4 of project", testDescription.get(3));
        assertEquals("Finish task 5 of project", testDescription.get(4));




    }
}
