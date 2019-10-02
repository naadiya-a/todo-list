package model;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
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
        todo.addTask("task 1", "10/01/2019");
        todo.addTask("task 2", "11/01/2019");
        todo.save();
        //(iterate?) and check that each task is in the file
        //!!! AND change save method so that it takes a file name as an argument,
        //      to avoid overwriting my real todoListData file
    }

    @Test
    public void testLoad() throws IOException, ClassNotFoundException {
        // !!!
        //manually create a file check that each part of the string prints what i expect
    }
}
