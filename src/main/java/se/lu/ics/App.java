package se.lu.ics;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import se.lu.ics.controllers.ProjectsViewController;
import se.lu.ics.models.ProjectRegister;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ProjectRegister projectRegister = new ProjectRegister();

        URL url = getClass().getResource("/fxml/ProjectsView.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());
        ProjectsViewController controller = loader.getController();
        controller.setProjectRegister(projectRegister);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Consultants");
        primaryStage.show();
    }
}