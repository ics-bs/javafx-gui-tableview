package se.lu.ics.controllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.lu.ics.models.Project;
import se.lu.ics.models.ProjectRegister;

public class ProjectAddDialogController {
    private ProjectRegister projectRegister;

    @FXML
    private TextField textFieldProjectId;

    @FXML
    private TextField textFieldProjectName;

    @FXML
    private DatePicker datePickerProjectStartDate;

    @FXML
    private DatePicker datePickerProjectEndDate;

    @FXML
    private TextField textFieldProjectBudget;

    @FXML
    private Label labelResponse;

    public void initialize() {
    }

    public void setProjectRegister(ProjectRegister projectRegister) {
        this.projectRegister = projectRegister;
    }

    @FXML
    public void handleButtonProjectSaveAction(ActionEvent event) {

        boolean isTextFieldProjectIdEmpty = textFieldProjectId.getText().isEmpty();
        boolean isTextFieldProjectNameEmpty = textFieldProjectName.getText().isEmpty();
        boolean isDatePickerProjectStartDateEmpty = datePickerProjectStartDate.getValue() == null;
        boolean isDatePickerProjectEndDateEmpty = datePickerProjectEndDate.getValue() == null;
        boolean isTextFieldProjectBudgetEmpty = textFieldProjectBudget.getText().isEmpty();

        boolean isTextFieldProjectBudgetNumber = false;
        try {
            Double.parseDouble(textFieldProjectBudget.getText());
            isTextFieldProjectBudgetNumber = true;
        } catch (NumberFormatException e) {
            isTextFieldProjectBudgetNumber = false;
        }
        
        if (isTextFieldProjectIdEmpty 
            || isTextFieldProjectNameEmpty 
            || isDatePickerProjectStartDateEmpty
            || isDatePickerProjectEndDateEmpty 
            || isTextFieldProjectBudgetEmpty) {
                String errorMessage = "Please fill in all fields.";
                labelResponse.setText(errorMessage);
                labelResponse.setVisible(true);
        } else if (!isTextFieldProjectBudgetNumber) {
            String errorMessage = "Please enter a number in the budget field.";
            labelResponse.setText(errorMessage);
            labelResponse.setVisible(true);
        } else {
            String id = textFieldProjectId.getText();
            String name = textFieldProjectName.getText();
            LocalDate startDate = datePickerProjectStartDate.getValue();
            LocalDate endDate = datePickerProjectEndDate.getValue();
            String budget = textFieldProjectBudget.getText();
            double budgetDouble = Double.parseDouble(budget);

            Project newProject = new Project(id, name, startDate, endDate, budgetDouble);

            projectRegister.addProject(newProject);

            textFieldProjectId.clear();
            textFieldProjectName.clear();
            datePickerProjectStartDate.setValue(null);
            datePickerProjectEndDate.setValue(null);
            textFieldProjectBudget.clear();

            String message = "Project added.";
            labelResponse.setText(message);
            labelResponse.setVisible(true);
        }
    }

    @FXML
    public void handleButtonBackAction(ActionEvent event) {
        Stage stage = (Stage) textFieldProjectId.getScene().getWindow();
        stage.close();
    }
}
