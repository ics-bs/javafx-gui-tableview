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
import javafx.util.StringConverter;
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

        tableColumnEmployeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        /*
         * TextFieldTableCell is a cell that allows the user to edit the cell
         * value. The cell value is displayed as a TextField.
         * TextFieldTableCell can handle String values out of the box.
         * If the cell value is not a String, a StringConverter must be
         * specified.
         */
        tableColumnEmployeeName.setCellFactory(TextFieldTableCell.forTableColumn());
        /*
         * When the user edits the cell, the new value
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
        tableColumnEmployeeTitle.setCellFactory(TextFieldTableCell.forTableColumn());
        tableColumnEmployeeTitle.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            employee.setTitle(event.getNewValue());
        });

        tableColumnEmployeeBaseSalary.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
        /*
         * TableView requires explicit instructions on how to convert
         * the text entered by the user to a non-String type.
         * (as the user can enter any text in the cell using their keyboard)
         * Therefore, we need to use a custom cell factory.
         * StringConverter is an abstract class with two abstract
         * methods: toString() and fromString(). The toString() method
         * that when implemented, allows us to specify how a given type
         * should be converted to a String.
         */
        tableColumnEmployeeBaseSalary.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

            /*
             * The toString() method is called when the TableView is
             * populated with data. The method is called for each
             * row in the table, and the return value is displayed.
             * %.2f means that the number should be displayed with
             * two decimals.
             */
            @Override
            public String toString(Double object) {
                return String.format("%.2f", object);
            }

            /*
             * The fromString() method is called when the user edits
             * the cell. The method is called when the user presses
             * Enter, or when the cell loses focus. The method is
             * called with the String that the user entered in the
             * cell. The method should return the value that the
             * String should be converted to.
             */
            @Override
            public Double fromString(String string) {
                try {
                    return Double.parseDouble(string);
                } catch (NumberFormatException e) {
                    String errorMessage = "Invalid input format. Please enter a number.";
                    labelResponse.setText(errorMessage);
                    return 0.0;
                }
            }
        }));

        tableColumnEmployeeBaseSalary.setOnEditCommit(event -> {
            Employee employee = event.getRowValue();
            Double newBaseSalary = event.getNewValue();

            // Check if a new salary was entered
            if (newBaseSalary != null) {
                employee.setBaseSalary(newBaseSalary);
            }
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