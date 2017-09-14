package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Test;
import pl.coderstrust.database.fileprocessor.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessorTest {

  private FileProcessor fileProcessor = new FileProcessor();

  @Test
  public void shouldReadUnformattedLinesFromTestInputFileToList() throws IOException {
    //given
    List<String> listFromFile;
    List<String> expectedList = new ArrayList<>();
    //when
    expectedList.add("283 + 293 + 307 + 311 + 313 + 317 + 331 + 337 + 347 + 349 = 3188");
    expectedList.add("This is the test message to be readed by FileProcessor");
    listFromFile = fileProcessor
        .readInvoicesFromFile("src/test/resources/pl.coderstrust/testFileInput.txt");
    //then
    Assert.assertNotNull(listFromFile);
    Assert.assertEquals(expectedList.size(), listFromFile.size());
    Assert.assertEquals(expectedList.get(0), listFromFile.get(0));
    Assert.assertEquals(expectedList.get(1), listFromFile.get(1));
  }

  @Test
  public void shouldWriteTheProvidedListToTestFileOutput() throws IOException {
    //given
    List<String> gotFromFile;
    List<String> listToBeWritten = new ArrayList<>();
    File outFileBefore = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    Long lengthBefore = outFileBefore.length();
    //when
    listToBeWritten.add("283 + 293 + 307 + 311 + 313 + 317 + 331 + 337 + 347 + 349 = 3188");
    listToBeWritten.add("This is the test message to be written by FileProcessor");
    fileProcessor.appendInvoiceToFile(listToBeWritten.get(0),
        "src/test/resources/pl.coderstrust/testFileOutput.txt");
    fileProcessor.appendInvoiceToFile(listToBeWritten.get(0),
        "src/test/resources/pl.coderstrust/testFileOutput.txt");
    gotFromFile = fileProcessor
        .readInvoicesFromFile("src/test/resources/pl.coderstrust/testFileOutput.txt");
    Long lengthAfter = outFileBefore.length();
    //then
    Assert.assertNotNull(outFileBefore);
    Assert.assertNotNull(gotFromFile);
    Assert.assertNotEquals(listToBeWritten.size(), gotFromFile.size());
    Assert.assertNotEquals(lengthBefore, lengthAfter);
  }


}