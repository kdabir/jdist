package com.example.shared.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for running system tests.
 * Pure utility - no UI dependencies.
 */
public class SystemTestRunner {
    
    /**
     * Runs a quick system test
     */
    public static TestResult runQuickTest(String userName) {
        try {
            // Simulate quick test
            Thread.sleep(500);
            
            // Simple validation
            if (userName == null || userName.trim().isEmpty()) {
                return new TestResult(false, "User name is required", userName);
            }
            
            return new TestResult(true, "Quick test completed successfully", userName);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return new TestResult(false, "Test interrupted: " + e.getMessage(), userName);
        }
    }
    
    /**
     * Runs a comprehensive system test asynchronously
     */
    public static CompletableFuture<TestResult> runSystemTest(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulate comprehensive test
                Thread.sleep(2000);
                
                // Simple validation
                if (userName == null || userName.trim().isEmpty()) {
                    return new TestResult(false, "User name is required", userName);
                }
                
                // Simulate various system checks
                if (userName.length() < 2) {
                    return new TestResult(false, "User name too short", userName);
                }
                
                return new TestResult(true, "Comprehensive test completed successfully", userName);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new TestResult(false, "Test interrupted: " + e.getMessage(), userName);
            }
        });
    }
    
    /**
     * Test result data class
     */
    public static class TestResult {
        private final boolean success;
        private final String message;
        private final String userName;
        private final long timestamp;
        
        public TestResult(boolean success, String message, String userName) {
            this.success = success;
            this.message = message;
            this.userName = userName;
            this.timestamp = System.currentTimeMillis();
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getMessage() {
            return message;
        }
        
        public String getUserName() {
            return userName;
        }
        
        public long getTimestamp() {
            return timestamp;
        }
        
        @Override
        public String toString() {
            return String.format("TestResult{success=%s, message='%s', userName='%s', timestamp=%d}", 
                success, message, userName, timestamp);
        }
    }
}