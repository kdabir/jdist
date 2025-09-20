package com.example.shared.util;

import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for working with Java modules
 */
public class ModuleUtils {
    
    /**
     * Get all available modules in the current module path
     */
    public static Set<String> getAvailableModules() {
        return ModuleFinder.ofSystem()
            .findAll()
            .stream()
            .map(ModuleReference::descriptor)
            .map(moduleDescriptor -> moduleDescriptor.name())
            .collect(Collectors.toSet());
    }
    
    /**
     * Check if a specific module is available
     */
    public static boolean isModuleAvailable(String moduleName) {
        return getAvailableModules().contains(moduleName);
    }
    
    /**
     * Get module dependencies for a given module
     */
    public static Set<String> getModuleDependencies(String moduleName) {
        return ModuleFinder.ofSystem()
            .find(moduleName)
            .map(ModuleReference::descriptor)
            .map(moduleDescriptor -> moduleDescriptor.requires())
            .orElse(Set.of())
            .stream()
            .map(requires -> requires.name())
            .collect(Collectors.toSet());
    }
    
    /**
     * Generate module-info.java content based on dependencies
     */
    public static String generateModuleInfo(String moduleName, Set<String> exports, Set<String> requires) {
        StringBuilder sb = new StringBuilder();
        sb.append("module ").append(moduleName).append(" {\n");
        
        // Add requires
        requires.stream()
            .sorted()
            .forEach(req -> sb.append("    requires ").append(req).append(";\n"));
        
        // Add exports
        exports.stream()
            .sorted()
            .forEach(exp -> sb.append("    exports ").append(exp).append(";\n"));
        
        sb.append("}");
        return sb.toString();
    }
}
