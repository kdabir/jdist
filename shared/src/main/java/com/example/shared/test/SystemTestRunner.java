package com.example.shared.test;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;

/**
 * Shared system test runner that can be used by all modules
 */
public class SystemTestRunner {
    
    public static class TestResult {
        private final boolean success;
        private final String message;
        private final LocalTime timestamp;
        
        public TestResult(boolean success, String message) {
            this.success = success;
            this.message = message;
            this.timestamp = LocalTime.now();
        }
        
        public boolean isSuccess() { return success; }
        public String getMessage() { return message; }
        public LocalTime getTimestamp() { return timestamp; }
        
        @Override
        public String toString() {
            return String.format("[%s] %s: %s", 
                timestamp, 
                success ? "SUCCESS" : "FAILED", 
                message);
        }
    }
    
    /**
     * Run comprehensive system tests
     */
    public static CompletableFuture<TestResult> runSystemTest(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Test 1: Input validation
                if (userName == null || userName.trim().length() < 2) {
                    return new TestResult(false, "Invalid user name provided");
                }
                
                // Test 2: System properties
                String osName = System.getProperty("os.name");
                String javaVersion = System.getProperty("java.version");
                
                if (osName == null || javaVersion == null) {
                    return new TestResult(false, "Unable to read system properties");
                }
                
                // Test 3: Memory availability
                Runtime runtime = Runtime.getRuntime();
                long maxMemory = runtime.maxMemory();
                if (maxMemory < 64 * 1024 * 1024) { // 64MB minimum
                    return new TestResult(false, "Insufficient memory available");
                }
                
                // Test 4: Threading capability
                try {
                    Thread.sleep(100); // Simulate processing
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new TestResult(false, "Threading test interrupted");
                }
                
                // Test 5: File system access
                String tempDir = System.getProperty("java.io.tmpdir");
                if (tempDir == null || tempDir.isEmpty()) {
                    return new TestResult(false, "Unable to access temporary directory");
                }
                
                return new TestResult(true, 
                    String.format("System test passed for user '%s' on %s with Java %s", 
                        userName, osName, javaVersion));
                        
            } catch (Exception e) {
                return new TestResult(false, "System test failed: " + e.getMessage());
            }
        });
    }
    
    /**
     * Run quick validation test
     */
    public static TestResult runQuickTest(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return new TestResult(false, "Please enter your name to run the test!");
        }
        
        if (userName.trim().length() < 2) {
            return new TestResult(false, "Name must be at least 2 characters long");
        }
        
        return new TestResult(true, 
            String.format("Hello %s! Your system is working correctly.", userName));
    }
}
