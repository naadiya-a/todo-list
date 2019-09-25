package ui;

import model.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {

    private Scanner scanner;
    public ArrayList<Task> taskList;

    public ToDoList() {
        scanner = new Scanner(System.in);
        taskList = new ArrayList<>();
        inputAction();
    }

    //    Reference: https://stackoverflow.com/questions/31152539/how-to-make-a-functioning-to-do-list-in-java
    private void inputAction() {
        int action = 0;
        while (action < 4) {
            System.out.println("Would you like to:");
            System.out.println("[1] Add new task");
            System.out.println("[2] Mark a task as completed");
            System.out.println("[3] View your ToDo List");
            System.out.println("[4] Exit");
            action = scanner.nextInt();
            performAction(action);
        }
    }

    public void performAction(int i) {
        if (i == 1) {
            String newTaskName = inputNewTask();
            System.out.println("'" + newTaskName + "' has been added to the list!");
        }
        if (i == 2) {
            String completedTask = inputCompleteTask();
            System.out.println("'" + completedTask + "' has been marked as complete!");
        }
        if (i == 3 || i == 4) {
            System.out.println("ToDo List:");
//            System.out.println(Arrays.toString(taskList)); !!! remove
            for (Task t : taskList) {
                System.out.println(t.taskName);
            }
        }
    }

    // EFFECTS: gets name of new task to be created
    //          and returns the name
    public String inputNewTask() {
        System.out.println("Type in the task you want to add");
        scanner.nextLine();
        String name = scanner.nextLine();
        addTask(name);
        return name;
    }

    // MODIFIES: taskList
    // EFFECTS: adds new task to taskList
    public void addTask(String newTaskName) {
        Task newTask = new Task(newTaskName);
        taskList.add(newTask);
    }

    // EFFECTS: gets name of completed task
    //          and returns the name
    public String inputCompleteTask() {
        System.out.println("Enter the name of the completed task");
        scanner.nextLine();
        String name = scanner.nextLine();
        completeTask(name);
        return name;
    }

    private void completeTask(String completeTaskName) {
        for (Task t : taskList) {
            if (t.taskName.equals(completeTaskName)) {
                t.isCompleted();
            }
        }
    }
}
