package model;

import model.Task;
import model.ToDoList;
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
}
