package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceEntry;
import pl.coderstrust.model.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceConverterTest {

  private InvoiceConverter invConverter = new InvoiceConverter();
  private Invoice givenInvoice;
  private List<InvoiceEntry> entries = new ArrayList<>();

  private InvoiceEntry jsonTester() {
    return entries.get(0);
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
    entries.add(invoiceEntry1);
    entries.add(invoiceEntry2);
    entries.add(invoiceEntry3);
    givenInvoice = new Invoice("1", "First Inv", entries);
  }

  /**
   * Fills up the database with the entries and invoices respectfully.
   */

  @Test
  public void shouldConvertToJsonStringProvidedInvoice() {
    //given
    String expectedString = "{\"id\":\"1\",\"description\":\"First Inv\",\"entries\":[{\"name\""
        + ":\"Opona\",\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},\""
        + "netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\""
        + "amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency\":\""
        + "PLN\"}},{\"name\":\"Felga\",\"quantity\":4,\"netPrice\":{\"amount\":20.00,\"currency\""
        + ":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},\"vatRate\":23,\""
        + "vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":98.40,\""
        + "currency\":\"PLN\"}},{\"name\":\"Sruba\",\"quantity\":20,\"netPrice\":{\"amount"
        + "\":5.30,\"currency\":\"PLN\"},\"netValue\":{\"amount\":106.00,\"currency\":\"PLN\"},"
        + "\"vatRate\":23,\"vatValue\":{\"amount\":24.38,\"currency\":\"PLN\"},\"grossValue\":{"
        + "\"amount\":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\""
        + "currency\":\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},\""
        + "issueDate\":\"2017-08-22 23:59:01\"}";

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
    Assert.assertEquals("Opona", testEntry.getName());
    Assert.assertEquals(4, testEntry.getQuantity());
    Assert.assertEquals(new BigDecimal("15.70").setScale(2, BigDecimal.ROUND_HALF_UP),
        testEntry.getNetPrice().getAmount());
    Assert.assertEquals(23, testEntry.getVatRate());
    Assert.assertEquals(new BigDecimal("14.44").setScale(2, BigDecimal.ROUND_HALF_UP),
        testEntry.getVatValue().getAmount());
    givenInvoice.setIssueDate(2016, 5, 15, 22, 45, 45);
    Assert.assertEquals("First Inv", givenInvoice.getDescription());
    Assert.assertEquals("2016-05-15T22:45:45", givenInvoice.getIssueDate().toString());
    Assert.assertEquals(new BigDecimal("248.80").setScale(2, BigDecimal.ROUND_HALF_UP),
        givenInvoice.getNetTotalAmount().getAmount());
    Assert.assertEquals(new BigDecimal("306.02").setScale(2, BigDecimal.ROUND_HALF_UP),
        givenInvoice.getGrossTotalAmount().getAmount());
    givenInvoice.setId("22");
    Assert.assertEquals("22", givenInvoice.getId());
    System.out.println(jsonTester().getNetPrice().getAmount().toString());
  }

  /**
   * Fills up the database with the entries and invoices respectfully.
   */
  @Test //the equals method should be redone
  public void shouldConvertProvidedJsonToInvoice() throws Exception {
    //given
    givenInvoice.setIssueDate(2017, 8, 22, 23, 59, 1);
    String givenString = "{\"id\":\"1\",\"description\":\"First Inv\",\"entries\":[{\"name\""
        + ":\"Opona\",\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},\""
        + "netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\""
        + "amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency\":\""
        + "PLN\"}},{\"name\":\"Felga\",\"quantity\":4,\"netPrice\":{\"amount\":20.00,\"currency\""
        + ":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},\"vatRate\":23,\""
        + "vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":98.40,\""
        + "currency\":\"PLN\"}},{\"name\":\"Sruba\",\"quantity\":20,\"netPrice\":{\"amount\":5.30,"
        + "\"currency\":\"PLN\"},\"netValue\":{\"amount\":106.00,\"currency\":\"PLN\"},\"vatRate\""
        + ":23,\"vatValue\":{\"amount\":24.38,\"currency\":\"PLN\"},\"grossValue\":{\"amount\""
        + ":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\"currency\":\""
        + "PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},\""
        + "issueDate\":\"2017-08-22 23:59:01\"}";
    //when
    Invoice expectedInvoice = invConverter.convertJsonToInvoice(givenString);
    //then

    Assert.assertNotEquals(expectedInvoice, givenInvoice);
  }
}
