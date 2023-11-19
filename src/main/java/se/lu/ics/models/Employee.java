package se.lu.ics.models;

import java.time.LocalDate;

public class Employee {
    private String id;
    private String name;
    private String title;
    private double baseSalary;
    private LocalDate hireDate;
    private Project project;

    public Employee(String id, String name, String title, double baseSalary, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.baseSalary = baseSalary;
        this.hireDate = hireDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
