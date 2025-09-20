module com.example.shared {
    requires java.desktop;
    requires java.logging;
    
    exports com.example.shared.ui;
    exports com.example.shared.test;
    exports com.example.shared.util;
}