package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI extends JFrame implements ActionListener {

    JPanel mainPanel = new JPanel();
    JLabel menuLabel = new JLabel("Would you like to:");

    // Reference: https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
    public ToDoListGUI() {
        super("ToDo List");

        setSize(new Dimension(400, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton b1 = new JButton("Add a new task");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        JButton b2 = new JButton("Mark a task as completed");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        JButton b3 = new JButton("View your ToDo List");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //
            }
        });

        mainPanel.add(menuLabel);
        mainPanel.add(b1);
        mainPanel.add(b2);
        mainPanel.add(b3);
        add(mainPanel);
//        getContentPane().add(mainPanel);

        setVisible(true);


        // Reference: https://docs.oracle.com/javase/tutorial/uiswing/components/toplevel.html
//        contentPanel = new JPanel(new BorderLayout());
//        contentPanel.setBorder(someBorder);
//        contentPanel.add(someComponent, BorderLayout.CENTER);
//        contentPanel.add(anotherComponent, BorderLayout.PAGE_END);
//
//        frame.setContentPane(contentPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
