package se.lu.ics.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * This class is currently not used in the application.
 * However if we would want to create a view for all employees
 * in the application, we would need a class like this.
 */
public class EmployeeRegister {
    private ObservableList<Employee> employees;

    public EmployeeRegister() {
        this.employees = FXCollections.observableArrayList();
    }

    /*
     * Returns an unmodifiable list of employees.
     * Purpose: To preserve encapsulation
     * by preventing the caller from modifying the list.
     */
    public ObservableList<Employee> getEmployees() {
        return FXCollections.unmodifiableObservableList(this.employees);
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }
}
