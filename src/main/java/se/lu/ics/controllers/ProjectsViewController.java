package se.lu.ics.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

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
import javafx.stage.Stage;

import se.lu.ics.models.ProjectRegister;
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

        /*
         * The cell value factories are used to populate the table columns with data
         * from the Project object. The argument "id" will cause the cell value factory
         * to call the getId() method on the Project object.
         */
        tableColumnProjectId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableColumnProjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnProjectName.setOnEditCommit(event -> {
            Project project = event.getRowValue();
            project.setName(event.getNewValue());
        });

        tableColumnProjectStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tableColumnProjectEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        /*
         * The values in the duration columns are not based on the data in the
         * Project object. Instead, the values are calculated based on the start and end
         * dates of the project. This is why we need to use a custom cell factory.
         */
        tableColumnProjectDuration.setCellValueFactory(cellData -> {
            Project project = cellData.getValue();
            LocalDate startDate = project.getStartDate();
            LocalDate endDate = project.getEndDate();

            /*
             * The duration is calculated by subtracting the start date from the end date.
             * The result is a Period object, which contains the number of years, months,
             * and days between the two dates.
             */
            Period period = Period.between(startDate, endDate);
            int years = period.getYears();
            int months = period.getMonths();
            int days = period.getDays();

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

    @FXML
    public void handleButtonViewEmployeesAction(ActionEvent event) {
        Project selectedProject = tableViewProject.getSelectionModel().getSelectedItem();

        /*
         * If no project is selected, selectedProject will be null, and
         * we cannot show the employees for the project.
         * Instead, we show an error message to the user.
         */
        if (selectedProject == null) {
            String message = "Please select a project.";
            labelResponse.setText(message);
            labelResponse.setVisible(true);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectEmployeesView.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            ProjectEmployeesViewController controller = loader.getController();

            controller.setProject(selectedProject);

            String projectName = selectedProject.getName();

            stage.setTitle("Employees for project " + projectName);
            stage.show();
        } catch (IOException e) {

            String errorMessage = "Internal system error, please contact support.";
            labelResponse.setText(errorMessage);
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