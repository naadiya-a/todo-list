package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class Load implements Loadable {

    @Override
    // EFFECTS: reads saved to-do list from text file
    public List<String> load(String filePath) throws IOException {
        return (Files.readAllLines(Paths.get(filePath)));
    }

    // EFFECTS: separates strings at semicolon
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(";");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void printLoad(String filePath) {
        try {
            for (String line : this.load(filePath)) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                System.out.print("Name: " + partsOfLine.get(0) + " ");
                System.out.print("Due date: " + partsOfLine.get(1) + " ");
                System.out.println("Completed status: " + partsOfLine.get(2));
            }
        } catch (IOException e) {
            System.out.println("Cannot load exception");
        }
    }
}
