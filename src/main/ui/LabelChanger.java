package ui;

// Reference: https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application

import model.ToDoList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LabelChanger extends JFrame implements ActionListener {
    private JLabel label;
    private JTextField field;
    private ToDoList toDoList;

    public LabelChanger() {
        super("ToDo List");
        toDoList = new ToDoList();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
//        setLayout(new FlowLayout());
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton btn1 = new JButton("Add a new task");
        btn1.setActionCommand("addNew");
        btn1.addActionListener(this); //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere

        JButton btn2 = new JButton("Mark a task as completed");
        btn2.setActionCommand("completed");
        btn2.addActionListener(this);

        JButton btn3 = new JButton("View your ToDo List");
        btn3.setActionCommand("view");
        btn3.addActionListener(this);

        label = new JLabel("flag");
        field = new JTextField(10);
        field.setMaximumSize(field.getPreferredSize());
        add(btn1);
//        add(label);
        add(btn2);
        add(btn3);
        add(field);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addNew")) {
            label.setText(field.getText());
        }
    }
}
