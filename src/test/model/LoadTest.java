package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoadTest {

    private Load load;

    @BeforeEach
    void setup() {
        load = new Load();
    }

    @Test
    void testLoad() throws IOException {
        ArrayList<String> array = (ArrayList<String>) load.load("./data/testLoad.txt");
        assertEquals("Testing;Thu Oct 03 00:00:00 PDT 2019;false", array.get(0));
    }

    @Test
    void testSplitOnSpace() {
        ArrayList<String> actual = Load.splitOnSpace("hey;you");
        ArrayList<String> array = new ArrayList<>(Arrays.asList("hey", "you"));
        assertEquals(array, actual);
    }
}
