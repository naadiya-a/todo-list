package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class Task {

    protected String taskName;
    private Date dueDate;
    private boolean completed;

    public Task(String name, String dueDate) {
        setName(name);
        setDueDate(dueDate);
        completed = false;
    }

    // MODIFIES: this
    // EFFECTS: assigns name to the task
    public abstract void setName(String name);

    // Reference: https://stackoverflow.com/questions/11665195/unhandled-exception-type-parseexception
    // MODIFIES: this
    // EFFECTS: assigns due date to the task
    public void setDueDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            this.dueDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(this.taskName + " is due at " + this.dueDate);
    }


//    public void setDueDate(String date) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//            sdf.setLenient(false);
//            this.dueDate = sdf.parse(date);
//            System.out.println(this.taskName + " is due at " + this.dueDate);
//
//            // *if moving to ui then change^ to:
//            // return sdf.parse(date); <-- except use a variable for this
//
//            // *then this method would just be:
//            // this.dueDate = date;
//        } catch (ParseException e) {
//            System.out.println("Please enter a valid date");
//            Scanner sc = new Scanner(System.in);
//            setDueDate(sc.nextLine());
//        }
//    }


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
