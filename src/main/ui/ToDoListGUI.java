package ui;

import model.ToDoList;
import model.TooManyThingsToDo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Set;

public class ToDoListGUI extends JFrame implements ActionListener {

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private ToDoList toDoList = new ToDoList();

    // Reference: https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
    public ToDoListGUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException,
            IllegalAccessException {
        super("ToDo List");

        JPanel mainPanel = new JPanel();
        setSize(new Dimension(450, 250));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                toDoList.save(new File("./data/todoListData.txt"));
                System.exit(0);
            }
        });
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JLabel menuLabel = new JLabel("What would you like to-do?");
        JLabel punLabel = new JLabel("(Yes, pun intended)");
        b1 = new JButton("Add a new task");
        b2 = new JButton("Mark a task as completed");
        b3 = new JButton("View your ToDo List");

        menuLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        punLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuLabel.setFont(new Font(menuLabel.getFont().getName(), Font.PLAIN, 18));
        menuLabel.setBorder(new EmptyBorder(18, 0, 6, 0));
        punLabel.setFont(new Font(menuLabel.getFont().getName(), Font.PLAIN, 14));
        punLabel.setBorder(new EmptyBorder(0, 0, 12, 0));
        b1.setMaximumSize(new Dimension(225, 30));
        b2.setMaximumSize(new Dimension(225, 30));
        b3.setMaximumSize(new Dimension(225, 30));

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        mainPanel.add(menuLabel);
        mainPanel.add(punLabel);
        mainPanel.add(b1);
        mainPanel.add(b2);
        mainPanel.add(b3);
        add(mainPanel);
        setVisible(true);
    }

    // EFFECTS: identifies the action event from the button click
    //          and calls the appropriate method to present the new option pane
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            actionOne();
        }
        if (e.getSource() == b2) {
            actionTwo();
        }
        if (e.getSource() == b3) {
            actionThree();
        }
    }

    // Reference: https://stackoverflow.com/questions/30265720/java-joptionpane-radio-buttons
    private void actionOne() {
        try {
            toDoList.addTask(JOptionPane.showInputDialog("Enter the name of the task"),
                    JOptionPane.showInputDialog("Enter the due date in the format: MM/DD/YYYY"),
                    inputUrgent());
        } catch (TooManyThingsToDo e) {
            JOptionPane.showMessageDialog(null,
                    "There are too many incomplete tasks in your list.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,
                    "The task was not created. Please ensure all the values are filled in.");
        }
    }

    private String inputUrgent() {
        String urgent;
        Object selected = JOptionPane.showInputDialog(null, "Is the task urgent?",
                null, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "No"}, "No");
        if (selected == null) {
            urgent = null;
        } else if (selected.toString().equals("Yes")) {
            urgent = "1";
        } else {
            urgent = "2";
        }
        return urgent;
    }

    private void actionTwo() {
        Set<String> keys = toDoList.getIncompleteTaskMap().keySet();
        String[] array = keys.toArray(new String[0]);
        Object task = JOptionPane.showInputDialog(null, "Select the completed task",
                null, JOptionPane.QUESTION_MESSAGE, null, array, null);
        toDoList.completeTask((String) task);
    }

    // Reference: https://stackoverflow.com/questions/30265720/java-joptionpane-radio-buttons
    private void actionThree() {
        try {
            String list;
            String[] values = {"View all tasks", "View incomplete tasks only"};
            Object selected = JOptionPane.showInputDialog(null,
                    "Which tasks would you like to view?", null, JOptionPane.QUESTION_MESSAGE,
                    null, values, "View all tasks");
            if (selected.toString().equals("View all tasks")) {
                list = "1";
            } else {
                list = "2";
            }
            toDoList.printCollection(list);
        } catch (NullPointerException ignored) {
            // do nothing
        }
    }
}
