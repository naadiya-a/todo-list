package ui;

import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestToDoList {

    private ToDoList todo;

    @BeforeEach
    public void setup() {
        String input = "4";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        todo = new ToDoList();
    }

    @Test
    public void testAddTask() {
        String input = "01/01/2020";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        todo.addTask("Test!");
        ArrayList<Task> listOfTask = todo.getTaskList();

        for (Task t : listOfTask) {
            assertEquals("Test!", t.getTaskName());
        }
    }
}
