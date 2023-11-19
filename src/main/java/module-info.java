module javafx.gui.tableview {
    exports se.lu.ics;
    exports se.lu.ics.controllers;

    opens se.lu.ics.controllers to javafx.fxml;
    
    /*
     * Required for the TableView to be able to access the Project class
     * via PropertyValuesFactory
     */
    opens se.lu.ics.models to javafx.base;

    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
}
