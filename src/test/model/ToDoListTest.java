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
    public void setup() {
        todo = new ToDoList();
    }

    @Test
    public void testAddTask() throws TooManyThingsToDo {
        todo.addTask("Test 1!", "01/01/2020", "2");
        todo.addTask("Test 2!", "01/02/2020", "2");
        ArrayList<Task> listOfTask = todo.getTaskList();
        Task t1 = listOfTask.get(0);
        Task t2 = listOfTask.get(1);
        assertEquals(t1.getTaskName(), "Test 1!");
        assertEquals(t2.getTaskName(), "Test 2!");
    }

    @Test
    public void testAddTaskUpperBound() throws TooManyThingsToDo {
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
    public void testAddTaskThrowException() throws TooManyThingsToDo {
        for (int i = 0; i < ToDoList.MAX_INCOMPLETE; i++) {
            todo.addTask("Test!", "01/01/2020", "2");
        }
        try {
            todo.addTask("Too many", "12/31/2019", "2");
            fail("I was not expecting to reach this line of code!");
        } catch (TooManyThingsToDo e) {
            System.out.println("Caught the exception!");
        }
    }

    @Test
    public void testCompleteTask() throws TooManyThingsToDo {
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
    public void testSave() throws IOException, TooManyThingsToDo {
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
    public void testLoad() throws IOException {
        ArrayList<String> array = (ArrayList<String>) todo.load("./data/testLoad.txt");
        assertEquals(array.get(0),"Testing;Thu Oct 03 00:00:00 PDT 2019;false");
    }

    @Test
    public void testSplitOnSpace() {
        ArrayList<String> array = ToDoList.splitOnSpace("hey;you");
        ArrayList<String> actual = new ArrayList<>(Arrays.asList("hey", "you"));
        assertEquals(array, actual);
    }
}
