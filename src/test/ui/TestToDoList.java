package ui;

import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestToDoList {

    private ToDoList todo;

    @BeforeEach
    public void setup() {
        String input = "4";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        todo = new ToDoList();
    }

//    @Test
//    public void testInputNewTask() {
//        String input = "New";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        String name = todo.inputNewTask();
//        assertEquals("New", name);
//    }

    @Test
    public void testAddTask() {
        String input = "01/01/2020";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        todo.addTask("Test!");

        for (Task t : todo.taskList) {
            assertEquals("Test!", t.taskName);
        }
    }

//    @Test
//    public void testInputCompleteTask() {
//        String input = "Complete";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        System.setIn(in);
//        String name = todo.inputCompleteTask();
//        assertEquals("Complete", name);
//    }

}

