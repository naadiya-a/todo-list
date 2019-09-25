package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask {

    private Task testTask;

    // Reference: https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner?fbclid=IwAR2UKU7ks6DmG9pQEa2Rm06IH0IzC4GV2qYaH5fPhbceBTQcskkVqGhEsMc
    @BeforeEach
    public void setup() {
        String input = "01/01/2020";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        testTask = new Task("null");
    }

    @Test
    public void testSetName() {
        String name = "Test!";
        testTask.setName(name);
        assertEquals("Test!", testTask.getTaskName());
    }

    @Test
    public void testSetDueDate() {
        String date = "01/02/2020";
        testTask.setDueDate(date);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dueDateAsString = sdf.format(testTask.getDueDate());
        assertEquals("01/02/2020", dueDateAsString);
    }

    @Test
    public void testIsCompletedFalse() {
        assertFalse(testTask.getCompleted());
    }

    @Test
    public void testIsCompletedTrue() {
        testTask.setName("Test!");
        testTask.isCompleted();
        assertTrue(testTask.getCompleted());
        assertEquals("✓ Test!", testTask.getTaskName());
    }
}
