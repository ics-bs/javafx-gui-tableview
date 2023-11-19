package se.lu.ics.controllers;

import java.time.LocalDate;
import java.time.Period;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import se.lu.ics.models.Employee;
import se.lu.ics.models.Project;


public class ProjectEmployeesViewController {

    /*
     * The project for which the employees are to be displayed.
     */
    private Project project;

    @FXML
    private TableView<Employee> tableViewEmployee;

    @FXML
    private TableColumn<Employee, String> tableColumnEmployeeId;

    @FXML
    private TableColumn<Employee, String> tableColumnEmployeeName;

    @FXML
    private TableColumn<Employee, String> tableColumnEmployeeTitle;

    @FXML
    private TableColumn<Employee, Double> tableColumnEmployeeBaseSalary;

    @FXML
    private TableColumn<Employee, LocalDate> tableColumnEmployeeHireDate;

    @FXML
    private TableColumn<Employee, String> tableColumnEmployeeTimeWithCompany;

    @FXML
    private TableColumn<Employee, String> tableColumnEmployeeCurrentProject;

    @FXML
    private Label labelResponse;

    public void initialize() {
        tableColumnEmployeeId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableColumnEmployeeName.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        /*
         * This cell is editable. When the user edits the cell, the new value
         * is passed to the setOnEditCommit() method. The event object contains
         * the row value, which is the Employee object that is to be updated.
         * The new value is retrieved from the event object, and the setName()
         * method is called on the Employee object.
         */
        tableColumnEmployeeName.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setName(event.getNewValue());
        });
        tableColumnEmployeeTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableColumnEmployeeTitle.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setTitle(event.getNewValue());
        });
        tableColumnEmployeeBaseSalary.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
        tableColumnEmployeeBaseSalary.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setBaseSalary(event.getNewValue());
        });
        tableColumnEmployeeHireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        tableColumnEmployeeCurrentProject.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            Project currentProject = employee.getProject();

            if (currentProject != null) {
                String currentProjectName = currentProject.getId() + ": " + currentProject.getName();
                return new ReadOnlyObjectWrapper<>(currentProjectName);
            } else {
                return new ReadOnlyObjectWrapper<>("No project");
            }
        });

        tableColumnEmployeeTimeWithCompany.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            LocalDate hireDate = employee.getHireDate();
            LocalDate now = LocalDate.now();
            Period period = Period.between(hireDate, now);

            int years = period.getYears();
            int months = period.getMonths();
            int days = period.getDays();

            String timeWithCompany = years + " years, " + months + " months, " + days + " days";
            return new ReadOnlyObjectWrapper<>(timeWithCompany);
        });
    }

    @FXML
    public void handleButtonEmployeeDeleteAction(ActionEvent event) {
        Employee selectedEmployee = tableViewEmployee.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            project.removeEmployee(selectedEmployee);
            String response = "Employee deleted successfully!";
            labelResponse.setText(response);
            labelResponse.setVisible(true);
        }
    }

    @FXML
    public void handleButtonBackAction(ActionEvent event) {
        Stage stage = (Stage) tableViewEmployee.getScene().getWindow();
        stage.close();
    }

    private void populateTableView() {
        tableViewEmployee.setItems(project.getEmployees());
    }

    public void setProject(Project project) {
        this.project = project;
        populateTableView();
    }
    
}
