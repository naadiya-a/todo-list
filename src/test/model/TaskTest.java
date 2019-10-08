package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task regTask;

    // Reference: https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner?fbclid=IwAR2UKU7ks6DmG9pQEa2Rm06IH0IzC4GV2qYaH5fPhbceBTQcskkVqGhEsMc
    @BeforeEach
    public void setup() {
        regTask = new RegularTask("regular", "12/31/2019");
    }

    @Test
    public void testSetDueDate() {
        String date = "01/02/2020";
        regTask.setDueDate(date);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dueDateAsString = sdf.format(regTask.getDueDate());
        assertEquals("01/02/2020", dueDateAsString);
    }

    @Test
    public void testIsCompletedFalse() {
        assertFalse(regTask.getCompleted());
    }

    @Test
    public void testIsCompletedTrue() {
        regTask.setName("Test!");
        regTask.isCompleted();
        assertTrue(regTask.getCompleted());
        assertEquals("✓ Test!", regTask.getTaskName());
    }
}
