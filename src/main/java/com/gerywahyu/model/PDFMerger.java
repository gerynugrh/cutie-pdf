package com.gerywahyu.model;

import javafx.application.Platform;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;

public class PDFMerger {
  private PDFContents contents;

  public PDFMerger(PDFContents contents) {
    this.contents = contents;
  }

  public void save(File file, OnSuccessCallback successCallback, OnErrorCallback errorCallback) {
    new Thread(() -> {
      try {
        PDDocument doc = new PDDocument();
        for (int i = 0; i < contents.getLength(); i++) {
          PDPage page = new PDPage(PDRectangle.A4);
          contents.addContentToPage(doc, page, i);
          doc.addPage(page);
        }
        doc.save(file);
        doc.close();

        Platform.runLater(successCallback::onSuccess);
      } catch (Exception ex) {
        Platform.runLater(() -> errorCallback.onError(ex));
      }
    }).start();
  }

  public interface OnSuccessCallback {
    void onSuccess();
  }

  public interface OnErrorCallback {
    void onError(Exception ex);
  }
}