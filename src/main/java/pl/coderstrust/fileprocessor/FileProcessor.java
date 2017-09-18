package pl.coderstrust.fileprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {

  private List<String> readLinesFromFile;

  private List<String> readLinesFromFile(String pathNameInput) {
    try {
      readLinesFromFile = new ArrayList<>();
      Scanner inLineScan = new Scanner(new File(pathNameInput));
      while (inLineScan.hasNextLine()) {
        readLinesFromFile.add(inLineScan.nextLine());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return readLinesFromFile;
  }

  public List<String> readInvoicesFromFile(String pathNameInput) throws IOException {
    readLinesFromFile = readLinesFromFile(pathNameInput);
    return readLinesFromFile;
  }

  public void appendInvoiceToFile(String inputString, String pathNameOutput) throws IOException {
    writeProvidedLinesToFile(inputString, pathNameOutput);
  }

  private void writeProvidedLinesToFile(String inputString, String pathNameOutput) {
    try (FileWriter myFileWriter = new FileWriter(pathNameOutput, true)) {
      myFileWriter.write(inputString);
      myFileWriter.write(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
