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
  private List<String> inputList;
  private List<String> readLinesFromFile;


  private List<String> readLinesFromFile(String pathNameInput) throws IOException{
    List<String> readLinesFromFile = new ArrayList<>();
    try {
      readLinesFromFile = new ArrayList<>();
      Scanner inLineScan = new Scanner(new File(pathNameInput));
      while (inLineScan.hasNextLine()) {
        readLinesFromFile.add(inLineScan.nextLine());
      }
    } catch (IOException e) {
      e.toString();
 //     e.printStackTrace();
    }
    return readLinesFromFile;
  }

  public List<String> readLinesUnformatted(String pathNameInput) throws IOException{
    this.pathNameInput = pathNameInput;
    readLinesFromFile = readLinesFromFile(pathNameInput);
    return readLinesFromFile;
  }

  public void writeLinesToFile(List<String> inputList, String pathNameOutput)throws IOException {
    this.pathNameOutput = pathNameOutput;
    this.inputList = inputList;
    writeProvidedLinesToFile(inputList, pathNameOutput);
  }

  private void writeProvidedLinesToFile(List<String> inputList, String pathNameOutput) throws IOException{
    try (FileWriter myFileWriter = new FileWriter(pathNameOutput)) {
      for (String inputString : inputList) {
        myFileWriter.write(inputString);
        myFileWriter.write(System.lineSeparator());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
