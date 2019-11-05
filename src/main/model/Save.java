package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Save implements Saveable {

    @Override
    // EFFECTS: writes current to-do list to a text file
    public void save(File fileName, String itemToSave) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
//            for (Task t : taskMap.values()) {
//                printWriter.println(t.getTaskName() + ";"
//                                + t.getDueDate() + ";"
//                                + t.getCompleted()
////                        + ";" + t.getClass()
//                );
//            }
            printWriter.println(itemToSave);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot save exception");
        }
    }
}
