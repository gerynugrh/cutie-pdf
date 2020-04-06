package com.gerywahyu.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;

public class ImageContent implements Content {

  String contentPath;

  ImageContent(File file) {
    contentPath = file.getAbsolutePath();
  }

  @Override
  public void drawContent(PDDocument context, PDPageContentStream contentStream) throws IOException {
    PDImageXObject pdImage = PDImageXObject.createFromFile(contentPath, context);
    contentStream.drawImage(pdImage, 0, 0, PDRectangle.A4.getWidth(), PDRectangle.A4.getHeight());
  }

  @Override
  public String toString() {
    return contentPath;
  }
}