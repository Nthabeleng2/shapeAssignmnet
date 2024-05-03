module com.example.shapeassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shapeassignment to javafx.fxml;
    exports com.example.shapeassignment;
}