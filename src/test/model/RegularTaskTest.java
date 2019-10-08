package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class RegularTaskTest {

    private Task testTask;

    // Reference: https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner?fbclid=IwAR2UKU7ks6DmG9pQEa2Rm06IH0IzC4GV2qYaH5fPhbceBTQcskkVqGhEsMc
    @BeforeEach
    public void setup() {
        testTask = new RegularTask("null", "12/31/2019");
    }

    @Test
    public void testSetName() {
        String name = "Test";
        testTask.setName(name);
        assertEquals("Test", testTask.getTaskName());
    }
}
