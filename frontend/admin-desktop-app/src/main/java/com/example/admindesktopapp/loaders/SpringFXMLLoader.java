package com.example.admindesktopapp.loaders;

import com.example.admindesktopapp.AdminApplication;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpringFXMLLoader {

    private ApplicationContext context;

    public SpringFXMLLoader(ApplicationContext context) {
        this.context = context;
    }

    public FXMLLoader load(String fxmlPath) throws IOException {

        FXMLLoader loader = new FXMLLoader(AdminApplication.class.getResource(fxmlPath));
        loader.setControllerFactory(context::getBean);
        return loader;

    }


}
