package pl.coderstrust.fileprocessor;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
    List<String> listFromFile = new ArrayList<>();
    List<String> expectedList = new ArrayList<>();
    //when
    expectedList.add("283 + 293 + 307 + 311 + 313 + 317 + 331 + 337 + 347 + 349 = 3188");
    expectedList.add("This is the test message to be readed by FileProcessor");
    listFromFile = fileProcessor
        .readLinesUnformatted("src/test/resources/pl.coderstrust/testFileInput.txt");
    //then
    Assert.assertNotNull(listFromFile);
    Assert.assertEquals(expectedList.size(), listFromFile.size());
    Assert.assertEquals(expectedList.get(0), listFromFile.get(0));
    Assert.assertEquals(expectedList.get(1), listFromFile.get(1));
  }

  @Test
  public void shouldWriteTheProvidedListToTestFileOutput() throws IOException {
    //given
    List<String> gotFromFile = new ArrayList<>();
    List<String> listToBeWritten = new ArrayList<>();
    //when
    listToBeWritten.add("283 + 293 + 307 + 311 + 313 + 317 + 331 + 337 + 347 + 349 = 3188");
    listToBeWritten.add("This is the test message to be written by FileProcessor");
    fileProcessor
        .writeLinesToFile(listToBeWritten, "src/test/resources/pl.coderstrust/testFileOutput.txt");
    gotFromFile = fileProcessor
        .readLinesUnformatted("src/test/resources/pl.coderstrust/testFileOutput.txt");
    File outFile = new File("src/test/resources/pl.coderstrust/testFileOutput.txt");
    //then
    Assert.assertNotNull(outFile);
    Assert.assertNotNull(gotFromFile);
    Assert.assertEquals(listToBeWritten.size(), gotFromFile.size());
    Assert.assertEquals(listToBeWritten.get(0), gotFromFile.get(0));
    Assert.assertEquals(listToBeWritten.get(1), gotFromFile.get(1));
  }


}