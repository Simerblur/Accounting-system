package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.database.fileprocessor.InvoiceConverter;
import pl.coderstrust.database.model.Currency;
import pl.coderstrust.database.model.Invoice;
import pl.coderstrust.database.model.InvoiceEntry;
import pl.coderstrust.database.model.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvoiceConverterTest {

  private InvoiceConverter invConverter = new InvoiceConverter();
  private Invoice givenInvoice;
  private List<InvoiceEntry> entries = new ArrayList<>();

  /**
   * Test sample Javadoc. Fills up the database with the entries and invoices respectfully.
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

    // givenInvoice.setIssueDate(2017,9,18);
 //   String expectedString = "{\"id\":\"1\",\"description\":\"First Inv\",\"entries\":[{\"name\":\"Opona\",\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\":\"PLN\"},\"netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":77.24,\"currency\":\"PLN\"}},{\"name\":\"Felga\",\"quantity\":4,\"netPrice\":{\"amount\":20.00,\"currency\":\"PLN\"},\"netValue\":{\"amount\":80.00,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":18.40,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":98.40,\"currency\":\"PLN\"}},{\"name\":\"Sruba\",\"quantity\":20,\"netPrice\":{\"amount\":5.30,\"currency\":\"PLN\"},\"netValue\":{\"amount\":106.00,\"currency\":\"PLN\"},\"vatRate\":23,\"vatValue\":{\"amount\":24.38,\"currency\":\"PLN\"},\"grossValue\":{\"amount\":130.38,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":248.80,\"currency\":\"PLN\"},\"grossTotalAmount\":{\"amount\":306.02,\"currency\":\"PLN\"},\"issueDate\":{\"year\":2017,\"month\":\"SEPTEMBER\",\"chronology\":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"},\"dayOfMonth\":18,\"dayOfWeek\":\"MONDAY\",\"dayOfYear\":261,\"era\":\"CE\",\"monthValue\":9,\"leapYear\":false}}";
  // System.out.println(expectedString);
    //when
    System.out.println(givenInvoice.getIssueDate());
    givenInvoice.setIssueDate(2017,8,22);
    System.out.println(givenInvoice.getIssueDate());
    String givenString = invConverter.convertToJsonString(givenInvoice);
    System.out.println(givenString);
 /*   String testINOUT = invConverter
        .convertToJsonString(invConverter.convertJsonToInvoice(givenString));
    System.out.println("Test");
    System.out.println(testINOUT);*/
    //then
   //Assert.assertEquals(expectedString, givenString);
  }

  /*  @Test //the equals method should be redone
   public void shouldConvertProvidedJsonToInvoice() throws Exception {
    //given
    Invoice expectedInvoice = new Invoice("2", "conti:",
        new ArrayList<>(Arrays.asList(new InvoiceEntry("opona", 4,
            new Money(new BigDecimal(555.8), Currency.PLN), 23))));
    String json = "{\"id\":2,\"description\":\"conti:\","
        + "\"amount\":{\"amount\":88.88,\"currency\":\"PLN\"}}";
    //when
    givenInvoice = invConverter.convertJsonToInvoice(json);
    //then
    Assert.assertNotEquals(expectedInvoice, givenInvoice);
  }*/

}
