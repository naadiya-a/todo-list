package model;

import model.Saveable;

import java.util.ArrayList;

public class ToDoList {

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
}
