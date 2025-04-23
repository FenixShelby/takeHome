package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    JLabel firstNameLabel, lastNameLabel, rawScoreLabel, totalScoreLabel;
    JTextField firstNameField, lastNameField, rawScoreField, totalScoreField;
    JButton saveButton, deleteButton;
    Container container;
    JTable table;
    PersonDataTable personDataTable;
    JPanel panel;
    public GUI() {
        firstNameLabel = new JLabel("First Name:");
        lastNameLabel = new JLabel("Last Name");
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");
        rawScoreField = new JTextField(10);
        totalScoreField = new JTextField(10);
        rawScoreLabel = new JLabel("RS:");
        totalScoreLabel = new JLabel("TS:");
        panel = new JPanel();

        personDataTable = new PersonDataTable();
        table = new JTable(personDataTable);
        panel.add(new JScrollPane(table));

        container = this.getContentPane();
        container.setLayout(new FlowLayout());
        container.add(firstNameLabel);
        container.add(firstNameField);
        container.add(lastNameLabel);
        container.add(lastNameField);
        container.add(rawScoreLabel);
        container.add(rawScoreField);
        container.add(totalScoreLabel);
        container.add(totalScoreField);
        container.add(saveButton);
        container.add(deleteButton);
        container.add(panel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
