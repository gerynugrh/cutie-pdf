package com.gerywahyu.model;

import com.gerywahyu.util.FileUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFContents {
  ObservableList<Content> contents = FXCollections.observableArrayList();

  public void add(File file) {
    String extension = FileUtil.getFileExtension(file);
    switch (extension) {
      case ".png":
      case ".jpg":
      case ".jpeg":
        contents.add(new ImageContent(file));
        break;
    }
  }

  public void addAll(List<File> files) {
    for (File file : files) {
      this.add(file);
    }
  }

  public void addContentToPage(PDDocument context, PDPage page, int contentNumber) throws IOException {
    PDPageContentStream contentStream = new PDPageContentStream(context, page);
    contents.get(contentNumber).drawContent(context, contentStream);
    contentStream.close();
  }

  public int getLength() {
    return contents.size();
  }

  public ObservableList<Content> getContents() {
    return contents;
  }

  public void removeAllContents() {
    contents.clear();
  }
}
