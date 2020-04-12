package com.gerywahyu.controller;

import com.gerywahyu.model.Content;
import com.gerywahyu.model.PDFContents;
import com.gerywahyu.model.PDFMerger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class MainMenu {
  @FXML
  private Button addFileButton;

  @FXML
  private Button clearAllButton;

  @FXML
  private Button saveFileButton;

  @FXML
  private ProgressBar progressBar;

  @FXML
  private ListView<Content> addedFiles;

  private PDFContents pdfContents = new PDFContents();

  @FXML
  public void initialize() {
    addedFiles.setItems(pdfContents.getContents());
    progressBar.setProgress(0);
  }

  @FXML
  void handleAddFileButton() {
    Window owner = addFileButton.getScene().getWindow();
    FileChooser fileChooser = new FileChooser();
    pdfContents.addAll(fileChooser.showOpenMultipleDialog(owner));
  }

  @FXML
  void handleClearAllButton() {
    progressBar.setProgress(0);
    pdfContents.removeAllContents();
  }

  @FXML
  void handleSaveFileButton() {
    Window owner = saveFileButton.getScene().getWindow();
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showSaveDialog(owner);

    PDFMerger pdfMerger = new PDFMerger(pdfContents);
    pdfMerger.setOnSuccessCallback(() -> {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Merger Response");
      alert.setHeaderText("Success");
      alert.setContentText("Converting and Merging is successful");

      alert.showAndWait();
    });
    pdfMerger.setOnErrorCallback(ex -> {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Merger Response");
      alert.setHeaderText("Error");
      alert.setContentText("Converting and Merging failed");

      alert.showAndWait();
    });
    pdfMerger.setOnProgressCallback(progress -> {
      progressBar.setProgress(progress);
    });
    progressBar.setProgress(0);
    pdfMerger.save(file);
  }
}