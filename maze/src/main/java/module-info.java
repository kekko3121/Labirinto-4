module com.maze {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens com.maze to javafx.fxml;
    exports com.maze;
}