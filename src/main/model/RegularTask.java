package model;

public class RegularTask extends Task {
    public RegularTask(String name, String dueDate) {
        super(name, dueDate);
    }

    @Override
    public void setName(String name) {
        this.taskName = name;
    }
}
