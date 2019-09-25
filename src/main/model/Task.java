package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Task {

    // !!! make private and add getters

    private String taskName;
    private Date dueDate;
    private boolean completed;
    private Scanner scanner;

    public Task(String name) {
        scanner = new Scanner(System.in);
        setName(name);
        inputDueDate();
        completed = false;
    }

    // MODIFIES: this
    // EFFECTS: assigns name to the task
    public void setName(String name) {
        this.taskName = name;
    }

    private void inputDueDate() {
        System.out.println("Type in the due date as: MM/DD/YYYY");
        String date = scanner.nextLine();
        setDueDate(date);
        System.out.println(this.taskName + " is due at " + this.dueDate);
    }

//    Reference: https://stackoverflow.com/questions/11665195/unhandled-exception-type-parseexception
    // MODIFIES: this
    // EFFECTS: assigns due date to the task
    public void setDueDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            this.dueDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: changes completed status to true
    //          and adds check mark to its name
    public void isCompleted() {
        this.setName("✓ " + this.taskName);
        this.completed = true;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public boolean getCompleted() {
        return this.completed;
    }
}
