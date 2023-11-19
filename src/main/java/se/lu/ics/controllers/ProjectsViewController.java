package se.lu.ics.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import se.lu.ics.models.ProjectRegister;
import se.lu.ics.models.Employee;
import se.lu.ics.models.Project;

public class ProjectsViewController {

    private ProjectRegister projectRegister;

    @FXML
    private TableView<Project> tableViewProject;

    @FXML
    private TableColumn<Project, String> tableColumnProjectId;

    @FXML
    private TableColumn<Project, String> tableColumnProjectName;

    @FXML
    private TableColumn<Project, LocalDate> tableColumnProjectStartDate;

    @FXML
    private TableColumn<Project, LocalDate> tableColumnProjectEndDate;

    @FXML
    private TableColumn<Project, String> tableColumnProjectDuration;

    @FXML
    private TableColumn<Project, Double> tableColumnProjectBudget;

    @FXML
    private ComboBox<Project> comboBoxProjectIncreaseBudget;

    @FXML
    private Label labelResponse;

    public void initialize() {
        tableColumnProjectId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableColumnProjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnProjectName.setOnEditCommit(event -> {
            Project project = event.getRowValue();
            project.setName(event.getNewValue());
        });

        tableColumnProjectStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        tableColumnProjectEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        tableColumnProjectDuration.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            LocalDate startDate = project.getStartDate();
            LocalDate endDate = project.getEndDate();

            int years = endDate.getYear() - startDate.getYear();
            int months = endDate.getMonthValue() - startDate.getMonthValue();
            int days = endDate.getDayOfMonth() - startDate.getDayOfMonth();

            if (days < 0) {
                months--;
                days += startDate.lengthOfMonth();
            }

            if (months < 0) {
                years--;
                months += 12;
            }

            String duration = years + " years, " + months + " months, " + days + " days";
            return new ReadOnlyObjectWrapper<>(duration);
        });

        tableColumnProjectBudget.setCellValueFactory(new PropertyValueFactory<>("budget"));
        tableColumnProjectBudget.setOnEditCommit(event -> {
            Project project = event.getRowValue();
            project.setBudget(event.getNewValue());
        });

        /*
         * This is a custom cell factory for the ComboBox. It is used to display the
         * project id and name in the ComboBox instead of the default toString() method
         * when the ComboBox is expanded.
         */
        comboBoxProjectIncreaseBudget.setCellFactory(param -> new ListCell<Project>() {
            @Override
            protected void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);

                if (empty || project == null || project.getName() == null) {
                    setText(null);
                } else {
                    setText(project.getId() + " - " + project.getName());
                }
            }
        });

        /*
         * This is a custom cell factory for the ComboBox. It is used to display the
         * project id and name in the ComboBox instead of the default toString() method
         * when the ComboBox is collapsed.
         */
        comboBoxProjectIncreaseBudget.setButtonCell(new ListCell<Project>() {
            @Override
            protected void updateItem(Project project, boolean empty) {
                super.updateItem(project, empty);

                if (empty || project == null || project.getName() == null) {
                    setText(null);
                } else {
                    setText(project.getId() + " - " + project.getName());
                }
            }
        });

    }

    @FXML
    public void handleButtonProjectAddAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectAddDialog.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            ProjectAddDialogController controller = loader.getController();
            controller.setProjectRegister(projectRegister);

            stage.setTitle("Add Project");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            /* 
             * We cannot really explain
             * IOException to the user, and
             * they cannot correct issues
             * related to missing fxml files
             * so we just show a generic error
             */
            String errorMessage = "Internal system error, please contact support.";
            labelResponse.setText(errorMessage);
            labelResponse.setVisible(true);
        }
    }

    @FXML
    public void handleButtonProjectDeleteAction(ActionEvent event) {
        Project selectedProject = tableViewProject.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            projectRegister.removeProject(selectedProject);
            String message = "Project deleted successfully.";
            labelResponse.setText(message);
            labelResponse.setVisible(true);
        }
    }

    @FXML
    public void handleButtonProjectIncreaseBudgetAction(ActionEvent event) {
        Project selectedProject = comboBoxProjectIncreaseBudget.getSelectionModel().getSelectedItem();
        if (selectedProject != null) {
            double currentBudget = selectedProject.getBudget();
            double newBudget = currentBudget * 1.1;
            selectedProject.setBudget(newBudget);

            String message = "Project budget increased successfully.";
            labelResponse.setText(message);
            labelResponse.setVisible(true);
            tableViewProject.refresh();
        } else {
            String message = "Please select a project.";
            labelResponse.setText(message);
            labelResponse.setVisible(true);
        }
    }

    private void populateTableView() {
        tableViewProject.getItems().clear();
        tableViewProject.setItems(projectRegister.getProjects());
    }

    private void populateComboBox() {
        comboBoxProjectIncreaseBudget.getItems().clear();
        comboBoxProjectIncreaseBudget.setItems(projectRegister.getProjects());
    }

    public void setProjectRegister(ProjectRegister projectRegister) {
        this.projectRegister = projectRegister;
        populateTableView();
        populateComboBox();
    }
}
