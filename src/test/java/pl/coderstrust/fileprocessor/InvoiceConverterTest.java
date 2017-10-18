package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.logic.FileProcessor;
import pl.coderstrust.logic.InvoiceConverter;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;

public class InvoiceConverterTest {

  private InvoiceConverter invConverter = new InvoiceConverter();
  private Invoice givenInvoice;

  private InvoiceEntry jsonTester() {
    return givenInvoice.getEntries().get(0);
  }

  /**
   * Provides the entries and invoices respectfully.
   */
  @Before
  public void fillDb() {
    final InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    givenInvoice = new Invoice(new Seller(), new Buyer());
    givenInvoice.addEntry(invoiceEntry1);
    givenInvoice.addEntry(invoiceEntry2);
    givenInvoice.addEntry(invoiceEntry3);
  }

  /**
   * Fills up the database with the entries and invoices respectfully.
   */
  @Test
  public void shouldConvertToJsonStringProvidedInvoice() {
    //given
    FileProcessor fileProcessor = new FileProcessor();
    String expectedString = fileProcessor
        .readInvoicesFromFile("src/test/resources/testFileInput").get(0);

    //when
    givenInvoice.setIssueDate(2017, 8, 22, 23, 59, 1);
    String givenString = invConverter.convertToJsonString(givenInvoice);

    //then
    Assert.assertEquals(expectedString, givenString);
  }

  /**
   * Test of getters and setters. Temporary tests to meet checkstyle and maven conditions.
   */
  @Test
  public void shouldTestSettersAndGetters() {
    final InvoiceEntry testEntry;
    testEntry = givenInvoice.getEntries().get(0);
    Assert.assertEquals("Opona", testEntry.getEntryName());
    Assert.assertEquals(4, testEntry.getEntryQuantity());
    Assert.assertEquals(new BigDecimal("15.70").setScale(2, BigDecimal.ROUND_HALF_UP),
        testEntry.getEntryNetPrice().getAmount());
    Assert.assertEquals(23, testEntry.getEntryVatRate());
    Assert.assertEquals(new BigDecimal("14.44").setScale(2, BigDecimal.ROUND_HALF_UP),
        testEntry.getEntryVatValue().getAmount());
    givenInvoice.setIssueDate(2016, 5, 15, 22, 45, 45);
    Assert.assertEquals("default description", givenInvoice.getDescription());
    Assert.assertEquals("2016-05-15T22:45:45", givenInvoice.getIssueDate().toString());
    Assert.assertEquals(new BigDecimal("248.80").setScale(2, BigDecimal.ROUND_HALF_UP),
        givenInvoice.getNetTotalAmount().getAmount());
    Assert.assertEquals(new BigDecimal("306.02").setScale(2, BigDecimal.ROUND_HALF_UP),
        givenInvoice.getGrossTotalAmount().getAmount());
    givenInvoice.setInvoiceId(22);
    Assert.assertEquals(22, givenInvoice.getInvoiceId());
    System.out.println(jsonTester().getEntryNetPrice().getAmount().toString());
  }

  /**
   * Fills up the database with the entries and invoices respectfully.
   */
  @Test
  public void shouldConvertProvidedJsonToInvoice() {
    //given
    givenInvoice.setIssueDate(2017, 8, 22, 23, 59, 1);
    FileProcessor fileProcessor = new FileProcessor();
    String givenString = fileProcessor
        .readInvoicesFromFile("src/test/resources/testFileInput").get(0);
    //when
    Invoice expectedInvoice = invConverter.convertJsonToInvoice(givenString);
    //then
    System.out.println(invConverter.convertToJsonString(givenInvoice));
    Assert.assertEquals(expectedInvoice.getName(), givenInvoice.getName());
    Assert.assertEquals(expectedInvoice.getDescription(), givenInvoice.getDescription());
    Assert.assertEquals(expectedInvoice.getIssueDate(), givenInvoice.getIssueDate());
    Assert.assertEquals(expectedInvoice.getEntries().get(0).getEntryNetPrice(),
        givenInvoice.getEntries().get(0).getEntryNetPrice());
  }

  /**
   * Exception handling test.
   */
  @Test
  public void shouldConvertWrongProvidedJsonToInvoice() {
    //given
    String givenString = "     ";
    //when
    Invoice testResultInvoice = invConverter.convertJsonToInvoice(givenString);
    //then
    Assert.assertEquals(null, testResultInvoice.getDescription());
  }

  /**
   * Exception handling test.
   */
  @Test
  public void shouldConvertWrongProvidedInvoiceToJson() {
    //given
    String givenString = "     ";
    //when
    Invoice testResultInvoice = invConverter.convertJsonToInvoice(givenString);
    //then
    Assert.assertEquals(null, testResultInvoice.getDescription());
  }
}
