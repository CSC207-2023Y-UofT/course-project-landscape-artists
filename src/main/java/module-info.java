module com.example.kanbangui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kanbangui to javafx.fxml;
    exports com.example.kanbangui;
}