package se.lu.ics;

import javafx.application.Application;
import javafx.stage.Stage;

import se.lu.ics.controllers.AppController;
import se.lu.ics.models.AppModel;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppModel appModel = new AppModel();
        AppController appController = new AppController(primaryStage, appModel);
        appController.showProjectsView();
    }
}