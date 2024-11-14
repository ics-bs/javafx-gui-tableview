package se.lu.ics.controllers;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.lu.ics.models.AppModel;
import se.lu.ics.models.Project;

public class AppController {
    private final Stage primaryStage;
    private final AppModel appModel;

    public AppController(Stage primaryStage, AppModel appModel) {
        this.primaryStage = primaryStage;
        this.appModel = appModel;
    }

    public void showProjectsView() throws IOException {
        URL url = getClass().getResource("/fxml/ProjectsView.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());

        ProjectsViewController controller = loader.getController();
        controller.setAppController(this);
        controller.setAppModel(appModel);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Projects");
        primaryStage.show();
    }

    public void showProjectEmployeesView(Project project) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectEmployeesView.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));

        ProjectEmployeesViewController controller = loader.getController();

        controller.setProject(project);

        String projectName = project.getName();

        modalStage.setTitle("Employees for project " + projectName);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        Stage currentStage = (Stage) primaryStage.getScene().getWindow();
        modalStage.initOwner(currentStage);
        modalStage.showAndWait();

    }

    public void showProjectAddDialog() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectAddDialog.fxml"));
        Stage modalStage = new Stage();
        modalStage.setScene(new Scene(loader.load()));

        ProjectAddDialogController controller = loader.getController();
        controller.setAppModel(appModel);

        modalStage.setTitle("Add Project");
        modalStage.initModality(Modality.APPLICATION_MODAL);
        Stage currentStage = (Stage) primaryStage.getScene().getWindow();
        modalStage.initOwner(currentStage);
        modalStage.showAndWait();

    }
}
