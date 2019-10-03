package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {

    private ToDoList todo;

    @BeforeEach
    public void setup() {
        todo = new ToDoList();
    }

    @Test
    public void testAddTask() {
        todo.addTask("Test!", "01/01/2020");
        ArrayList<Task> listOfTask = todo.getTaskList();

        for (Task t : listOfTask) {
            assertEquals("Test!", t.getTaskName());
        }
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
