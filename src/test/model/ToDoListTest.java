package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    private ToDoList todo;

    @BeforeEach
    void setup() {
        todo = new ToDoList();
    }

    @Test
    void testAddTaskRegular() throws TooManyThingsToDo {
        todo.addTask("Test 1!", "01/01/2020", "2");
        todo.addTask("Test 2!", "01/02/2020", "2");
        ArrayList<Task> listOfTask = todo.getTaskList();
        Task t1 = listOfTask.get(0);
        Task t2 = listOfTask.get(1);
        assertEquals(t1.getTaskName(), "Test 1!");
        assertEquals(t2.getTaskName(), "Test 2!");
    }

    @Test
    void testAddTaskUrgent() throws TooManyThingsToDo {
        todo.addTask("Test 1", "01/01/2020", "1");
        Task t1 = todo.getTaskList().get(0);
        assertEquals(t1.getTaskName(), "! Test 1");
    }

    @Test
    void testAddTaskUpperBound() throws TooManyThingsToDo {
        for (int i = 0; i < ToDoList.MAX_INCOMPLETE - 1; i++) {
            todo.addTask("Test!", "01/01/2020", "2");
        }
        try {
            todo.addTask("Last one", "12/31/2019", "2");
        } catch (TooManyThingsToDo e) {
            fail("I was not expecting TooManyThingsToDo!");
        }
    }

    @Test
    void testAddTaskThrowException() throws TooManyThingsToDo {
        for (int i = 0; i < ToDoList.MAX_INCOMPLETE; i++) {
            String name = ("Test" + i);
            todo.addTask(name, "01/01/2020", "2");
        }
        try {
            todo.addTask("Too many", "12/31/2019", "2");
            fail("I was not expecting to reach this line of code!");
        } catch (TooManyThingsToDo e) {
            System.out.println("Caught the exception!");
        }
    }

    @Test
    void testCompleteTask() throws TooManyThingsToDo {
        todo.addTask("Test 1!", "01/01/2020", "2");
        todo.addTask("Test 2!", "01/02/2020", "2");
        todo.completeTask("Test 2!");
        ArrayList<Task> listOfTask = todo.getTaskList();
        Task t1 = listOfTask.get(0);
        Task t2 = listOfTask.get(1);
        assertEquals(t1.getTaskName(), "Test 1!");
        assertFalse(t1.getCompleted());
        assertEquals(t2.getTaskName(), "✓ Test 2!");
        assertTrue(t2.getCompleted());
    }

    @Test
    void testSave() throws IOException, TooManyThingsToDo {
        File file = new File("./data/testSave.txt");
        // clear the file
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        todo.addTask("Test!", "10/03/2019", "2");
        todo.save(file);
        ArrayList<String> array = (ArrayList<String>) Files.readAllLines(Paths.get("./data/testSave.txt"));
        assertEquals(array.get(0),"Test!;Thu Oct 03 00:00:00 PDT 2019;false");
    }

    @Test
    void testLoad() throws IOException {
        ArrayList<String> array = (ArrayList<String>) todo.load("./data/testLoad.txt");
        assertEquals(array.get(0),"Testing;Thu Oct 03 00:00:00 PDT 2019;false");
    }

    @Test
    void testSplitOnSpace() {
        ArrayList<String> array = ToDoList.splitOnSpace("hey;you");
        ArrayList<String> actual = new ArrayList<>(Arrays.asList("hey", "you"));
        assertEquals(array, actual);
    }

    @Test
    public void testAddToList() {
        Task t1 = new RegularTask("Test","10/29/2019");
        assertFalse(todo.getTaskList().contains(t1));
        assertEquals(0, todo.getTaskList().size());
        todo.addToList(t1);
        assertTrue(todo.getTaskList().contains(t1));
        assertEquals(1, todo.getTaskList().size());
    }

    @Test
    void testAddToListAlreadyThere() {
        Task t1 = new RegularTask("Test","10/29/2019");
        assertFalse(todo.getTaskList().contains(t1));
        assertEquals(0, todo.getTaskList().size());
        todo.addToList(t1);
        assertTrue(todo.getTaskList().contains(t1));
        assertEquals(1, todo.getTaskList().size());
        todo.addToList(t1);
        assertTrue(todo.getTaskList().contains(t1));
        assertEquals(1, todo.getTaskList().size());
    }

    @Test
    void testRemoveFromList() {
        Task t1 = new RegularTask("Test","10/29/2019");
        todo.addToList(t1);
        assertTrue(todo.getTaskList().contains(t1));
        todo.removeFromList(t1);
        assertFalse(todo.getTaskList().contains(t1));
    }

    @Test
    void testRemoveFromListNotThere() {
        Task t1 = new RegularTask("Test","10/29/2019");
        assertFalse(todo.getTaskList().contains(t1));
        todo.removeFromList(t1);
        assertFalse(todo.getTaskList().contains(t1));
    }
}
