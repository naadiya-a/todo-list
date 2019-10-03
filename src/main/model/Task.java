package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private String taskName;
    private Date dueDate;
    private boolean completed;

    public Task(String name, String dueDate) {
        setName(name);
        setDueDate(dueDate);
        completed = false;
    }

    // MODIFIES: this
    // EFFECTS: assigns name to the task
    public void setName(String name) {
        this.taskName = name;
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
        System.out.println(this.taskName + " is due at " + this.dueDate);
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
