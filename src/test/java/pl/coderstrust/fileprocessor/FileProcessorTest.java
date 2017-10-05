package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Test;

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
    expectedList.add("{\"invoiceId\":1,\"name\":\"1/10/2017\",\"description\":"
        + "\"default description\",\"buyer\":{\"name\":\"Zosia\",\"address1\":null,"
        + "\"address2\":null,\"zip\":null,\"vatId\":\"PL9999999\",\"accountNumber\":null},"
        + "\"seller\":{\"name\":\"Kasia\",\"address1\":null,\"address2\":null,\"zip\":null,"
        + "\"vatId\":\"PL12345678\",\"accountNumber\":null},\"entries\":[{\"name\":\"Opona\","
        + "\"entryId\":1,\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},"
        + "\"netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{"
        + "\"amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency"
        + "\":\"PLN\"}},{\"name\":\"Felga\",\"entryId\":2,\"quantity\":4,\"netPrice\":{\"amount"
        + "\":20.00,\"currency\":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},"
        + "\"vatRate\":23,\"vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue\":{"
        + "\"amount\":98.40,\"currency\":\"PLN\"}},{\"name\":\"Sruba\",\"entryId\":3,\"quantity"
        + "\":20,\"netPrice\":{\"amount\":5.30,\"currency\":\"PLN\"},\"netValue\":{\"amount"
        + "\":106.00,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":24.38,"
        + "\"currency\":\"PLN\"},\"grossValue\":{\"amount\":130.38,\"currency\":\"PLN\"}}],"
        + "\"netTotalAmount\":{\"amount\":248.80,\"currency\":\"PLN\"},\"grossTotalAmount\":{"
        + "\"amount\":306.02,\"currency\":\"PLN\"},\"issueDate\":\"2017-10-05 15:17:15\"}");
    listFromFile = processor
        .readInvoicesFromFile("src/test/resources/testFileOutputIB.txt");
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