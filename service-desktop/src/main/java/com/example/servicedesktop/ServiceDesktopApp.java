package com.example.servicedesktop;

import com.example.service.ServiceApp;
import java.awt.Desktop;
import java.net.URI;

/**
 * Desktop wrapper for the Hello World Service.
 * Adds browser opening capability to the pure service.
 * This module cannot be compiled to GraalVM native images due to AWT dependency.
 */
public class ServiceDesktopApp {
    
    public static void main(String[] args) {
        System.out.println("🚀 Starting Hello World Service Desktop...");
        
        // Start the service
        ServiceApp.main(args);
        
        // Open browser automatically
        openBrowser("http://localhost:8080");
        
        System.out.println("✅ Service Desktop running with browser integration");
        System.out.println("⚠️  Note: This module cannot be compiled to GraalVM native images due to AWT dependency");
    }
    
    /**
     * Opens the default web browser to the specified URL
     */
    private static void openBrowser(String url) {
        try {
            System.out.println("🌐 Opening browser automatically...");
            
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
                System.out.println("✅ Browser opened successfully!");
            } else {
                // Fallback to system commands
                openBrowserFallback(url);
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to open browser: " + e.getMessage());
            System.out.println("Please manually open: " + url);
        }
    }
    
    /**
     * Fallback method using system commands
     */
    private static void openBrowserFallback(String url) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb;
            
            if (os.contains("win")) {
                pb = new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url);
            } else if (os.contains("mac")) {
                pb = new ProcessBuilder("open", url);
            } else if (os.contains("nix") || os.contains("nux")) {
                pb = new ProcessBuilder("xdg-open", url);
            } else {
                System.out.println("Unsupported OS for automatic browser opening");
                return;
            }
            
            pb.start();
            System.out.println("✅ Browser opened successfully!");
        } catch (Exception e) {
            System.err.println("❌ Failed to open browser: " + e.getMessage());
            System.out.println("Please manually open: " + url);
        }
    }
}
