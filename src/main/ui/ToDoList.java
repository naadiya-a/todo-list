package ui;

import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {

    private Scanner scanner;
    private ArrayList<String> tasks;

    public static void main(String[] args) {
        new ToDoList();
    }

    public ToDoList() {
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
        chooseAction();
    }

//    Reference: https://stackoverflow.com/questions/31152539/how-to-make-a-functioning-to-do-list-in-java
    private void chooseAction() {
        int action = 0;
        while (action < 4) {
            System.out.println("Would you like to:");
            System.out.println("[1] Add new task");
            System.out.println("[2] Mark a completed task");
            System.out.println("[3] View your ToDo List");
            System.out.println("[4] Exit");
            action = scanner.nextInt();
            performAction(action);
        }
    }

    private void performAction(int i) {
        if (i == 1) {
            String newTask = addTask();
            System.out.println("'" + newTask + "' has been added to the list!");
        }
        if (i == 2) {
            String completeTask = completeTask();
            System.out.println("'" + completeTask + "' has been marked as complete!");
        }
        if (i == 3 || i == 4) {
            System.out.println("ToDo List:");
            System.out.println(tasks);
        }
    }

    private String addTask() {
        System.out.println("Type in the task you want to add");
        scanner.nextLine();
        String t = scanner.nextLine();
        tasks.add(t);
        return t;
    }

    private String completeTask() {
        System.out.println(tasks);
        System.out.println("Enter the number of the completed task");
        int i = scanner.nextInt();
        String complete = tasks.get(i - 1);
        tasks.set(i - 1, "✓ " + complete);
        return complete;
    }
}
