package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrgentTaskTest {
    private Task testTask;

    // Reference: https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner?fbclid=IwAR2UKU7ks6DmG9pQEa2Rm06IH0IzC4GV2qYaH5fPhbceBTQcskkVqGhEsMc
    @BeforeEach
    void setup() {
        testTask = new UrgentTask("null", "12/31/2019");
    }

    @Test
    void testSetName() {
        String name = "Test";
        testTask.setName(name);
        assertEquals("Test", testTask.getTaskName());
    }
}
