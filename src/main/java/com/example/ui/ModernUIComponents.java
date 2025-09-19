package com.example.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Factory class for creating modern, themed UI components.
 * Provides consistent styling and behavior across the application.
 */
public class ModernUIComponents {
    private static final UIThemeManager themeManager = UIThemeManager.getInstance();
    
    /**
     * Creates a modern title label with proper typography
     */
    public static JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(themeManager.getTitleFont());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    /**
     * Creates a modern subtitle label
     */
    public static JLabel createSubtitleLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(themeManager.getSubtitleFont());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    /**
     * Creates a modern body text label
     */
    public static JLabel createBodyLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(themeManager.getBodyFont());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }
    
    /**
     * Creates a modern text input field with proper styling
     */
    public static JTextField createModernTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(themeManager.getBodyFont());
        textField.setAlignmentX(Component.CENTER_ALIGNMENT);
        textField.setMaximumSize(new Dimension(300, 40));
        textField.setBorder(themeManager.createInputBorder());
        
        // Apply theme
        themeManager.applyThemeToTextField(textField);
        
        return textField;
    }
    
    /**
     * Creates a modern button with proper styling and behavior
     */
    public static JButton createModernButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(themeManager.getButtonFont());
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 45));
        button.setPreferredSize(new Dimension(200, 45));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        
        // Modern Java: Optional handling
        java.util.Optional.ofNullable(actionListener)
                .ifPresent(button::addActionListener);
        
        // Apply theme
        themeManager.applyThemeToButton(button);
        
        return button;
    }
    
    /**
     * Creates a theme toggle button (moon/sun emoji)
     */
    public static JButton createThemeToggleButton() {
        JButton button = new JButton("🌙");
        button.setFont(new Font("SF Pro Text", Font.PLAIN, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(50, 50));
        button.setPreferredSize(new Dimension(50, 50));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setToolTipText("Toggle Dark Mode");
        
        // Modern Java: Lambda expression with ternary operator
        button.addActionListener(e -> {
            themeManager.toggleTheme();
            button.setText(themeManager.isDarkMode() ? "☀️" : "🌙");
        });
        
        return button;
    }
    
    /**
     * Creates a modern panel with proper layout and styling
     */
    public static JPanel createModernPanel(LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        themeManager.applyThemeToPanel(panel);
        return panel;
    }
    
    /**
     * Creates a vertical panel with BoxLayout
     */
    public static JPanel createVerticalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        themeManager.applyThemeToPanel(panel);
        return panel;
    }
    
    /**
     * Creates a horizontal panel with BoxLayout
     */
    public static JPanel createHorizontalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        themeManager.applyThemeToPanel(panel);
        return panel;
    }
    
    /**
     * Creates a panel with proper padding
     */
    public static JPanel createPaddedPanel(LayoutManager layout, int top, int left, int bottom, int right) {
        JPanel panel = new JPanel(layout);
        panel.setBorder(themeManager.createPaddingBorder(top, left, bottom, right));
        themeManager.applyThemeToPanel(panel);
        return panel;
    }
    
    /**
     * Creates a vertical spacer
     */
    public static Component createVerticalSpacer(int height) {
        return Box.createVerticalStrut(height);
    }
    
    /**
     * Creates a horizontal spacer
     */
    public static Component createHorizontalSpacer(int width) {
        return Box.createHorizontalStrut(width);
    }
    
    /**
     * Creates a flexible vertical spacer
     */
    public static Component createVerticalGlue() {
        return Box.createVerticalGlue();
    }
    
    /**
     * Creates a flexible horizontal spacer
     */
    public static Component createHorizontalGlue() {
        return Box.createHorizontalGlue();
    }
    
    /**
     * Creates a modern frame with proper settings
     */
    public static JFrame createModernFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        // Apply theme to frame
        frame.getContentPane().setBackground(themeManager.getBackgroundColor());
        
        return frame;
    }
    
    /**
     * Creates a status label for displaying messages with appropriate colors
     */
    public static JLabel createStatusLabel(String text, StatusType statusType) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(themeManager.getBodyFont());
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(themeManager.createPaddingBorder(15, 20, 15, 20));
        
        // Set color based on status type
        switch (statusType) {
            case SUCCESS:
                label.setForeground(themeManager.getSuccessColor());
                break;
            case ERROR:
                label.setForeground(themeManager.getErrorColor());
                break;
            case WARNING:
                label.setForeground(themeManager.getWarningColor());
                break;
            case INFO:
            default:
                label.setForeground(themeManager.getForegroundColor());
                break;
        }
        
        return label;
    }
    
    /**
     * Updates the theme for all components in a container
     */
    public static void updateTheme(Container container) {
        themeManager.applyThemeToPanel((JPanel) container);
    }
    
    
    /**
     * Modern Java: Creates multiple buttons using Stream API
     */
    public static java.util.List<JButton> createModernButtons(java.util.Map<String, ActionListener> buttonConfigs) {
        return buttonConfigs.entrySet().stream()
                .map(entry -> createModernButton(entry.getKey(), entry.getValue()))
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Modern Java: Creates a status message with string interpolation
     */
    public static String createStatusMessage(String user, boolean success, String testType) {
        return success 
            ? """
                ✅ %s Test Successful!
                User: %s
                Status: All systems operational
                Time: %s
                """.formatted(testType, user, java.time.LocalTime.now())
            : """
                ❌ %s Test Failed!
                User: %s
                Status: System error detected
                Time: %s
                """.formatted(testType, user, java.time.LocalTime.now());
    }
    
    /**
     * Enum for status label types
     */
    public enum StatusType {
        SUCCESS, ERROR, WARNING, INFO
    }
}
