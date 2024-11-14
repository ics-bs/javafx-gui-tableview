package se.lu.ics.models;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectRegister {
    private ObservableList<Project> projects;

    public ProjectRegister() {
        this.projects = FXCollections.observableArrayList();
    }

    /*
     * Returns an unmodifiable list of employees.
     * Purpose: To preserve encapsulation
     * by preventing the caller from modifying the list.
     */
    public ObservableList<Project> getProjects() {
        return FXCollections.unmodifiableObservableList(this.projects);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
    }
}