module com.example.service-desktop {
    requires com.example.service;
    requires com.example.shared;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires io.javalin;
    requires java.desktop;
    requires org.slf4j;

    exports com.example.servicedesktop;
}