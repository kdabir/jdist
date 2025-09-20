package com.example.cli;

import com.example.shared.test.SystemTestRunner;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.CompletableFuture;

@Command(
    name = "system-test-cli",
    mixinStandardHelpOptions = true,
    version = "1.0.0",
    description = "System Test CLI - Command line interface for system testing"
)
public class CliApp implements Runnable {
    
    @Parameters(
        index = "0",
        description = "Your name for the system test",
        defaultValue = "User"
    )
    private String userName;
    
    @Option(
        names = {"-q", "--quick"},
        description = "Run quick test instead of comprehensive test"
    )
    private boolean quickTest = false;
    
    @Option(
        names = {"-a", "--async"},
        description = "Run test asynchronously"
    )
    private boolean async = false;
    
    @Option(
        names = {"-v", "--verbose"},
        description = "Enable verbose output"
    )
    private boolean verbose = false;
    
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CliApp()).execute(args);
        System.exit(exitCode);
    }
    
    @Override
    public void run() {
        System.out.println("🚀 System Test CLI v1.0.0");
        System.out.println("==========================");
        
        if (verbose) {
            System.out.println("📋 Configuration:");
            System.out.println("  User: " + userName);
            System.out.println("  Quick Test: " + quickTest);
            System.out.println("  Async: " + async);
            System.out.println("  Verbose: " + verbose);
            System.out.println();
        }
        
        if (quickTest) {
            runQuickTest();
        } else {
            runComprehensiveTest();
        }
    }
    
    private void runQuickTest() {
        System.out.println("⚡ Running quick system test...");
        
        var result = SystemTestRunner.runQuickTest(userName);
        
        if (result.isSuccess()) {
            System.out.println("✅ " + result.getMessage());
            System.out.println("🎉 Quick test completed successfully!");
        } else {
            System.out.println("❌ " + result.getMessage());
            System.out.println("💥 Quick test failed!");
        }
    }
    
    private void runComprehensiveTest() {
        System.out.println("🔍 Running comprehensive system test...");
        
        if (async) {
            runAsyncTest();
        } else {
            runSyncTest();
        }
    }
    
    private void runSyncTest() {
        try {
            var result = SystemTestRunner.runSystemTest(userName).get();
            
            if (result.isSuccess()) {
                System.out.println("✅ " + result.getMessage());
                System.out.println("🎉 Comprehensive test completed successfully!");
            } else {
                System.out.println("❌ " + result.getMessage());
                System.out.println("💥 Comprehensive test failed!");
            }
        } catch (Exception e) {
            System.out.println("💥 Test execution failed: " + e.getMessage());
        }
    }
    
    private void runAsyncTest() {
        System.out.println("⏳ Running test asynchronously...");
        
        SystemTestRunner.runSystemTest(userName)
            .thenAccept(result -> {
                if (result.isSuccess()) {
                    System.out.println("✅ " + result.getMessage());
                    System.out.println("🎉 Comprehensive test completed successfully!");
                } else {
                    System.out.println("❌ " + result.getMessage());
                    System.out.println("💥 Comprehensive test failed!");
                }
            })
            .exceptionally(throwable -> {
                System.out.println("💥 Test execution failed: " + throwable.getMessage());
                return null;
            });
    }
}
