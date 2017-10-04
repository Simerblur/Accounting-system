package pl.coderstrust.fileprocessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {

  private List<String> readLinesFromFile(String pathNameInput) {
    List<String> readLinesFromFile;
    try {
      readLinesFromFile = new ArrayList<>();
      Scanner inLineScan = new Scanner(new File(pathNameInput));
      while (inLineScan.hasNextLine()) {
        readLinesFromFile.add(inLineScan.nextLine());
      }
      inLineScan.close();
      return readLinesFromFile;
    } catch (IOException e) {
      System.out.println(e.toString());
      readLinesFromFile = new ArrayList<>();
      readLinesFromFile.add(0, "File not found");
      return readLinesFromFile;
    }
  }

  /**
   * Returns content of the provided file as a List of strings.
   *
   * @param pathNameInput specifies a path to the file.
   * @return ArrayList of Strings.
   */

  public List<String> readInvoicesFromFile(String pathNameInput) {
    List<String> readLinesFromFile;
    readLinesFromFile = readLinesFromFile(pathNameInput);
    return readLinesFromFile;
  }

  public void appendInvoiceToFile(String inputString, String pathNameOutput) {
    writeProvidedLinesToFile(inputString, pathNameOutput);
  }

  private void writeProvidedLinesToFile(String inputString, String pathNameOutput) {
    try (FileWriter myFileWriter = new FileWriter(pathNameOutput, true)) {
      myFileWriter.write(inputString);
      myFileWriter.write(System.lineSeparator());
      myFileWriter.close();
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }
}
