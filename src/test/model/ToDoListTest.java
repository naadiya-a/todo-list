package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    private ToDoList todo;

    @BeforeEach
    public void setup() {
        todo = new ToDoList();
    }

    @Test
    public void testAddTask() {
        todo.addTask("Test 1!", "01/01/2020");
        todo.addTask("Test 2!", "01/02/2020");
        ArrayList<Task> listOfTask = todo.getTaskList();
        Task t1 = listOfTask.get(0);
        Task t2 = listOfTask.get(1);
        assertEquals(t1.getTaskName(), "Test 1!");
        assertEquals(t2.getTaskName(), "Test 2!");
    }

    @Test
    public void testCompleteTask() {
        todo.addTask("Test 1!", "01/01/2020");
        todo.addTask("Test 2!", "01/02/2020");
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
    public void testSave() throws IOException {
        File file = new File("./data/testSave.txt");
        // clear the file
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        todo.addTask("Test!", "10/03/2019");
        todo.save(file);
        ArrayList<String> array = (ArrayList<String>) Files.readAllLines(Paths.get("./data/testSave.txt"));
        assertEquals(array.get(0),"Test!;Thu Oct 03 00:00:00 PDT 2019;false");
    }

    @Test
    public void testLoad() throws IOException {
        ArrayList<String> array = (ArrayList<String>) todo.load("./data/testLoad.txt");
        assertEquals(array.get(0),"Testing;Thu Oct 03 00:00:00 PDT 2019;false");
    }
}
