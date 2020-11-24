package model.tests;

import model.Description;
import model.DueDate;
import model.Homework;
import model.exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Tests for the Homework class
public class HomeworkTest {
    private Homework testHomework;
    List<Description> testDescription;


    @BeforeEach
    public void setup() {
        testHomework = new Homework("CPSC 210", "Project Phase One");
        testDescription = testHomework.getDescription();
    }

    @Test
    public void testHomeworkConstructor() {
        assertEquals("CPSC 210", testHomework.getSubject());
        assertEquals("Project Phase One", testHomework.getName());
        assertEquals(0, testDescription.size());
        assertFalse(testHomework.getHomeWorkDone());

    }

    @Test
    public void testSetValidDueDate() {
        try {
            testHomework.setDueDate(14, 10, 2020);
            // pass
        } catch (InvalidDateException e) {
            fail();
        }

        DueDate updatedDueDate = testHomework.getDueDate();
        assertEquals(14, updatedDueDate.getDay());
        assertEquals(10, updatedDueDate.getMonth());
        assertEquals(2020, updatedDueDate.getYear());

    }

    @Test
    public void testSetInvalidDueDateExceptionThrown() {
        try {
            testHomework.setDueDate(0, 0, 0);
            fail();
        } catch (InvalidDateException e) {
           // pass
        }
    }

    @Test
    public void testIsHomeworkDoneTrue() {
        testHomework.setRepeat(true);
        assertTrue(testHomework.getHomeWorkDone());
    }

    @Test
    public void testIsHomeworkDoneFalse() {
        testHomework.setRepeat(false);
        assertFalse(testHomework.getHomeWorkDone());
    }

    @Test
    public void testAddOneDescription() {
        testHomework.addDescription("Finish phase one of term project by Wednesday");

        assertEquals(1, testDescription.size());
        assertEquals("Finish phase one of term project by Wednesday", testDescription.get(0).getDescription());
    }

    @Test
    public void testAddSeveralDescriptions() {
        testHomework.addDescription("Finish task 1 of project");
        testHomework.addDescription("Finish task 2 of project");
        testHomework.addDescription("Finish task 3 of project");
        testHomework.addDescription("Finish task 4 of project");
        testHomework.addDescription("Finish task 5 of project");

        assertEquals(5, testDescription.size());
        assertEquals("Finish task 1 of project", testDescription.get(0).getDescription());
        assertEquals("Finish task 2 of project", testDescription.get(1).getDescription());
        assertEquals("Finish task 3 of project", testDescription.get(2).getDescription());
        assertEquals("Finish task 4 of project", testDescription.get(3).getDescription());
        assertEquals("Finish task 5 of project", testDescription.get(4).getDescription());


    }
}
