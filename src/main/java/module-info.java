module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens org.example to javafx.fxml, lombok;
    exports org.example;

}

