package model;

import java.io.File;
import java.io.IOException;

public interface Saveable {
    void save(File fileName, String itemToSave) throws IOException;
}
