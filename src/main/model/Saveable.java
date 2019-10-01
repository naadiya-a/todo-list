package model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public interface Saveable {
    void save() throws FileNotFoundException, UnsupportedEncodingException;
}
