package se.lu.ics.models;

import java.time.LocalDate;

public class ModelIntegrationTest {

    public static void main(String[] args) {
        ProjectRegister projectRegister = new ProjectRegister();

        Project project1 = new Project("P01", "Skyline Residential Towers", LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1), 2000000);
        Project project2 = new Project("P02", "Greenfield Commercial Plaza", LocalDate.of(2023, 4, 1),
                LocalDate.of(2024, 4, 1), 1800000);

        Employee employee1 = new Employee("E01", "Mia Torres", "Architect", 85000, LocalDate.of(2019, 5, 15));
        Employee employee2 = new Employee("E02", "Carlos Rivera", "Structural Engineer", 80000,
                LocalDate.of(2018, 8, 20));
        Employee employee3 = new Employee("E03", "Liam Chen", "Project Manager", 90000, LocalDate.of(2020, 1, 10));
        Employee employee4 = new Employee("E04", "Aisha Patel", "Civil Engineer", 75000, LocalDate.of(2021, 2, 15));
        Employee employee5 = new Employee("E05", "John Doe", "Electrician", 60000, LocalDate.of(2017, 4, 12));

        project1.addEmployee(employee1);
        employee1.setProject(project1);

        project1.addEmployee(employee2);
        employee2.setProject(project1);

        project1.addEmployee(employee3);
        employee3.setProject(project1);

        project2.addEmployee(employee3);
        employee3.setProject(project2);

        project2.addEmployee(employee4);
        employee4.setProject(project2);

        projectRegister.addProject(project1);
        projectRegister.addProject(project2);

        System.out.println("Project1:");
        System.out.println("ID: " + project1.getId());
        System.out.println("Name: " + project1.getName());
        System.out.println("Start date: " + project1.getStartDate());
        System.out.println("End date: " + project1.getEndDate());
        System.out.println("Budget: " + project1.getBudget());

        System.out.println();

        System.out.println("Employee5:");
        System.out.println("ID: " + employee5.getId());
        System.out.println("Name: " + employee5.getName());
        System.out.println("Title: " + employee5.getTitle());
        System.out.println("Base salary: " + employee5.getBaseSalary());
        System.out.println("Hire date: " + employee5.getHireDate());

        System.out.println();

        System.out.println("Employees of project1:");
        for (Employee employee : project1.getEmployees()) {
            System.out.println(employee.getId() + " " + employee.getName());
        }

        System.out.println("Project of employee4:");
        System.out.println("ID: " + employee4.getProject().getId());
        System.out.println("Name: " + employee4.getProject().getName());

        System.out.println();
        System.out.println("Done!");

    }

}
