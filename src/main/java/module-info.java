module com.example.kanbangui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kanbangui to javafx.fxml;
    exports com.example.kanbangui;

    opens interface_adapters to javafx.fxml;
    exports interface_adapters;
}