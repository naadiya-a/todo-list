package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    private ToDoList todo;

    @BeforeEach
    void setup() {
        todo = new ToDoList();
    }

    @Test
    void testAddTaskRegular() throws TooManyThingsToDo {
        todo.addTask("Test 1", "01/01/2020", "2");
        Task t1 = new RegularTask("Test 1", "01/01/2020");

        HashMap<String,Task> mapOfAllTask = todo.getAllTaskMap();
        Collection<Task> allValues = todo.getAllTaskMap().values();
        assertTrue(mapOfAllTask.containsKey("Test 1"));
        assertTrue(allValues.contains(t1));

        HashMap<String,Task> mapOfIncTask = todo.getIncompleteTaskMap();
        Collection<Task> incompleteValues = todo.getIncompleteTaskMap().values();
        assertTrue(mapOfIncTask.containsKey("Test 1"));
        assertTrue(incompleteValues.contains(t1));
    }

    @Test
    void testAddTaskUrgent() throws TooManyThingsToDo {
        todo.addTask("Test 1", "01/01/2020", "1");
        Task t1 = new UrgentTask("Test 1", "01/01/2020");

        HashMap<String,Task> mapOfAllTask = todo.getAllTaskMap();
        Collection<Task> allValues = todo.getAllTaskMap().values();
        assertTrue(mapOfAllTask.containsKey("Test 1"));
        assertTrue(allValues.contains(t1));

        HashMap<String,Task> mapOfIncTask = todo.getIncompleteTaskMap();
        Collection<Task> incompleteValues = todo.getIncompleteTaskMap().values();
        assertTrue(mapOfIncTask.containsKey("Test 1"));
        assertTrue(incompleteValues.contains(t1));
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
            fail();
        } catch (TooManyThingsToDo e) {
            System.out.println("Caught the exception!");
        }
    }

    @Test
    void testCompleteTask() throws TooManyThingsToDo {
        todo.addTask("Test 1", "01/01/2020", "2");
        todo.addTask("Test 2", "01/02/2020", "2");
        todo.completeTask("Test 2");
        HashMap<String,Task> mapOfTask = todo.getAllTaskMap();
        Task t1 = mapOfTask.get("Test 1");
        Task t2 = mapOfTask.get("Test 2");
        assertFalse(t1.getCompleted());
        assertTrue(t2.getCompleted());
    }

//    @Test
//    void testSave() throws IOException, TooManyThingsToDo {
//        File file = new File("./data/testSave.txt");
//        // clear the file
//        PrintWriter writer = new PrintWriter(file);
//        writer.print("");
//        writer.close();
//
//        todo.addTask("Test!", "10/03/2019", "2");
//        todo.save(file);
//        ArrayList<String> array = (ArrayList<String>) Files.readAllLines(Paths.get("./data/testSave.txt"));
//        assertEquals(array.get(0),"Test!;Thu Oct 03 00:00:00 PDT 2019;false");
//    }
//
//    @Test
//    void testLoad() throws IOException {
//        ArrayList<String> array = (ArrayList<String>) todo.load("./data/testLoad.txt");
//        assertEquals(array.get(0),"Testing;Thu Oct 03 00:00:00 PDT 2019;false");
//    }
//
//    @Test
//    void testSplitOnSpace() {
//        ArrayList<String> array = ToDoList.splitOnSpace("hey;you");
//        ArrayList<String> actual = new ArrayList<>(Arrays.asList("hey", "you"));
//        assertEquals(array, actual);
//    }

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
    void testAddToMap() {
        Task t1 = new RegularTask("Test","10/29/2019");
        assertFalse(todo.getAllTaskMap().containsKey("Test"));
        assertEquals(0, todo.getAllTaskMap().size());

        todo.addToMap(t1);
        assertTrue(todo.getAllTaskMap().containsKey("Test"));
        assertEquals(1, todo.getAllTaskMap().size());
        assertTrue(todo.getIncompleteTaskMap().containsKey("Test"));
        assertEquals(1, todo.getIncompleteTaskMap().size());
    }

    @Test
    void testAddToListAlreadyThere() {
        Task t1 = new RegularTask("Test","10/29/2019");
        assertFalse(todo.getAllTaskMap().containsKey(t1));
        assertEquals(0, todo.getAllTaskMap().size());

        todo.addToMap(t1);
        assertTrue(todo.getAllTaskMap().containsKey("Test"));
        assertEquals(1, todo.getAllTaskMap().size());

        todo.addToMap(t1);
        assertTrue(todo.getAllTaskMap().containsKey("Test"));
        assertEquals(1, todo.getAllTaskMap().size());
    }

    @Test
    void testRemoveFromList() {
        Task t1 = new RegularTask("Test","10/29/2019");
        todo.addToMap(t1);
        assertTrue(todo.getAllTaskMap().containsKey("Test"));
        assertTrue(todo.getIncompleteTaskMap().containsKey("Test"));

        todo.removeFromMap("Test");
        assertFalse(todo.getAllTaskMap().containsKey("Test"));
        assertFalse(todo.getIncompleteTaskMap().containsKey("Test"));
    }

    @Test
    void testRemoveFromListNotThere() {
        assertFalse(todo.getAllTaskMap().containsKey("Test"));
        todo.removeFromMap("Test");
        assertFalse(todo.getAllTaskMap().containsKey("Test"));
    }

    @Test
    void testConvertToArrayAllMap() {
        Task t1 = new RegularTask("Test","10/29/2019");
        ArrayList<Task> array = new ArrayList<>();
        array.add(t1);
        todo.getAllTaskMap().put(t1.getTaskName(), t1);
        assertEquals(array, todo.convertToArray("1"));
    }

    @Test
    void testConvertToArrayAllMapEmpty() {
        try {
            todo.convertToArray("1");
            fail();
        } catch (NullPointerException e) {}
    }

    @Test
    void testConvertToArrayIncompleteMap() {
        Task t1 = new RegularTask("Test","10/29/2019");
        ArrayList<Task> array = new ArrayList<>();
        array.add(t1);
        todo.getIncompleteTaskMap().put(t1.getTaskName(), t1);
        assertEquals(array, todo.convertToArray("2"));
    }

    @Test
    void testConvertToArrayIncompleteMapEmpty() {
        try {
            todo.convertToArray("2");
            fail();
        } catch (NullPointerException e) {}
    }

    @Test
    void testUpdate() {
        Task t1 = new RegularTask("test", "11/12/2019");
        todo.addToIncompleteMap(t1);
        assertTrue(todo.getIncompleteTaskMap().containsKey("test"));

        todo.update(t1.getTaskName());
        assertFalse(todo.getIncompleteTaskMap().containsKey("test"));
    }
}
