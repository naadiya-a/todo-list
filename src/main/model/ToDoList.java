package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ToDoList implements Saveable, Loadable {

    public static final int MAX_INCOMPLETE = 30;
//    private ArrayList<Task> taskList;

    public HashMap<String, Task> taskMap;

    public ToDoList() {
//        taskList = new ArrayList<>();
        taskMap = new HashMap<>();
    }

    // MODIFIES: taskList
    // EFFECTS: adds new task to taskList
    public void addTask(String newTaskName, String newDueDate, String urgent) throws TooManyThingsToDo {
        Task newTask;
        if (taskMap.size() < MAX_INCOMPLETE) {
            if (urgent.equals("2")) {
                newTask = new RegularTask(newTaskName, newDueDate);
            } else {
                newTask = new UrgentTask(newTaskName, newDueDate);
            }
            addToMap(newTask);
        } else {
            throw new TooManyThingsToDo();
        }
    }

    // MODIFIES: this
    // EFFECTS: if task is not already in the list,
    //          adds the task to the list and sets itself as the task's list
    public void addToMap(Task task) {
        if (!taskMap.containsKey(task.getTaskName())) {
            taskMap.put(task.getTaskName(), task);
            task.addList(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: if task is in the list,
    //          removes the task from the list and removes itself as the task's list
    public void removeFromMap(String taskName) {
        if (taskMap.containsKey(taskName)) {
            Task t = taskMap.get(taskName);
            taskMap.remove(taskName);
            t.removeList();
        }
    }

    // EFFECTS: finds completed task from taskList
    public void completeTask(String completeTaskName) {
        Task t = taskMap.get(completeTaskName);
        t.isCompleted();
    }

    public HashMap<String, Task> getTaskMap() {
        return this.taskMap;
    }

    // EFFECTS: writes current to-do list to a text file
    public void save(File fileName) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Task t : taskMap.values()) {
                printWriter.println(t.getTaskName() + ";"
                        + t.getDueDate() + ";"
                        + t.getCompleted());
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot save exception");
        }
    }

    // EFFECTS: reads saved to-do list from text file
    public List<String> load(String filePath) throws IOException {
        return (Files.readAllLines(Paths.get(filePath)));
    }

    public void printLoad(String filePath) {
        List<String> lines;
        try {
            lines = this.load(filePath);
            for (String line : lines) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                System.out.print("Name: " + partsOfLine.get(0) + " ");
                System.out.print("Due date: " + partsOfLine.get(1) + " ");
                System.out.println("Completed status: " + partsOfLine.get(2));
            }
        } catch (IOException e) {
            System.out.println("Cannot load exception");
        }
    }

    // EFFECTS: separates strings at semicolon
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }

    // EFFECTS: prints the name, due date, and completed status of each task in the list
    public void printCollection() {
        if (!this.getTaskMap().isEmpty()) {
            ArrayList<Task> taskList = convertToArray();
            for (Task t : taskList) {
                System.out.print("Name: " + t.getTaskName() + " ");
                System.out.print("Due date: " + t.getDueDate() + " ");
                System.out.println("Completed status: " + t.getCompleted());
            }
        } else {
            System.out.println("Your list is empty.");
        }
    }

    // EFFECTS: converts taskMap values collection into ArrayList
    public ArrayList<Task> convertToArray() {
        Collection<Task> tasks = this.taskMap.values();
        return new ArrayList<>(tasks);
    }
}
