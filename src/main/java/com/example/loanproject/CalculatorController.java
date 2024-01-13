package com.example.loanproject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculatorController implements Initializable {
    @FXML
    private TextField tfAnnualInterestRate ;
    @FXML
    private TextField tfNumberOfYears;
    @FXML
    private TextField tfLoanAmount;
    @FXML
    private TextField tfMonthlyPayment ;
    @FXML
    private TextField tfTotalPayment ;
    @FXML
    private Button btCalculate;
    @FXML
    private Button button_home;
    Alert a = new Alert(Alert.AlertType.NONE);
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_home.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("logged-in.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();
                    stage.setTitle("Home page");
                    stage.setScene(scene);
                    stage.show();
                    ((Node) (event.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                    Logger logger = Logger.getLogger(getClass().getName());
                    logger.log(Level.SEVERE, "Failed", e);
                }
            }
        });
    }

    @FXML
    private void handle (ActionEvent event) {
       try {
           double interest = Double.parseDouble(tfAnnualInterestRate.getText());

           int year = Integer.parseInt(tfNumberOfYears.getText());
           double loanAmount = Double.parseDouble(tfLoanAmount.getText());
           double EMi = getMonthlyPayment(interest, year, loanAmount);
           double Tot = getTotalPayment(interest, year, loanAmount);
           tfMonthlyPayment.setText(String.format("₹%.2f", EMi));
           tfTotalPayment.setText(String.format("₹%.2f", Tot));
       }
       catch (Exception e)
       {
          System.out.println("Wrong input ,try again");
        Alert.AlertType type = Alert.AlertType.ERROR;
        Alert alert =new Alert(type,"");
        alert.getDialogPane().setContentText("Wrong input ,try again");
        alert.getDialogPane().setHeaderText("Error!");
        alert.showAndWait();
       }
    }
    /** Find monthly payment */
    public double getMonthlyPayment(double interest,int year,double loanAmount ) {
        double monthlyInterestRate = interest / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - (1 / Math.pow(1 + monthlyInterestRate, year * 12)));
        return monthlyPayment;
    }

    /** Find total payment */
    public double getTotalPayment(double interest ,int year,double loanAmount) {
        double totalPayment = getMonthlyPayment(interest,year,loanAmount) * year * 12;
        return totalPayment;
    }
}


