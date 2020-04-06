package com.gerywahyu.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

public interface Content {
  void drawContent(PDDocument context, PDPageContentStream contentStream) throws IOException;
  String toString();
}