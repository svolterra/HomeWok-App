package model.tests;

import model.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DescriptionTest {
    private Description descriptionOne;
    private Description descriptionTwo;

    @BeforeEach
    public void setup() {
        descriptionOne = new Description("Add flour to bowl");
        descriptionTwo = new Description("Add sugar to mixture");
    }

    @Test
    public void testSetDescription() {
        descriptionOne.setDescription("Add sugar to mixture");
        descriptionTwo.setDescription("Add flour to bowl");

        String addSugar = descriptionOne.getDescription();
        String addFlour = descriptionTwo.getDescription();

        assertEquals("Add sugar to mixture", addSugar);
        assertEquals("Add flour to bowl", addFlour);
    }

}
