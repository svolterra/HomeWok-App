package model.tests;

import model.DueDate;
import model.exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests for the DueDate class
public class DueDateTest {
    private DueDate dateTest;

    @Test
    public void testConstructorNoExceptionThrown() {
        try {
            dateTest = new DueDate(19, 02, 2000);
            // pass
        } catch (InvalidDateException e) {
            fail();
        }
        assertEquals(19, dateTest.getDay());
        assertEquals(02, dateTest.getMonth());
        assertEquals(2000, dateTest.getYear());
    }

    @Test
    public void testConstructorZeroDay() {
        try {
            dateTest = new DueDate(0, 10, 2020);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }

    @Test
    public void testConstructorNegativeDay() {
        try {
            dateTest = new DueDate(-10, 12, 2012);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }

    @Test
    public void testConstructorInvalidDay() {
        try {
            dateTest = new DueDate(32, 10, 2010);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }

    @Test
    public void testConstructorZeroMonth() {
        try {
            dateTest = new DueDate(19, 0, 2000);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }

    @Test
    public void testConstructorNegativeMonth() {
        try {
            dateTest = new DueDate(19, -10, 2000);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }

    @Test
    public void testConstructorInvalidMonth() {
        try {
            dateTest = new DueDate(31, 13, 2020);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }

    @Test
    public void testConstructorZeroYear() {
        try {
            dateTest = new DueDate(19, 12, 0);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }

    @Test
    public void testConstructorNegativeYear() {
        try {
            dateTest = new DueDate(23, 11, -2020);
            fail();
        } catch (InvalidDateException e) {
            // pass
        }
    }


}


