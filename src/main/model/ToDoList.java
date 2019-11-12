package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ToDoList {

    static final int MAX_INCOMPLETE = 30;

    private HashMap<String, Task> taskMap;
    private Save save = new Save();

    public ToDoList() {
        taskMap = new HashMap<>();
    }

    // MODIFIES: taskList
    // EFFECTS: adds new task to taskList
    public void addTask(String newTaskName, String newDueDate, String urgent) throws TooManyThingsToDo {
        Task newTask;
        if (taskMap.size() < MAX_INCOMPLETE) {
            if (urgent.equals("2")) {
                newTask = new RegularTask(newTaskName, newDueDate);
            } else {
                newTask = new UrgentTask(newTaskName, newDueDate);
            }
            addToMap(newTask);
        } else {
            throw new TooManyThingsToDo();
        }
    }

    // MODIFIES: this
    // EFFECTS: if task is not already in the list,
    //          adds the task to the list and sets itself as the task's list
    public void addToMap(Task task) {
        if (!taskMap.containsKey(task.getTaskName())) {
            taskMap.put(task.getTaskName(), task);
            task.addList(this);
        }
    }

    // MODIFIES: this
    // EFFECTS: if task is in the list,
    //          removes the task from the list and removes itself as the task's list
    public void removeFromMap(String taskName) {
        if (taskMap.containsKey(taskName)) {
            Task t = taskMap.get(taskName);
            taskMap.remove(taskName);
            t.removeList();
        }
    }

    // EFFECTS: finds completed task from taskList
    public void completeTask(String completeTaskName) {
        Task t = taskMap.get(completeTaskName);
        t.isCompleted();
    }

    public void save(File filename) {
        for (Task t : taskMap.values()) {
            String taskString = (t.getTaskName() + ";"
                    + t.getDueDate() + ";"
                    + t.getCompleted());
            save.save(filename, taskString);
        }
    }

    // EFFECTS: prints the name, due date, and completed status of each task in the list
    public void printCollection() {
        try {
            ArrayList<Task> taskList = convertToArray();
            for (Task t : taskList) {
                System.out.print("Name: " + t.getTaskName() + " ");
                System.out.print("Due date: " + t.getDueDate() + " ");
                System.out.println("Completed status: " + t.getCompleted());
            }
        } catch (NullPointerException e) {
            System.out.println("Your list is empty.");
        }
    }

    // Reference: https://stackoverflow.com/questions/1968068/java-how-to-convert-type-collection-into-arraylist
    // EFFECTS: converts taskMap values collection into ArrayList
    public ArrayList<Task> convertToArray() {
        if (!taskMap.isEmpty()) {
            Collection<Task> tasks = taskMap.values();
            return new ArrayList<>(tasks);
        }
        throw new NullPointerException();
    }

    public HashMap<String, Task> getTaskMap() {
        return this.taskMap;
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
