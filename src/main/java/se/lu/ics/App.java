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
        /*
         * This instance of ProjectRegister will be passed
         * to the ProjectsViewController, and will be the
         * instance that is used throughout the application.
         */
        ProjectRegister projectRegister = new ProjectRegister();

        URL url = getClass().getResource("/fxml/ProjectsView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load());

        /*
         * After fxmlLoader.load() has been called, the controller
         * can be retrieved from the FXMLLoader instance.
         */
        ProjectsViewController controller = fxmlLoader.getController();

        /*
         * The ProjectRegister instance is passed to the controller.
         */
        controller.setProjectRegister(projectRegister);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Projects");
        primaryStage.show();
    }
}