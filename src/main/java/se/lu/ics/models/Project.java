package se.lu.ics.models;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Project {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private ObservableList<Employee> employees;

    public Project(String id, String name, LocalDate startDate, LocalDate endDate, double budget) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.employees = FXCollections.observableArrayList();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void addBatchEmployees(Employee[] employees) {
        this.employees.addAll(employees);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    /*
     * Returns an unmodifiable list of employees.
     * Purpose: To preserve encapsulation
     * by preventing the caller from modifying the list.
     */
    public ObservableList<Employee> getEmployees() {
        return FXCollections.unmodifiableObservableList(employees);
    }
}
