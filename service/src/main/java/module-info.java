module com.example.service {
    requires com.example.shared;
    requires io.javalin;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires java.logging;
    
    exports com.example.service;
}