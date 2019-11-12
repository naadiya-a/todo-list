package ui;

import network.ReadWebPage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ReadWebPage.main(args);
        new UserInput();
    }
}
