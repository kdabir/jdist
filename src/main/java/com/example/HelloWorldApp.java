package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorldApp extends JFrame {
    private JTextField nameField;
    private JLabel greetingLabel;
    
    public HelloWorldApp() {
        // Set up the frame
        setTitle("Hello World App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window
        
        // Set macOS look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create components
        JLabel titleLabel = new JLabel("Hello World!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        JLabel namePromptLabel = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        
        JButton greetButton = new JButton("Greet Me!");
        greetingLabel = new JLabel("", JLabel.CENTER);
        greetingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Set up layout
        setLayout(new BorderLayout());
        
        // Create panels for better organization
        JPanel topPanel = new JPanel();
        topPanel.add(titleLabel);
        
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.add(namePromptLabel);
        centerPanel.add(nameField);
        centerPanel.add(greetButton);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(greetingLabel);
        
        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Add action listener to button
        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    greetingLabel.setText("Please enter your name!");
                    greetingLabel.setForeground(Color.RED);
                } else {
                    greetingLabel.setText("Hello, " + name + "! Welcome to Java Swing!");
                    greetingLabel.setForeground(Color.BLUE);
                }
            }
        });
        
        // Add action listener to text field (Enter key)
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greetButton.doClick(); // Trigger the button click
            }
        });
    }
    
    public static void main(String[] args) {
        // Ensure GUI is created on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HelloWorldApp().setVisible(true);
            }
        });
    }
}

