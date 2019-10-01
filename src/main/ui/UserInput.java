package ui;

import model.Task;
import model.ToDoList;

import java.util.Scanner;

public class UserInput {

    private Scanner scanner;
    private ToDoList toDoList;

    public UserInput() {
        scanner = new Scanner(System.in);
        toDoList = new ToDoList();
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
//            inputNewTask();
            toDoList.addTask(inputNewTask(), inputDueDate());
        }
        if (i == 2) {
            inputCompleteTask();
            System.out.println("The task has been marked as complete!");
        }
        if (i == 3 || i == 4) {
            System.out.println("ToDo List:");
            for (Task t : toDoList.getTaskList()) {
                System.out.println(t.getTaskName());
            }
        }
    }

    // EFFECTS: gets name of new task to be created
    //          and returns the name
    public String inputNewTask() {
        System.out.println("Type in the task you want to add");
        scanner.nextLine();
//        String name = scanner.nextLine();
//        toDoList.addTask(name);
        return scanner.nextLine();
    }

    private String inputDueDate() {
        System.out.println("Type in the due date as: MM/DD/YYYY");
        return scanner.nextLine();
    }

    // EFFECTS: gets name of completed task
    //          and returns the name
    public void inputCompleteTask() {
        System.out.println("Enter the name of the completed task");
        scanner.nextLine();
        String name = scanner.nextLine();
        toDoList.completeTask(name);
    }
}
