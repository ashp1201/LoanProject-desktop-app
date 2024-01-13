module com.example.loanproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jetty;


    opens com.example.loanproject to javafx.fxml;
    exports com.example.loanproject;
}