package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToDoList implements Saveable, Loadable {

    private ArrayList<Task> taskList;

    public ToDoList() {
        taskList = new ArrayList<>();
    }

    // MODIFIES: taskList
    // EFFECTS: adds new task to taskList
    public void addTask(String newTaskName, String newDueDate) {
        Task newTask = new Task(newTaskName, newDueDate);
        taskList.add(newTask);
    }

    public void completeTask(String completeTaskName) {
        for (Task t : taskList) {
            if (t.getTaskName().equals(completeTaskName)) {
                t.isCompleted();
            }
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    // EFFECTS: writes current to-do list to a text file
    public void save(File fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Task t = taskList.get(taskList.size() - 1);
        printWriter.println(t.getTaskName() + ";"
                + t.getDueDate() + ";"
                + t.getCompleted());
        printWriter.close();
    }

    // EFFECTS: reads saved to-do list from text file
    public List<String> load(String filePath) throws IOException {
        return (Files.readAllLines(Paths.get(filePath)));
    }

    public void printLoad(String filePath) throws IOException {
        List<String> lines = this.load(filePath);
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            System.out.print("Name: " + partsOfLine.get(0) + " ");
            System.out.print("Due date: " + partsOfLine.get(1) + " ");
            System.out.println("Completed status: " + partsOfLine.get(2));
        }
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
