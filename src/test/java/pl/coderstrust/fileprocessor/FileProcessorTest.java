package pl.coderstrust.fileprocessor;

import static junit.framework.TestCase.fail;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessorTest {

  private FileProcessor fileProcessor = new FileProcessor();

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldReadUnformattedLinesFromTestInputFileToList() throws IOException {
    //given
    List<String> listFromFile;
    List<String> expectedList = new ArrayList<>();
    //when
    expectedList.add("{\"id\":\"1\",\"description\":\"First Inv\",\"entries\":[{\"name\":"
        + "\"Opona\",\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},"
        + "\"netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{"
        + "\"amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency"
        + "\":\"PLN\"}},{\"name\":\"Felga\",\"quantity\":4,\"netPrice\":{\"amount\":20.00,"
        + "\"currency\":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},"
        + "\"vatRate\":23,\"vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue\":{"
        + "\"amount\":98.40,\"currency\":\"PLN\"}},{\"name\":\"Sruba\",\"quantity\":20,\"netPrice"
        + "\":{\"amount\":5.30,\"currency\":\"PLN\"},\"netValue\":{\"amount\":106.00,\"currency\":"
        + "\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":24.38,\"currency\":\"PLN\"},"
        + "\"grossValue\":{\"amount\":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{"
        + "\"amount\":248.80,\"currency\":\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,"
        + "\"currency\":\"PLN\"},\"issueDate\":\"2017-09-19 14:25:01\"}");
    expectedList.add("{\"id\":\"1\",\"description\":\"First Inv\",\"entries\":[{\"name\":"
        + "\"Opona\",\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},"
        + "\"netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{"
        + "\"amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency\":"
        + "\"PLN\"}},{\"name\":\"Felga\",\"quantity\":4,\"netPrice\":{\"amount\":20.00,\"currency"
        + "\":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},\"vatRate\":23,"
        + "\"vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":98.40,"
        + "\"currency\":\"PLN\"}},{\"name\":\"Sruba\",\"quantity\":20,\"netPrice\":{\"amount"
        + "\":5.30,\"currency\":\"PLN\"},\"netValue\":{\"amount\":106.00,\"currency\":\"PLN\"},"
        + "\"vatRate\":23,\"vatValue\":{\"amount\":24.38,\"currency\":\"PLN\"},\"grossValue\":{"
        + "\"amount\":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,"
        + "\"currency\":\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},"
        + "\"issueDate\":\"2017-09-19 14:25:01\"}");
    listFromFile = fileProcessor
        .readInvoicesFromFile("src/test/resources/pl.coderstrust/testFileInput.txt");
    //then
    Assert.assertNotNull(listFromFile);
    Assert.assertEquals(expectedList.get(0), listFromFile.get(0));
    Assert.assertEquals(expectedList.get(1), listFromFile.get(1));
  }

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldWriteTheProvidedListToTestFileOutput() throws IOException {
    //given
    final List<String> gotFromFile;
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
    gotFromFile = fileProcessor
        .readInvoicesFromFile("src/test/resources/pl.coderstrust/testFileOutput.txt");
    final Long lengthAfter = outFileBefore.length();
    //then
    Assert.assertNotNull(outFileBefore);
    Assert.assertNotNull(gotFromFile);
    Assert.assertNotEquals(lengthBefore, lengthAfter);
  }

  /**
   * Test file not found exception.
   */
  @Test
  public void shouldTestExceptionsHandlingWrongFilePathRead() {
    try {
      //given

      //when
      List<String> testList = fileProcessor
          .readInvoicesFromFile("src/test/resources/pl.coderstrust/WrongInvoiceBook.txt");
      testList.size();
      //then
    } catch (Exception e) {
      Assert.assertThat(e.toString(), containsString("FileNotFoundException"));

    }
  }
}