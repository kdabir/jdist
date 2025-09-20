module com.example.cli {
    requires com.example.shared;
    requires info.picocli;
    requires org.slf4j;
    requires java.logging;
    
    exports com.example.cli;
}