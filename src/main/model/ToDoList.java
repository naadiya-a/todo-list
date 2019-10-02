package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public void save() throws IOException {
        PrintWriter writer = new PrintWriter("./data/todoListData.txt","UTF-8");
        for (Task task : taskList) {
//            System.out.print("Name: " + task.getTaskName() + " ");
//            System.out.println("Due Date: " + task.getDueDate() + " ");
//            System.out.println("Completed status: " + task.getCompleted());
            writer.println(task);
        }
        writer.close();
    }

    // EFFECTS: reads saved to-do list from text file
    public void load() throws IOException, ClassNotFoundException {
        List<String> lines = Files.readAllLines(Paths.get("./data/todoListData.txt"));
        System.out.println(lines);
    }
}
