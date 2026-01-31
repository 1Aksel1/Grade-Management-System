package com.example.admindesktopapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.io.IOException;

@SpringBootApplication
public class AdminApplication extends Application {


    private ConfigurableApplicationContext applicationContext;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        this.applicationContext = SpringApplication.run(AdminApplication.class);
    }

    @Override
    public void stop() throws Exception {
        applicationContext.stop();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminApplication.class.getResource("login-view.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Scene scene = new Scene(fxmlLoader.load(), 1260, 1020);
        stage.setTitle("Admin Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}