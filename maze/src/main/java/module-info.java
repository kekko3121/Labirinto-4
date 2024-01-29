module com.maze {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.maze to javafx.fxml;
    exports com.maze;
}
