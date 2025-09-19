package com.example;

import com.example.ui.ModernUIComponents;
import com.example.ui.UIThemeManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HelloWorldApp extends JFrame {
    private JTextField nameField;
    private JLabel greetingLabel;
    private JPanel mainPanel;
    private JButton greetButton;
    private JButton themeButton;
    private UIThemeManager themeManager;
    
    public HelloWorldApp() {
        // Initialize theme manager
        themeManager = UIThemeManager.getInstance();
        
        // Create modern frame
        setTitle("Hello World App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Create the main UI
        createUI();
        
        // Apply initial theme
        ModernUIComponents.updateTheme(getContentPane());
    }
    
    private void createUI() {
        // Main panel with modern styling
        mainPanel = ModernUIComponents.createPaddedPanel(new BorderLayout(), 30, 30, 30, 30);
        
        // Create title section
        JPanel titlePanel = createTitlePanel();
        
        // Create input section
        JPanel inputPanel = createInputPanel();
        
        // Create greeting section
        JPanel greetingPanel = createGreetingPanel();
        
        // Create control panel with theme toggle
        JPanel controlPanel = createControlPanel();
        
        // Add all panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(greetingPanel, BorderLayout.SOUTH);
        mainPanel.add(controlPanel, BorderLayout.EAST);
        
        add(mainPanel);
    }
    
    private JPanel createTitlePanel() {
        JPanel panel = ModernUIComponents.createPaddedPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS), 0, 0, 30, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Main title
        JLabel titleLabel = ModernUIComponents.createTitleLabel("Hello World");
        
        // Subtitle
        JLabel subtitleLabel = ModernUIComponents.createSubtitleLabel("A beautiful Java Swing application");
        
        panel.add(titleLabel);
        panel.add(ModernUIComponents.createVerticalSpacer(8));
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    private JPanel createInputPanel() {
        JPanel panel = ModernUIComponents.createPaddedPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS), 20, 0, 20, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Name prompt
        JLabel namePromptLabel = ModernUIComponents.createBodyLabel("What's your name?");
        
        // Name input field with modern styling
        nameField = ModernUIComponents.createModernTextField(20);
        
        // Greet button with modern styling
        greetButton = ModernUIComponents.createModernButton("Say Hello", this::handleGreeting);
        
        // Add action listeners
        nameField.addActionListener(e -> handleGreeting(e));
        
        // Add key listener for real-time validation
        nameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            
            @Override
            public void keyPressed(KeyEvent e) {}
            
            @Override
            public void keyReleased(KeyEvent e) {
                updateButtonState();
            }
        });
        
        panel.add(namePromptLabel);
        panel.add(ModernUIComponents.createVerticalSpacer(15));
        panel.add(nameField);
        panel.add(ModernUIComponents.createVerticalSpacer(20));
        panel.add(greetButton);
        
        return panel;
    }
    
    private JPanel createGreetingPanel() {
        JPanel panel = ModernUIComponents.createPaddedPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS), 20, 0, 0, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        greetingLabel = ModernUIComponents.createStatusLabel("", ModernUIComponents.StatusType.INFO);
        
        panel.add(greetingLabel);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = ModernUIComponents.createPaddedPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS), 0, 20, 0, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Theme toggle button
        themeButton = ModernUIComponents.createThemeToggleButton();
        
        // Add theme change listener
        themeButton.addActionListener(e -> {
            ModernUIComponents.updateTheme(getContentPane());
        });
        
        panel.add(themeButton);
        
        return panel;
    }
    
    private void handleGreeting(ActionEvent e) {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            greetingLabel.setText("Please enter your name first! 👋");
            greetingLabel.setForeground(themeManager.getErrorColor());
        } else {
            greetingLabel.setText("Hello, " + name + "! 👋 Welcome to Java Swing!");
            greetingLabel.setForeground(themeManager.getSuccessColor());
        }
    }
    
    private void updateButtonState() {
        boolean hasText = !nameField.getText().trim().isEmpty();
        greetButton.setEnabled(hasText);
        greetButton.setOpaque(hasText);
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

