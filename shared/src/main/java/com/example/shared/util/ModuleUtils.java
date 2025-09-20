package com.example.shared.util;

import java.lang.module.ModuleDescriptor;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for module-related operations.
 * Pure utility - no UI dependencies.
 */
public class ModuleUtils {
    
    /**
     * Gets module dependencies for a given module
     */
    public static Set<String> getModuleDependencies(Module module) {
        if (module == null) {
            return Set.of();
        }
        
        return module.getDescriptor().requires().stream()
                .map(ModuleDescriptor.Requires::name)
                .collect(Collectors.toSet());
    }
    
    /**
     * Checks if a module is available
     */
    public static boolean isModuleAvailable(String moduleName) {
        try {
            ModuleLayer.boot().findModule(moduleName).isPresent();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Gets system information
     */
    public static String getSystemInfo() {
        return String.format("""
            System Information:
            - OS: %s %s
            - Java Version: %s
            - Java Vendor: %s
            - Available Processors: %d
            - Max Memory: %d MB
            """,
            System.getProperty("os.name"),
            System.getProperty("os.version"),
            System.getProperty("java.version"),
            System.getProperty("java.vendor"),
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().maxMemory() / (1024 * 1024)
        );
    }
}