package pl.coderstrust.database.fileprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {

  private String pathNameInput;
  private String pathNameOutput;
  private String inputString;
  private List<String> readLinesFromFile;

  private List<String> readLinesFromFile(String pathNameInput) {
//    List<String> readLinesFromFile = new ArrayList<>();
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
    this.pathNameInput = pathNameInput;
    readLinesFromFile = readLinesFromFile(pathNameInput);
    return readLinesFromFile;
  }

  public void appendInvoiceToFile(String inputString, String pathNameOutput) throws IOException {
    this.pathNameOutput = pathNameOutput;
    this.inputString = inputString;
    writeProvidedLinesToFile(inputString, pathNameOutput);
  }

  private void writeProvidedLinesToFile(String inputString, String pathNameOutput){
    try (FileWriter myFileWriter = new FileWriter(pathNameOutput, true)) {
      myFileWriter.write(inputString);
      myFileWriter.write(System.lineSeparator());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
