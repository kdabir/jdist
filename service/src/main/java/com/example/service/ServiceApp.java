package com.example.service;

import com.example.shared.test.SystemTestRunner;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.json.JavalinJackson;
import io.javalin.http.staticfiles.Location;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class ServiceApp {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static Javalin app;
    
    public static void main(String[] args) {
        System.out.println("🚀 Starting System Test Service...");
        
        app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson(objectMapper));
            config.showJavalinBanner = false;
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/web";
                staticFiles.directory = "web";
                staticFiles.location = Location.CLASSPATH;
            });
        })
        .start(8080);
        
        setupRoutes();
        
        System.out.println("✅ Hello World Service running on http://localhost:8080");
        
        System.out.println("📋 Available endpoints:");
        System.out.println("  GET  / - Hello World UI");
        System.out.println("  GET  /health - Health check");
        System.out.println("  POST /test - Run system test");
        System.out.println("  GET  /test/quick?name=<name> - Run quick test");
        System.out.println("  GET  /status - Service status");
    }
    
    private static void setupRoutes() {
        // Health check endpoint
        app.get("/health", ctx -> {
            ctx.json(new HealthResponse("OK", "Service is running"));
        });
        
        // Service status
        app.get("/status", ctx -> {
            Runtime runtime = Runtime.getRuntime();
            long maxMemory = runtime.maxMemory();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            
            ctx.json(new StatusResponse(
                System.getProperty("os.name"),
                System.getProperty("java.version"),
                maxMemory,
                totalMemory,
                usedMemory,
                freeMemory
            ));
        });
        
        // Quick test endpoint
        app.get("/test/quick", ctx -> {
            String name = ctx.queryParam("name");
            if (name == null || name.trim().isEmpty()) {
                ctx.status(400).json(new ErrorResponse("Name parameter is required"));
                return;
            }
            
            var result = SystemTestRunner.runQuickTest(name);
            ctx.json(new TestResponse(
                result.isSuccess(),
                result.getMessage(),
                String.valueOf(result.getTimestamp())
            ));
        });
        
        // Comprehensive test endpoint
        app.post("/test", ctx -> {
            TestRequest request = ctx.bodyAsClass(TestRequest.class);
            
            if (request.name == null || request.name.trim().isEmpty()) {
                ctx.status(400).json(new ErrorResponse("Name is required"));
                return;
            }
            
            try {
                // Run test synchronously for now to avoid async issues
                var result = SystemTestRunner.runSystemTest(request.name).get();
                ctx.json(new TestResponse(
                    result.isSuccess(),
                    result.getMessage(),
                    String.valueOf(result.getTimestamp())
                ));
            } catch (Exception e) {
                ctx.status(500).json(new ErrorResponse("Test execution failed: " + e.getMessage()));
            }
        });
        
        // Hello World endpoint
        app.get("/hello", ctx -> {
            ctx.redirect("/web/hello.html");
        });
        
        // Default route - serve hello world page
        app.get("/", ctx -> {
            ctx.redirect("/web/hello.html");
        });
    }
    
    // Response classes
    public static class HealthResponse {
        public String status;
        public String message;
        
        public HealthResponse(String status, String message) {
            this.status = status;
            this.message = message;
        }
    }
    
    public static class StatusResponse {
        public String osName;
        public String javaVersion;
        public long maxMemory;
        public long totalMemory;
        public long usedMemory;
        public long freeMemory;
        
        public StatusResponse(String osName, String javaVersion, long maxMemory, long totalMemory, long usedMemory, long freeMemory) {
            this.osName = osName;
            this.javaVersion = javaVersion;
            this.maxMemory = maxMemory;
            this.totalMemory = totalMemory;
            this.usedMemory = usedMemory;
            this.freeMemory = freeMemory;
        }
    }
    
    public static class TestRequest {
        public String name;
        
        public TestRequest() {}
        
        public TestRequest(String name) {
            this.name = name;
        }
    }
    
    public static class TestResponse {
        public boolean success;
        public String message;
        public String timestamp;
        
        public TestResponse(boolean success, String message, String timestamp) {
            this.success = success;
            this.message = message;
            this.timestamp = timestamp;
        }
    }
    
    public static class ErrorResponse {
        public String error;
        
        public ErrorResponse(String error) {
            this.error = error;
        }
    }
    
}
