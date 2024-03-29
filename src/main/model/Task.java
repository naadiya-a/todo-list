package model;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Task extends Subject {

    private String taskName;
    private Date dueDate;
    private boolean completed;
    private ToDoList toDoList;

    public Task(String name, String dueDate) {
        setName(name);
        setDueDate(dueDate);
        completed = false;
    }

    // MODIFIES: this
    // EFFECTS: assigns name to the task
    void setName(String name) {
        this.taskName = name;
    }

    // Reference: https://stackoverflow.com/questions/11665195/unhandled-exception-type-parseexception
    // MODIFIES: this
    // EFFECTS: assigns due date to the task
    void setDueDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            sdf.setLenient(false);
            this.dueDate = sdf.parse(date);
//            JOptionPane.showMessageDialog(null, this.taskName + " is due at " + this.dueDate);
        } catch (ParseException e) {
//            System.out.println("The due date was not set because an invalid date was entered");
            JOptionPane.showMessageDialog(null,
                    "The due date was not set because an invalid date was entered");
        }
//        System.out.println(this.taskName + " is due at " + this.dueDate);
    }

    // MODIFIES: this
    // EFFECTS: changes completed status to true
    //          and notifies its observers of the change
    void isCompleted() {
        this.completed = true;
        notifyObservers(this.getTaskName());
    }

    // MODIFIES: this
    // EFFECTS: sets the toDoList as this task's list and adds itself to the toDoList
    void addList(ToDoList toDoList) {
        this.toDoList = toDoList;
        toDoList.addToMap(this);
    }

    // MODIFIES: this
    // EFFECTS: sets this task's list to null and removes itself from the toDoList
    void removeList() {
        if (toDoList != null) {
            toDoList.removeFromMap(this.taskName);
        }
        this.toDoList = null;
    }

    // EFFECTS: compares the two given Task objects and
    //              if all fields are matched it returns true,
    //              if any field is not matched it returns false
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return completed == task.completed
                && Objects.equals(taskName, task.taskName)
                && Objects.equals(dueDate, task.dueDate);
    }

    // EFFECTS: returns the integer hash code value of the Task object
    @Override
    public int hashCode() {
        return Objects.hash(taskName, dueDate, completed);
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

    public ToDoList getToDoList() {
        return this.toDoList;
    }
}
