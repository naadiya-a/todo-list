package ui;

import model.ToDoList;

import java.io.File;
import java.io.IOException;
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

    public void performAction(int i) throws IOException {
        if (i == 1) {
            toDoList.addTask(inputNewTask(), inputDueDate(), inputUrgent());
            toDoList.save(new File("./data/todoListData.txt"));
        }
        if (i == 2) {
            inputCompleteTask();
            System.out.println("The task has been marked as complete!");
        }
        if (i == 3 || i == 4) {
            toDoList.printLoad("./data/todoListData.txt");
        }
        // !!! tasks not marking complete! - ALSO probably shouldn't be using taskName !
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

    private String inputUrgent() {
        System.out.println("Is this task urgent? Select [1] for yes, [2] for no");
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
