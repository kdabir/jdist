package com.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HelloWorldApp extends JFrame {
    private JTextField nameField;
    private JLabel greetingLabel;
    private JPanel mainPanel;
    private boolean isDarkMode = false;
    
    public HelloWorldApp() {
        // Set up the frame
        setTitle("Hello World App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        
        // Set macOS look and feel and enable dark mode detection
        setupLookAndFeel();
        
        // Create the main UI
        createUI();
        
        // Apply initial theme
        applyTheme();
    }
    
    private void setupLookAndFeel() {
        try {
            // Set system look and feel for native appearance
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Enable dark mode detection on macOS
            System.setProperty("apple.awt.application.appearance", "auto");
            System.setProperty("apple.awt.application.name", "Hello World App");
            
            // Set macOS-specific properties
            if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("com.apple.macos.useScreenMenuBar", "true");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Hello World App");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createUI() {
        // Main panel with modern styling
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        
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
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0, 0, 30, 0));
        
        // Main title
        JLabel titleLabel = new JLabel("Hello World", JLabel.CENTER);
        titleLabel.setFont(new Font("SF Pro Display", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtitle
        JLabel subtitleLabel = new JLabel("A beautiful Java Swing application", JLabel.CENTER);
        subtitleLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 14));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        // Name prompt
        JLabel namePromptLabel = new JLabel("What's your name?");
        namePromptLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        namePromptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Name input field with modern styling
        nameField = new JTextField(20);
        nameField.setFont(new Font("SF Pro Text", Font.PLAIN, 16));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameField.setMaximumSize(new Dimension(300, 40));
        nameField.setBorder(new CompoundBorder(
            new LineBorder(Color.GRAY, 1, true),
            new EmptyBorder(8, 12, 8, 12)
        ));
        
        // Greet button with modern styling
        JButton greetButton = new JButton("Say Hello");
        greetButton.setFont(new Font("SF Pro Text", Font.BOLD, 16));
        greetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        greetButton.setMaximumSize(new Dimension(200, 45));
        greetButton.setPreferredSize(new Dimension(200, 45));
        greetButton.setFocusPainted(false);
        greetButton.setBorderPainted(false);
        greetButton.setOpaque(true);
        
        // Add action listeners
        greetButton.addActionListener(this::handleGreeting);
        nameField.addActionListener(e -> handleGreeting(e));
        
        // Add key listener for real-time validation
        nameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            
            @Override
            public void keyPressed(KeyEvent e) {}
            
            @Override
            public void keyReleased(KeyEvent e) {
                updateButtonState(greetButton);
            }
        });
        
        panel.add(namePromptLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(nameField);
        panel.add(Box.createVerticalStrut(20));
        panel.add(greetButton);
        
        return panel;
    }
    
    private JPanel createGreetingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        greetingLabel = new JLabel("", JLabel.CENTER);
        greetingLabel.setFont(new Font("SF Pro Text", Font.PLAIN, 18));
        greetingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        greetingLabel.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        panel.add(greetingLabel);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0, 20, 0, 0));
        
        // Theme toggle button
        JButton themeButton = new JButton("🌙");
        themeButton.setFont(new Font("SF Pro Text", Font.PLAIN, 20));
        themeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        themeButton.setMaximumSize(new Dimension(50, 50));
        themeButton.setPreferredSize(new Dimension(50, 50));
        themeButton.setFocusPainted(false);
        themeButton.setBorderPainted(false);
        themeButton.setOpaque(false);
        themeButton.setToolTipText("Toggle Dark Mode");
        
        themeButton.addActionListener(e -> {
            isDarkMode = !isDarkMode;
            themeButton.setText(isDarkMode ? "☀️" : "🌙");
            applyTheme();
        });
        
        panel.add(themeButton);
        
        return panel;
    }
    
    private void handleGreeting(ActionEvent e) {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            greetingLabel.setText("Please enter your name first! 👋");
            greetingLabel.setForeground(isDarkMode ? new Color(255, 150, 150) : new Color(220, 50, 50));
        } else {
            greetingLabel.setText("Hello, " + name + "! 👋 Welcome to Java Swing!");
            greetingLabel.setForeground(isDarkMode ? new Color(150, 255, 150) : new Color(50, 150, 50));
        }
    }
    
    private void updateButtonState(JButton button) {
        boolean hasText = !nameField.getText().trim().isEmpty();
        button.setEnabled(hasText);
        button.setOpaque(hasText);
    }
    
    private void applyTheme() {
        Color bgColor, fgColor, inputBg, inputFg, buttonBg, buttonFg;
        
        if (isDarkMode) {
            bgColor = new Color(30, 30, 30);
            fgColor = new Color(220, 220, 220);
            inputBg = new Color(50, 50, 50);
            inputFg = new Color(220, 220, 220);
            buttonBg = new Color(0, 122, 255);
            buttonFg = Color.WHITE;
        } else {
            bgColor = new Color(248, 248, 248);
            fgColor = new Color(30, 30, 30);
            inputBg = Color.WHITE;
            inputFg = new Color(30, 30, 30);
            buttonBg = new Color(0, 122, 255);
            buttonFg = Color.WHITE;
        }
        
        // Apply colors to components
        mainPanel.setBackground(bgColor);
        mainPanel.setForeground(fgColor);
        
        // Update all panels
        updatePanelColors(mainPanel, bgColor, fgColor);
        
        // Update input field
        nameField.setBackground(inputBg);
        nameField.setForeground(inputFg);
        nameField.setCaretColor(inputFg);
        
        // Update button
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof JPanel) {
                updateButtonColors((JPanel) comp, buttonBg, buttonFg);
            }
        }
        
        // Update greeting label
        if (greetingLabel.getText().isEmpty()) {
            greetingLabel.setForeground(fgColor);
        }
    }
    
    private void updatePanelColors(Container container, Color bg, Color fg) {
        container.setBackground(bg);
        container.setForeground(fg);
        
        for (Component comp : container.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(fg);
            } else if (comp instanceof JPanel) {
                updatePanelColors((JPanel) comp, bg, fg);
            }
        }
    }
    
    private void updateButtonColors(JPanel panel, Color bg, Color fg) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if (button.getText().equals("Say Hello")) {
                    button.setBackground(bg);
                    button.setForeground(fg);
                }
            } else if (comp instanceof JPanel) {
                updateButtonColors((JPanel) comp, bg, fg);
            }
        }
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

