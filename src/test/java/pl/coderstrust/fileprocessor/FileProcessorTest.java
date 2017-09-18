package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessorTest {

  private FileProcessor fileProcessor = new FileProcessor();

  /**
   * Tests if the strings from file are being readed properly to List.
   */
  @Test
  public void shouldReadUnformattedLinesFromTestInputFileToList() throws IOException {
    //given
    List<String> listFromFile;
    List<String> expectedList = new ArrayList<>();
    //when
    expectedList.add(
        "{\"id\":2,\"description\":\"conti:\",\"amount\":{\"amount\":88.88,\"currency\":\"PLN\"}}");
    expectedList.add(
        "{\"id\":3,\"description\":\"New 3:\",\""
            + "amount\":{\"amount\":333.33,\"currency\":\"PLN\"}}");
    listFromFile = fileProcessor
        .readInvoicesFromFile("src/test/resources/pl.coderstrust/testFileInput.txt");
    //then
    Assert.assertNotNull(listFromFile);
    Assert.assertEquals(expectedList.get(0), listFromFile.get(0));
    Assert.assertEquals(expectedList.get(1), listFromFile.get(1));
  }

  /**
   * Tests if the method writes provided list to the output file..
   */

  @Test
  public void shouldWriteTheProvidedListToTestFileOutput() throws IOException {
    //given
    List<String> listToBeWritten = new ArrayList<>();
    File outFileBefore = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    final Long lengthBefore = outFileBefore.length();
    //when
    listToBeWritten.add("283 + 293 + 307 + 311 + 313 + 317 + 331 + 337 + 347 + 349 = 3188");
    listToBeWritten.add("This is the test message to be written by FileProcessor");
    fileProcessor.appendInvoiceToFile(listToBeWritten.get(0),
        "src/test/resources/pl.coderstrust/testFileOutput.txt");
    fileProcessor.appendInvoiceToFile(listToBeWritten.get(0),
        "src/test/resources/pl.coderstrust/testFileOutput.txt");
    List<String> gotFromFile = fileProcessor
        .readInvoicesFromFile("src/test/resources/pl.coderstrust/testFileOutput.txt");
    Long lengthAfter = outFileBefore.length();
    //then
    Assert.assertNotNull(outFileBefore);
    Assert.assertNotNull(gotFromFile);
    Assert.assertNotEquals(listToBeWritten.size(), gotFromFile.size());
    Assert.assertNotEquals(lengthBefore, lengthAfter);
  }
}