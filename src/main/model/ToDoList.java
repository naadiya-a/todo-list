package model;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ToDoList implements Observer {

    static final int MAX_INCOMPLETE = 30;

    private HashMap<String, Task> allTaskMap;
    private HashMap<String, Task> incompleteTaskMap;
    private Save save = new Save();
    private ImageIcon icon = new ImageIcon("./data/yes.gif");

    public ToDoList() {
        allTaskMap = new HashMap<>();
        incompleteTaskMap = new HashMap<>();
    }

    // MODIFIES: allTaskMap, incompleteTaskMap
    // EFFECTS: adds new task to the task maps
    public void addTask(String newTaskName, String newDueDate, String urgent) throws TooManyThingsToDo {
        Task newTask;
        if (allTaskMap.size() < MAX_INCOMPLETE) {
            if (urgent.equals("2")) {
                newTask = new RegularTask(newTaskName, newDueDate);
            } else {
                newTask = new UrgentTask(newTaskName, newDueDate);
            }
            addToMap(newTask);
            newTask.addObserver(this);
        } else {
            throw new TooManyThingsToDo();
        }
    }

    // MODIFIES: this
    // EFFECTS: if task is not already in the map,
    //          adds the task to the map of all tasks and sets itself as the task's ToDoList
    void addToMap(Task task) {
        if (!allTaskMap.containsKey(task.getTaskName())) {
            allTaskMap.put(task.getTaskName(), task);
            task.addList(this);

            addToIncompleteMap(task);
        }
    }

    // MODIFIES: this
    // EFFECTS: if task is not completed,
    //          adds the task to the incomplete task map
    void addToIncompleteMap(Task task) {
        if (!task.getCompleted()) {
            incompleteTaskMap.put(task.getTaskName(), task);
        }
    }

    // MODIFIES: this
    // EFFECTS: if task is in the list,
    //          removes the task from the map of all tasks and removes itself as the task's ToDoList
    void removeFromMap(String taskName) {
        if (allTaskMap.containsKey(taskName)) {
            Task t = allTaskMap.get(taskName);
            allTaskMap.remove(taskName);
            t.removeList();

            incompleteTaskMap.remove(taskName);
        }
    }

    // Reference: https://stackoverflow.com/questions/30986683/put-a-gif-in-joptionpane/30987192
    // MODIFIES: the specified task
    // EFFECTS: finds completed task from taskList
    public void completeTask(String completeTaskName) {
        if (allTaskMap.containsKey(completeTaskName)) {
            Task t = allTaskMap.get(completeTaskName);
            t.isCompleted();
            JOptionPane.showMessageDialog(null, "'" + completeTaskName + "' is completed!",
                    null, JOptionPane.INFORMATION_MESSAGE, icon);
        } else {
            JOptionPane.showMessageDialog(null, "The task was not found");
        }
    }

    // EFFECTS: decomposes a task's components into one string, for every task in the map for it to be saved
    public void save(File filename) {
        for (Task t : allTaskMap.values()) {
            String taskString = (t.getTaskName() + ";"
                    + t.getDueDate() + ";"
                    + t.getCompleted());
            save.save(filename, taskString);
        }
    }

    // EFFECTS: prints the name, due date, and completed status of each task in the list
    public void printCollection(String list) {
        try {
            for (Task t : convertToArray(list)) {
//                System.out.print("Name: " + t.getTaskName() + " ");
//                System.out.print("Due date: " + t.getDueDate() + " ");
//                System.out.println("Completed status: " + t.getCompleted());
                JOptionPane.showMessageDialog(null, "Name: " + t.getTaskName()
                        + "\nDue date: " + t.getDueDate() + "\nCompleted status: " + t.getCompleted());
            }
        } catch (NullPointerException e) {
//            System.out.println("The list is empty.");
            JOptionPane.showMessageDialog(null, "The list is empty.");
        }
    }

    // Reference: https://stackoverflow.com/questions/1968068/java-how-to-convert-type-collection-into-arraylist
    // EFFECTS: converts taskMap values collection into ArrayList
    ArrayList<Task> convertToArray(String list) {
        if (list.equals("1")) {
            if (!allTaskMap.isEmpty()) {
                Collection<Task> tasks = allTaskMap.values();
                return new ArrayList<>(tasks);
            }
        } else {
            if (!incompleteTaskMap.isEmpty()) {
                Collection<Task> tasks = incompleteTaskMap.values();
                return new ArrayList<>(tasks);
            }
        }
        throw new NullPointerException();
    }

    // MODIFIES: this
    // EFFECTS: removes the task from the map of incomplete tasks
    @Override
    public void update(String taskName) {
        incompleteTaskMap.remove(taskName);
    }

    public HashMap<String, Task> getAllTaskMap() {
        return this.allTaskMap;
    }

    public HashMap<String, Task> getIncompleteTaskMap() {
        return this.incompleteTaskMap;
    }


    //    // EFFECTS: reads saved to-do list from text file
//    public List<String> load(String filePath) throws IOException {
//        return (Files.readAllLines(Paths.get(filePath)));
//    }
//
//    // EFFECTS: separates strings at semicolon
//    public static ArrayList<String> splitOnSpace(String line) {
//        String[] splits = line.split(";");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//    public void printLoad(String filePath) {
//        try {
//            for (String line : this.load(filePath)) {
//                ArrayList<String> partsOfLine = splitOnSpace(line);
//                System.out.print("Name: " + partsOfLine.get(0) + " ");
//                System.out.print("Due date: " + partsOfLine.get(1) + " ");
//                System.out.println("Completed status: " + partsOfLine.get(2));
//
////                loadInTaskMap(partsOfLine);
//            }
//        } catch (IOException e) {
//            System.out.println("Cannot load exception");
//        }
//    }
}
