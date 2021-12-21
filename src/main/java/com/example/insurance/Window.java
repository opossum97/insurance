package com.example.insurance;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Window {
    public Window() {
    }

    public static void newWindow(Class classWindow, String path, String title, Button button) throws IOException {
        Pane root = (Pane) FXMLLoader.load(classWindow.getResource(path));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}

