package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DueDateTest {
    private DueDate dateTest;

    @BeforeEach
    public void setup() {
        dateTest = new DueDate(19, 02, 2000);
    }

    @Test
    public void testGetDay() {
        assertEquals(19, dateTest.getDay());
    }

    @Test
    public void testGetMonth() {
        assertEquals(02, dateTest.getMonth());
    }

    @Test
    public void testGetYear() {
        assertEquals(2000, dateTest.getYear());
    }

}
