package ui;

// Reference: https://www.codejava.net/java-se/swing/redirect-standard-output-streams-to-jtextarea

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}