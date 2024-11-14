package se.lu.ics.models;

import java.time.LocalDate;

public class AppModel {
    private ProjectRegister projectRegister;
    private EmployeeRegister employeeRegister;

    public AppModel() {
        this.projectRegister = new ProjectRegister();
        this.employeeRegister = new EmployeeRegister();

        addTestData();
    }

    public ProjectRegister getProjectRegister() {
        return this.projectRegister;
    }

    public EmployeeRegister getEmployeeRegister() {
        return this.employeeRegister;
    }

    /*
     * This method is called to add test data to the ObservableList.
     * Test data is used for testing purposes and
     * demonstration purposes only and should be removed
     * when the application is ready for production.
     */
    private void addTestData() {

        Employee emp1 = new Employee("E01", "Mia Torres", "Architect", 85000, LocalDate.of(2019, 5, 15));
        Employee emp2 = new Employee("E02", "Carlos Rivera", "Structural Engineer", 80000, LocalDate.of(2018, 8, 20));
        Employee emp3 = new Employee("E03", "Liam Chen", "Project Manager", 90000, LocalDate.of(2020, 1, 10));
        Employee emp4 = new Employee("E04", "Aisha Patel", "Civil Engineer", 75000, LocalDate.of(2021, 2, 15));
        Employee emp5 = new Employee("E05", "John Doe", "Electrician", 60000, LocalDate.of(2017, 4, 12));
        Employee emp6 = new Employee("E06", "Emily Smith", "Safety Officer", 65000, LocalDate.of(2018, 7, 21));
        Employee emp7 = new Employee("E07", "Kwame Nkrumah", "Mechanical Engineer", 80000, LocalDate.of(2019, 11, 30));
        Employee emp8 = new Employee("E08", "Sophia Zhang", "Landscape Designer", 72000, LocalDate.of(2020, 5, 22));
        Employee emp9 = new Employee("E09", "Luca Rossi", "Surveyor", 68000, LocalDate.of(2021, 3, 18));
        Employee emp10 = new Employee("E10", "Nadia Ali", "Interior Designer", 76000, LocalDate.of(2018, 12, 10));
        Employee emp11 = new Employee("E11", "Omar Farooq", "Foreman", 65000, LocalDate.of(2020, 6, 7));
        Employee emp12 = new Employee("E12", "Sarah Johnson", "Environmental Consultant", 77000,
                LocalDate.of(2019, 3, 15));
        Employee emp13 = new Employee("E13", "Diego Sánchez", "Quantity Surveyor", 71000, LocalDate.of(2017, 9, 20));
        Employee emp14 = new Employee("E14", "Leila Khaled", "HR Manager", 78000, LocalDate.of(2018, 11, 5));
        Employee emp15 = new Employee("E15", "Ivan Petrov", "Plumber", 58000, LocalDate.of(2020, 8, 30));
        Employee emp16 = new Employee("E16", "Zhang Wei", "Carpenter", 62000, LocalDate.of(2019, 2, 12));
        Employee emp17 = new Employee("E17", "Fatima Al-Fihri", "Site Inspector", 69000, LocalDate.of(2021, 4, 9));
        Employee emp18 = new Employee("E18", "Alex Johnson", "Mason", 55000, LocalDate.of(2018, 6, 18));
        Employee emp19 = new Employee("E19", "Yasmin Mohammed", "Project Coordinator", 73000,
                LocalDate.of(2019, 7, 22));
        Employee emp20 = new Employee("E20", "Bruce Wayne", "Logistics Manager", 76000, LocalDate.of(2020, 12, 15));

        Project proj1 = new Project("P01", "Skyline Residential Towers", LocalDate.of(2023, 1, 1),
                LocalDate.of(2024, 1, 1), 2000000);
        Project proj2 = new Project("P02", "Greenfield Commercial Plaza", LocalDate.of(2023, 4, 1),
                LocalDate.of(2024, 4, 1), 1800000);
        Project proj3 = new Project("P03", "Harborfront Leisure Complex", LocalDate.of(2023, 6, 1),
                LocalDate.of(2024, 8, 30), 2500000);
        Project proj4 = new Project("P04", "Downtown Bridge Reconstruction", LocalDate.of(2023, 2, 15),
                LocalDate.of(2023, 12, 15), 1500000);

        // Assigning Employees to Projects
        proj1.addBatchEmployees(new Employee[] { emp1, emp2, emp3, emp4, emp5 });
        proj2.addBatchEmployees(new Employee[] { emp6, emp7, emp8, emp9, emp10 });
        proj3.addBatchEmployees(new Employee[] { emp11, emp12, emp13, emp14, emp15 });
        proj4.addBatchEmployees(new Employee[] { emp16, emp17, emp18, emp19, emp20 });

        // Adding Projects to Employee
        emp1.setProject(proj1);
        emp2.setProject(proj1);
        emp3.setProject(proj1);
        emp4.setProject(proj1);
        emp5.setProject(proj1);

        emp6.setProject(proj2);
        emp7.setProject(proj2);
        emp8.setProject(proj2);
        emp9.setProject(proj2);
        emp10.setProject(proj2);

        emp11.setProject(proj3);
        emp12.setProject(proj3);
        emp13.setProject(proj3);
        emp14.setProject(proj3);
        emp15.setProject(proj3);

        emp16.setProject(proj4);
        emp17.setProject(proj4);
        emp18.setProject(proj4);
        emp19.setProject(proj4);
        emp20.setProject(proj4);

        this.projectRegister.addProject(proj1);
        this.projectRegister.addProject(proj2);
        this.projectRegister.addProject(proj3);
        this.projectRegister.addProject(proj4);

        this.employeeRegister.addEmployee(emp1);
        this.employeeRegister.addEmployee(emp2);
        this.employeeRegister.addEmployee(emp3);
        this.employeeRegister.addEmployee(emp4);
        this.employeeRegister.addEmployee(emp5);
        this.employeeRegister.addEmployee(emp6);
        this.employeeRegister.addEmployee(emp7);
        this.employeeRegister.addEmployee(emp8);
        this.employeeRegister.addEmployee(emp9);
        this.employeeRegister.addEmployee(emp10);
        this.employeeRegister.addEmployee(emp11);
        this.employeeRegister.addEmployee(emp12);
        this.employeeRegister.addEmployee(emp13);
        this.employeeRegister.addEmployee(emp14);
        this.employeeRegister.addEmployee(emp15);
        this.employeeRegister.addEmployee(emp16);
        this.employeeRegister.addEmployee(emp17);
        this.employeeRegister.addEmployee(emp18);
        this.employeeRegister.addEmployee(emp19);
        this.employeeRegister.addEmployee(emp20);
    }
}