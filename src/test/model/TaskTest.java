package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task regTask;

    // Reference: https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner?fbclid=IwAR2UKU7ks6DmG9pQEa2Rm06IH0IzC4GV2qYaH5fPhbceBTQcskkVqGhEsMc
    @BeforeEach
    void setup() {
        regTask = new RegularTask("regular", "12/31/2019");
    }

    @Test
    void testSetDueDate() {
        String date = "01/02/2020";
        regTask.setDueDate(date);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dueDateAsString = sdf.format(regTask.getDueDate());
        assertEquals("01/02/2020", dueDateAsString);
    }

    @Test
    void testIsCompletedFalse() {
        assertFalse(regTask.getCompleted());
    }

//    @Test
//    void testIsCompletedTrue() {
//        regTask.setName("Test!");
//        regTask.isCompleted();
//        assertTrue(regTask.getCompleted());
//        assertEquals("✓ Test!", regTask.getTaskName());
//    }

    @Test
    void testAddList() {
        ToDoList toDoList = new ToDoList();
        assertNotEquals(toDoList, regTask.getToDoList());
        regTask.addList(toDoList);
        assertEquals(toDoList, regTask.getToDoList());
    }

    @Test
    void testRemoveList() {
        ToDoList toDoList = new ToDoList();
        regTask.addList(toDoList);
        assertEquals(toDoList, regTask.getToDoList());
        regTask.removeList();
        assertNull(regTask.getToDoList());
    }

    @Test
    void testRemoveListNull() {
        ToDoList toDoList = new ToDoList();
        regTask.addList(toDoList);
        assertEquals(toDoList, regTask.getToDoList());
        regTask.removeList();
        assertNull(regTask.getToDoList());
        regTask.removeList();
        assertNull(regTask.getToDoList());
    }

    @Test
    void testEquals() {
        Task t1 = new RegularTask("regular", "12/31/2019");
        assertEquals(t1, regTask);
    }

    @Test
    void testNotEqualsName() {
        Task t1 = new RegularTask("regular1", "12/31/2019");
        assertNotEquals(t1, regTask);
    }

    @Test
    void testNotEqualsClass() {
        Task t1 = new UrgentTask("regular", "12/31/2019");
        assertNotEquals(t1, regTask);
    }
}
