package model;

import java.io.IOException;
import java.util.List;

public interface Loadable {
    List<String> load(String filePath) throws IOException;
}
