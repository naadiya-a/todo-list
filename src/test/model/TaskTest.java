package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private Task regTask;

    // Reference: https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner?fbclid=IwAR2UKU7ks6DmG9pQEa2Rm06IH0IzC4GV2qYaH5fPhbceBTQcskkVqGhEsMc
    @BeforeEach
    void setup() {
        regTask = new RegularTask("regular", "12/31/2019");
    }

    @Test
    void testSetName() {
        regTask.setName("Test");
        assertEquals("Test", regTask.getTaskName());
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
    void testInvalidDueDate() {
        String date = "40/12";
        regTask.setDueDate(date);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dueDateAsString = sdf.format(regTask.getDueDate());
        assertNotEquals(date, dueDateAsString);
    }

    @Test
    void testIsCompletedFalse() {
        assertFalse(regTask.getCompleted());
    }

    @Test
    void testIsCompletedTrue() {
        ToDoList toDoList = new ToDoList();
        toDoList.addToIncompleteMap(regTask);
        regTask.isCompleted();
        assertTrue(regTask.getCompleted());
    }

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
    void testEqualsTwoOfAKind() {
        Task t1 = new RegularTask("regular", "12/31/2019");
        assertEquals(t1, regTask);
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(regTask, regTask);
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

    @Test
    void testAddObserverEmpty() {
        assertEquals(0, regTask.getObservers().size());
    }

    @Test
    void testAddObserver() {
        ToDoList toDoList = new ToDoList();
        regTask.addObserver(toDoList);
        assertTrue(regTask.getObservers().contains(toDoList));
        assertEquals(1, regTask.getObservers().size());
    }

    @Test
    void testNotifyObservers() {
        ToDoList toDoList = new ToDoList();
        toDoList.addToIncompleteMap(regTask);
        assertTrue(toDoList.getIncompleteTaskMap().containsKey("regular"));
        regTask.addObserver(toDoList);
        regTask.isCompleted();
        assertFalse(toDoList.getIncompleteTaskMap().containsKey("regular"));
    }

    @Test
    void testGetObservers() {
        ToDoList toDoList = new ToDoList();
        regTask.addObserver(toDoList);
        List<Observer> list = new ArrayList<>();
        list.add(toDoList);
        assertEquals(list, regTask.getObservers());
    }

    @Test
    void testEqualsDate() {
        Task diffDate = new RegularTask("regular", "12/30/2019");
        assertNotEquals(diffDate, regTask);
    }

    @Test
    void testEqualsCompleted() {
        Task completed = new RegularTask("regular", "12/31/2019");
        completed.isCompleted();
        assertNotEquals(completed, regTask);
    }
}
