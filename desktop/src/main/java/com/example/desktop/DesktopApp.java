package com.example.desktop;

import com.example.shared.ui.ModernUIComponents;
import com.example.shared.ui.UIThemeManager;
import com.example.shared.test.SystemTestRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DesktopApp extends JFrame {
    private JTextField nameField;
    private JLabel greetingLabel;
    private JPanel mainPanel;
    private JButton greetButton;
    private JButton themeButton;
    private UIThemeManager themeManager;
    
    public DesktopApp() {
        // Initialize theme manager
        themeManager = UIThemeManager.getInstance();
        
        // Create modern frame for System Test
        setTitle("Java Swing System Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
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
        JLabel titleLabel = ModernUIComponents.createTitleLabel("System Test");
        
        // Subtitle
        JLabel subtitleLabel = ModernUIComponents.createSubtitleLabel("Verify your Java Swing installation is working correctly");
        
        panel.add(titleLabel);
        panel.add(ModernUIComponents.createVerticalSpacer(8));
        panel.add(subtitleLabel);
        
        return panel;
    }
    
    private JPanel createInputPanel() {
        JPanel panel = ModernUIComponents.createPaddedPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS), 20, 0, 20, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Test instructions
        JLabel instructionLabel = ModernUIComponents.createBodyLabel("Enter your name to test the system:");
        
        // Name input field with modern styling
        nameField = ModernUIComponents.createModernTextField(20);
        
        // Submit button for system test
        greetButton = ModernUIComponents.createModernButton("Run Test", this::handleTest);
        
        // Add action listeners using method reference
        nameField.addActionListener(this::handleTest);
        
        // Add key listener for real-time validation using lambda
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
        
        panel.add(instructionLabel);
        panel.add(ModernUIComponents.createVerticalSpacer(15));
        panel.add(nameField);
        panel.add(ModernUIComponents.createVerticalSpacer(20));
        panel.add(greetButton);
        
        return panel;
    }
    
    private JPanel createGreetingPanel() {
        JPanel panel = ModernUIComponents.createPaddedPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS), 20, 0, 0, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Test result label
        greetingLabel = ModernUIComponents.createStatusLabel("", ModernUIComponents.StatusType.INFO);
        
        panel.add(greetingLabel);
        
        return panel;
    }
    
    private JPanel createControlPanel() {
        JPanel panel = ModernUIComponents.createPaddedPanel(new BoxLayout(new JPanel(), BoxLayout.Y_AXIS), 0, 20, 0, 0);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Theme toggle button
        themeButton = ModernUIComponents.createThemeToggleButton();
        
        // Add theme change listener using lambda
        themeButton.addActionListener(e -> ModernUIComponents.updateTheme(getContentPane()));
        
        panel.add(themeButton);
        
        return panel;
    }
    
    private void handleTest(ActionEvent e) {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            greetingLabel.setText("❌ Please enter your name to run the test!");
            greetingLabel.setForeground(themeManager.getErrorColor());
        } else {
            // Use shared system test runner
            SystemTestRunner.runSystemTest(name)
                .thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        if (result.isSuccess()) {
                            greetingLabel.setText("""
                                ✅ TEST SUCCESSFUL!
                                Hello %s, your installation is working correctly!
                                System test completed successfully.
                                """.formatted(name));
                            greetingLabel.setForeground(themeManager.getSuccessColor());
                        } else {
                            greetingLabel.setText("""
                                ❌ TEST FAILED!
                                %s
                                Please check your setup and try again.
                                """.formatted(result.getMessage()));
                            greetingLabel.setForeground(themeManager.getErrorColor());
                        }
                    });
                })
                .exceptionally(throwable -> {
                    SwingUtilities.invokeLater(() -> {
                        greetingLabel.setText("""
                            ❌ TEST ERROR!
                            %s
                            Please check your setup and try again.
                            """.formatted(throwable.getMessage()));
                        greetingLabel.setForeground(themeManager.getErrorColor());
                    });
                    return null;
                });
        }
    }
    
    private void updateButtonState() {
        boolean hasText = !nameField.getText().trim().isEmpty();
        greetButton.setEnabled(hasText);
        greetButton.setOpaque(hasText);
    }
    
    
    public static void main(String[] args) {
        // Ensure GUI is created on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new DesktopApp().setVisible(true));
    }
}

