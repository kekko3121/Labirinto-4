module com.labirinto {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.labirinto to javafx.fxml;
    exports com.labirinto;
}
