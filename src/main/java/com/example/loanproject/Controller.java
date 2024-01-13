package com.example.loanproject;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import  javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;
import java.net.URL;
    public class Controller implements Initializable
    {
        public Label welcomeText;
        @FXML
        private Button button_login;
        @FXML
        private Button button_signup;
        @FXML
        private TextField tf_username;
        @FXML
        private TextField tf_password;
        @Override
        public void initialize(URL location, ResourceBundle resourses)
        {
            button_login.setOnAction(new EventHandler<ActionEvent>() {
                                         @Override
                                         public void handle(ActionEvent event) {
                                             DBUtils.logInUser(event, tf_username.getText(), tf_password.getText());

                                         }
                                     });


            button_signup.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent event)
                {
                    DBUtils.changeScene(event,"sign-up.fxml","Sign Up",null);
                }

            });


        }
    }
