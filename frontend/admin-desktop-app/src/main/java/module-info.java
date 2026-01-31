module com.example.admindesktopapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.webflux;
    requires spring.boot.starter.webflux;
    requires spring.web;
    requires reactor.core;
    requires com.google.gson;
    requires jjwt;


    opens com.example.admindesktopapp to javafx.fxml, spring.core, spring.beans;
    exports com.example.admindesktopapp;

    opens com.example.admindesktopapp.configuration to spring.beans, javafx.fxml;
    exports com.example.admindesktopapp.configuration;

    opens com.example.admindesktopapp.services to spring.beans, javafx.fxml;
    exports com.example.admindesktopapp.services;

    opens com.example.admindesktopapp.dto to spring.beans, spring.web, reactor.core, javafx.fxml, com.google.gson;
    exports com.example.admindesktopapp.dto;

    opens com.example.admindesktopapp.util to spring.beans, spring.web, spring.core, reactor.core, javafx.fxml, com.google.gson;
    exports com.example.admindesktopapp.util;

    opens com.example.admindesktopapp.loaders to spring.beans, spring.web, spring.core, reactor.core, javafx.fxml, com.google.gson;
    exports com.example.admindesktopapp.loaders;

    opens com.example.admindesktopapp.controllers to javafx.fxml, spring.core, spring.beans;
    exports com.example.admindesktopapp.controllers;

    opens com.example.admindesktopapp.clients to spring.beans, javafx.fxml;
    exports com.example.admindesktopapp.clients;

}