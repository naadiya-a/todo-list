package model;

public class UrgentTask extends Task {

    private Boolean urgent;

    public UrgentTask(String name, String dueDate) {
        super(name, dueDate);
    }

    @Override
    public void setName(String name) {
        this.taskName = name;
    }
}
