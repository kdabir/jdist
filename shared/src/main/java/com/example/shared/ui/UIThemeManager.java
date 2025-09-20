package com.example.shared.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Manages UI themes and provides consistent styling across the application.
 * Supports both light and dark modes with macOS system integration.
 */
public class UIThemeManager {
    private static UIThemeManager instance;
    private boolean isDarkMode = false;
    
    // Color schemes
    private Color lightBg, lightFg, lightInputBg, lightInputFg, lightButtonBg, lightButtonFg;
    private Color darkBg, darkFg, darkInputBg, darkInputFg, darkButtonBg, darkButtonFg;
    
    // Fonts
    private Font titleFont, subtitleFont, bodyFont, buttonFont;
    
    private UIThemeManager() {
        initializeColors();
        initializeFonts();
        setupSystemLookAndFeel();
    }
    
    public static UIThemeManager getInstance() {
        if (instance == null) {
            instance = new UIThemeManager();
        }
        return instance;
    }
    
    private void initializeColors() {
        // Light theme colors
        lightBg = new Color(248, 248, 248);
        lightFg = new Color(30, 30, 30);
        lightInputBg = Color.WHITE;
        lightInputFg = new Color(30, 30, 30);
        lightButtonBg = new Color(0, 122, 255);
        lightButtonFg = Color.WHITE;
        
        // Dark theme colors
        darkBg = new Color(30, 30, 30);
        darkFg = new Color(220, 220, 220);
        darkInputBg = new Color(50, 50, 50);
        darkInputFg = new Color(220, 220, 220);
        darkButtonBg = new Color(0, 122, 255);
        darkButtonFg = Color.WHITE;
    }
    
    private void initializeFonts() {
        // Use SF Pro fonts for macOS, fallback to system fonts
        String fontFamily = isMacOS() ? "SF Pro Display" : "Arial";
        String textFontFamily = isMacOS() ? "SF Pro Text" : "Arial";
        
        titleFont = new Font(fontFamily, Font.BOLD, 32);
        subtitleFont = new Font(textFontFamily, Font.PLAIN, 14);
        bodyFont = new Font(textFontFamily, Font.PLAIN, 16);
        buttonFont = new Font(textFontFamily, Font.BOLD, 16);
    }
    
    private void setupSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Enable dark mode detection on macOS
            System.setProperty("apple.awt.application.appearance", "auto");
            
            // Set macOS-specific properties
            if (isMacOS()) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("com.apple.macos.useScreenMenuBar", "true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean isMacOS() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }
    
    // Modern Java features: Stream API, Optional, and Switch Expressions
    public java.util.Optional<Color> getColorByName(String colorName) {
        return java.util.Optional.ofNullable(switch (colorName.toLowerCase()) {
            case "background" -> getBackgroundColor();
            case "foreground" -> getForegroundColor();
            case "input-bg" -> getInputBackgroundColor();
            case "input-fg" -> getInputForegroundColor();
            case "button-bg" -> getButtonBackgroundColor();
            case "button-fg" -> getButtonForegroundColor();
            case "success" -> getSuccessColor();
            case "error" -> getErrorColor();
            case "warning" -> getWarningColor();
            default -> null;
        });
    }
    
    // Modern Java: String interpolation for debug information
    public String getThemeInfo() {
        return """
            Theme Information:
            - Mode: %s
            - Background: %s
            - Foreground: %s
            - Button: %s
            """.formatted(
                isDarkMode ? "Dark" : "Light",
                getBackgroundColor(),
                getForegroundColor(),
                getButtonBackgroundColor()
            );
    }
    
    public void setDarkMode(boolean darkMode) {
        this.isDarkMode = darkMode;
    }
    
    public boolean isDarkMode() {
        return isDarkMode;
    }
    
    public void toggleTheme() {
        setDarkMode(!isDarkMode);
    }
    
    // Color getters
    public Color getBackgroundColor() {
        return isDarkMode ? darkBg : lightBg;
    }
    
    public Color getForegroundColor() {
        return isDarkMode ? darkFg : lightFg;
    }
    
    public Color getInputBackgroundColor() {
        return isDarkMode ? darkInputBg : lightInputBg;
    }
    
    public Color getInputForegroundColor() {
        return isDarkMode ? darkInputFg : lightInputFg;
    }
    
    public Color getButtonBackgroundColor() {
        return isDarkMode ? darkButtonBg : lightButtonBg;
    }
    
    public Color getButtonForegroundColor() {
        return isDarkMode ? darkButtonFg : lightButtonFg;
    }
    
    // Font getters
    public Font getTitleFont() {
        return titleFont;
    }
    
    public Font getSubtitleFont() {
        return subtitleFont;
    }
    
    public Font getBodyFont() {
        return bodyFont;
    }
    
    public Font getButtonFont() {
        return buttonFont;
    }
    
    // Utility methods for applying themes
    public void applyThemeToComponent(Component component) {
        if (component instanceof JPanel) {
            applyThemeToPanel((JPanel) component);
        } else if (component instanceof JLabel) {
            applyThemeToLabel((JLabel) component);
        } else if (component instanceof JTextField) {
            applyThemeToTextField((JTextField) component);
        } else if (component instanceof JButton) {
            applyThemeToButton((JButton) component);
        }
    }
    
    public void applyThemeToPanel(JPanel panel) {
        panel.setBackground(getBackgroundColor());
        panel.setForeground(getForegroundColor());
        
        // Modern Java: Stream API for processing components
        java.util.Arrays.stream(panel.getComponents())
                .forEach(this::applyThemeToComponent);
    }
    
    public void applyThemeToLabel(JLabel label) {
        label.setForeground(getForegroundColor());
    }
    
    public void applyThemeToTextField(JTextField textField) {
        textField.setBackground(getInputBackgroundColor());
        textField.setForeground(getInputForegroundColor());
        textField.setCaretColor(getInputForegroundColor());
    }
    
    public void applyThemeToButton(JButton button) {
        // Only apply theme to main action buttons, not theme toggle buttons
        if (!button.getText().matches("[🌙☀️]")) {
            button.setBackground(getButtonBackgroundColor());
            button.setForeground(getButtonForegroundColor());
        }
    }
    
    // Special color methods for different states
    public Color getSuccessColor() {
        return isDarkMode ? new Color(150, 255, 150) : new Color(50, 150, 50);
    }
    
    public Color getErrorColor() {
        return isDarkMode ? new Color(255, 150, 150) : new Color(220, 50, 50);
    }
    
    public Color getWarningColor() {
        return isDarkMode ? new Color(255, 200, 100) : new Color(255, 140, 0);
    }
    
    // Border creation utilities
    public javax.swing.border.Border createInputBorder() {
        return new javax.swing.border.CompoundBorder(
            new javax.swing.border.LineBorder(Color.GRAY, 1, true),
            new javax.swing.border.EmptyBorder(8, 12, 8, 12)
        );
    }
    
    public javax.swing.border.Border createPaddingBorder(int top, int left, int bottom, int right) {
        return new javax.swing.border.EmptyBorder(top, left, bottom, right);
    }
}
