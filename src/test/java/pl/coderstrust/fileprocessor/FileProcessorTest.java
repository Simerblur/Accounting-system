package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Test;
import pl.coderstrust.logic.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileProcessorTest {

  private FileProcessor processor = new FileProcessor();

  /**
   * Test sample Javadoc.
   */
  @Test
  public void shouldReadUnformattedLinesFromTestInputFileToList() throws IOException {
    //given
    List<String> listFromFile;
    List<String> expectedList = new ArrayList<>();
    //when
    expectedList.add("{\"invoiceId\":0,\"name\":\"Default Name\",\"description\":"
        + "\"default description\",\"buyer\":{\"name\":null,\"address1\":null,\"address2"
        + "\":null,\"zip\":null,\"vatId\":null,\"accountNumber\":null},\"seller\":{\"name"
        + "\":null,\"address1\":null,\"address2\":null,\"zip\":null,\"vatId\":null,"
        + "\"accountNumber\":null},\"entries\":[{\"entryName\":\"Opona\",\"entryId\":1,"
        + "\"entryQuantity\":4,\"entryNetPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},"
        + "\"entryNetValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"entryVatRate\":23,"
        + "\"entryVatValue\":{\"amount\":14.44,\"currency\":\"PLN\"},\"entryGrossValue\":{"
        + "\"amount\":77.24,\"currency\":\"PLN\"}},{\"entryName\":\"Felga\",\"entryId\":2,"
        + "\"entryQuantity\":4,\"entryNetPrice\":{\"amount\":20.00,\"currency\":\"PLN\"},"
        + "\"entryNetValue\":{\"amount\":80.00,\"currency\":\"PLN\"},\"entryVatRate\":23,"
        + "\"entryVatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"entryGrossValue\":{"
        + "\"amount\":98.40,\"currency\":\"PLN\"}},{\"entryName\":\"Sruba\",\"entryId\":3,"
        + "\"entryQuantity\":20,\"entryNetPrice\":{\"amount\":5.30,\"currency\":\"PLN\"},"
        + "\"entryNetValue\":{\"amount\":106.00,\"currency\":\"PLN\"},\"entryVatRate\":23,"
        + "\"entryVatValue\":{\"amount\":24.38,\"currency\":\"PLN\"},\"entryGrossValue\":{"
        + "\"amount\":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,"
        + "\"currency\":\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},"
        + "\"issueDate\":\"2017-08-22 23:59:01\"}");
    listFromFile = processor
        .readInvoicesFromFile("src/test/resources/testFileInput");
    //then
    Assert.assertNotNull(listFromFile);
    Assert.assertEquals(expectedList.get(0), listFromFile.get(0));
  }

  /**
   * Test sample Javadoc.
   */
  @Test
  public void shouldWriteTheProvidedListToTestFileOutput() throws IOException {
    //given
    final List<String> gotFromFile;
    List<String> listToBeWritten = new ArrayList<>();
    File outFileBefore = new File("src/test/resources/testFileOutput.txt");
    final Long lengthBefore = outFileBefore.length();
    //when
    listToBeWritten.add("283 + 293 + 307 + 311 + 313 + 317 + 331 + 337 + 347 + 349 = 3188");
    listToBeWritten.add("This is the test message to be written by FileProcessor");
    processor.appendInvoiceToFile(listToBeWritten.get(0),
        "src/test/resources/testFileOutput.txt");
    processor.appendInvoiceToFile(listToBeWritten.get(0),
        "src/test/resources/testFileOutput.txt");
    gotFromFile = processor
        .readInvoicesFromFile("src/test/resources/testFileOutput.txt");
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
    //given
    List<String> testList;
    //when
    testList = processor.readInvoicesFromFile(
        "src/test/resources/pl.coderstrust/WrongInvoiceBook.txt");
    //then
    Assert.assertEquals("File not found", testList.get(0));
  }
}