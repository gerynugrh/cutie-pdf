package com.gerywahyu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class MainMenu {
  @FXML
  private Button addFileButton;

  @FXML
  void handleAddFileButton() {
    Window owner = addFileButton.getScene().getWindow();
    FileChooser fileChooser = new FileChooser();
    fileChooser.showOpenMultipleDialog(owner);
  }
}