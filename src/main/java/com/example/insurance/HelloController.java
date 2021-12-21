package com.example.insurance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.sql.SQLException;

public class HelloController {
    static Client client;
    @FXML
    private Button submitButton;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Label name;

    @FXML
    public void getData(ActionEvent actionEvent) throws SQLException, IOException{
        Object user = JavaPostgersSQL.checkPassword(login.getText().toString(), password.getText().toString());
        String className = user.getClass().getName();
        if(className == "com.example.insurance.Admin") {
            Window.newWindow(HelloController.class, "admin-view.fxml","Администратор", submitButton);
        } else if(className == "com.example.insurance.Client") {
            Client c = (Client) user;
            client = c;
            Window.newWindow(HelloController.class, "client-view.fxml","Клиент", submitButton);
        } else {
            name.setText("Неправильное имя пользователя или пароль");
            name.setTextFill(Paint.valueOf("red"));
        }
        login.setText("");
        password.setText("");
    }
}