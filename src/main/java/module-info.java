module ua.lpnu.lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ua.lpnu.lab5 to javafx.fxml;
    exports ua.lpnu.lab5;
}