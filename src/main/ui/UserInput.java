package ui;

import model.Task;
import model.ToDoList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class UserInput {

    private Scanner scanner;
    private ToDoList toDoList;

    public UserInput() throws IOException, ClassNotFoundException {
        scanner = new Scanner(System.in);
        toDoList = new ToDoList();
        inputAction();
    }

    //    Reference: https://stackoverflow.com/questions/31152539/how-to-make-a-functioning-to-do-list-in-java
    private void inputAction() throws IOException, ClassNotFoundException {
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

    public void performAction(int i) throws IOException, ClassNotFoundException {
        if (i == 1) {
            toDoList.addTask(inputNewTask(), inputDueDate());
        }
        if (i == 2) {
            inputCompleteTask();
            System.out.println("The task has been marked as complete!");
        }
        if (i == 3 || i == 4) {
            toDoList.save();
            toDoList.load();
        }
        //he said don't worry about hitting it more than once and making duplicates
        //suggested making a separate option for save and a separate option for load
        //and [my thought] maybe make the last option as just exit instead of showing list
        //[also me] maybe try the index thing for save so i save the most recent task every time a new one is added
        //          ^(and put save under option 1)
    }

    // EFFECTS: gets name of new task to be created
    //          and returns the name
    public String inputNewTask() {
        System.out.println("Type in the task you want to add");
        scanner.nextLine();
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
