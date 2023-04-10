module com.example.whether {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.json;


    opens com.example.whether to javafx.fxml;
    exports com.example.whether;
}