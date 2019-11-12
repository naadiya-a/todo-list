package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveTest {

    private Save save;

    @BeforeEach
    void setup() {
        save = new Save();
    }

    @Test
    void testSave() throws IOException {
        File file = new File("./data/testSave.txt");
        // clear the file
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        save.save(file, "Test!;Thu Oct 03 00:00:00 PDT 2019;false");
        ArrayList<String> array = (ArrayList<String>) Files.readAllLines(Paths.get("./data/testSave.txt"));
        assertEquals("Test!;Thu Oct 03 00:00:00 PDT 2019;false", array.get(0));
    }
}
