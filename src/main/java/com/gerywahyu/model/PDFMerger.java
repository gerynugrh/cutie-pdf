package com.gerywahyu.model;

import javafx.application.Platform;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.File;
import java.io.IOException;

public class PDFMerger {
  private PDFContents contents;
  private OnProgressCallback onProgressCallback;
  private OnSuccessCallback onSuccessCallback;
  private OnErrorCallback onErrorCallback;

  public PDFMerger(PDFContents contents) {
    this.contents = contents;
  }

  public void save(File file) {
    new Thread(() -> {
      try {
        PDDocument doc = new PDDocument();
        for (int i = 0; i < contents.getLength(); i++) {
          PDPage page = new PDPage(PDRectangle.A4);
          contents.addContentToPage(doc, page, i);
          doc.addPage(page);

          final float progress = (float) i / contents.getLength();
          Platform.runLater(() -> onProgressCallback.onProgress(progress));
        }
        doc.save(file);
        doc.close();

        Platform.runLater(() -> onProgressCallback.onProgress(1.0F));
        Platform.runLater(onSuccessCallback::onSuccess);
      } catch (Exception ex) {
        Platform.runLater(() -> onErrorCallback.onError(ex));
      }
    }).start();
  }

  public void setOnErrorCallback(OnErrorCallback onErrorCallback) {
    this.onErrorCallback = onErrorCallback;
  }

  public void setOnSuccessCallback(OnSuccessCallback onSuccessCallback) {
    this.onSuccessCallback = onSuccessCallback;
  }

  public void setOnProgressCallback(OnProgressCallback onProgressCallback) {
    this.onProgressCallback = onProgressCallback;
  }

  public interface OnSuccessCallback {
    void onSuccess();
  }

  public interface OnErrorCallback {
    void onError(Exception ex);
  }

  public interface OnProgressCallback {
    void onProgress(float progress);
  }
}