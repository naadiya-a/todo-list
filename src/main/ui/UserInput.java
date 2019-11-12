package ui;

import model.Load;
import model.Save;
import model.ToDoList;
import model.TooManyThingsToDo;

import java.io.File;
import java.util.Scanner;

public class UserInput {

    private Scanner scanner;
    private ToDoList toDoList;
    private Load load = new Load();
//    private Save save;

    public UserInput() {
        scanner = new Scanner(System.in);
        toDoList = new ToDoList();
        inputAction();
    }

    //    Reference: https://stackoverflow.com/questions/31152539/how-to-make-a-functioning-to-do-list-in-java
    private void inputAction() {
        int action = 0;
//        toDoList.printLoad("./data/todoListData.txt");
        load.printLoad("./data/todoListData.txt");
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

    private void performAction(int i) {
        if (i == 1) {
            try {
                toDoList.addTask(inputNewTask(), inputDueDate(), inputUrgent());
            } catch (TooManyThingsToDo tooManyThingsToDo) {
                System.out.println("There are too many incomplete tasks in your list.");
                System.out.println("Please check off any completed tasks.");
            }
        }
        if (i == 2) {
            inputCompleteTask();
            System.out.println("The task has been marked as complete!");
        }
        if (i == 3) {
            inputTaskListToView();
//            toDoList.printCollection();
        }
        if (i == 4) {
            toDoList.save(new File("./data/todoListData.txt"));
        }
    }

    // EFFECTS: gets name of new task to be created
    //          and returns the name
    private String inputNewTask() {
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
    private void inputCompleteTask() {
        System.out.println("Enter the name of the completed task");
        scanner.nextLine();
        String name = scanner.nextLine();
        toDoList.completeTask(name);
    }

    private void inputTaskListToView() {
        System.out.println("[1] View all tasks");
        System.out.println("[2] View incomplete tasks only");
        scanner.nextLine();
        String list = scanner.nextLine();
        toDoList.printCollection(list);
    }
}
