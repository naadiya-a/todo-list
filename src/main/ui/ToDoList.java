package ui;

import java.util.Scanner;
import model.Tasks;

public class ToDoList {

    private Scanner scanner;

    public static void main(String[] args) {
        new ToDoList();
    }

    public ToDoList() {
        scanner = new Scanner(System.in);
        chooseAction();
    }

    // EFFECTS: gives user options and allows them to select one
    private void chooseAction() {
        System.out.println("Would you like to:");
        System.out.println("[1] Add new task");
        System.out.println("[2] Mark a completed task");
        System.out.println("[3] Edit settings for a task");
        System.out.println("[4] Delete a task");
        int action = scanner.nextInt();
        performAction(action);
    }

    // EFFECTS: begins executing action based on user input
    private void performAction(int i) {
        if (i == 1) {
            Tasks.addTask();
            System.out.println("Let's add a new task!");
        }
        if (i == 2) {
            Tasks.completeTask();
            System.out.println("Congrats! We can cross one off.");
        }
        if (i == 3) {
            Tasks.editTask();
            System.out.println("Let's edit the reminder settings.");
        }
        if (i == 4) {
            Tasks.deleteTask();
            System.out.println("One less thing to worry about!");
        }
    }
}
