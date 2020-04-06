package com.gerywahyu.controller;

import com.gerywahyu.model.PDFContents;
import com.gerywahyu.model.PDFMerger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class MainMenu {
  @FXML
  private Button addFileButton;

  @FXML
  private Button saveFileButton;

  private PDFContents pdfContents = new PDFContents();

  @FXML
  void handleAddFileButton() {
    Window owner = addFileButton.getScene().getWindow();
    FileChooser fileChooser = new FileChooser();
    pdfContents.addAll(fileChooser.showOpenMultipleDialog(owner));
  }

  @FXML
  void handleSaveFileButton() {
    Window owner = addFileButton.getScene().getWindow();
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showSaveDialog(owner);

    PDFMerger pdfMerger = new PDFMerger(pdfContents);

    pdfMerger.save(file, () -> {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Merger Response");
      alert.setHeaderText("Success");
      alert.setContentText("Converting and Merging is successful");

      alert.showAndWait();
    }, ex -> {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Merger Response");
      alert.setHeaderText("Error");
      alert.setContentText("Converting and Merging failed");

      alert.showAndWait();
    });
  }
}