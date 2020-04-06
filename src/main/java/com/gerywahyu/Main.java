package com.gerywahyu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    URL url = getClass().getResource("MainMenu.fxml");
    Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
    primaryStage.setTitle("Convert To PDF");
    primaryStage.setScene(new Scene(root, 300, 200));
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}