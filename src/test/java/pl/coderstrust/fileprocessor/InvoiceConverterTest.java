package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Test;
import pl.coderstrust.database.fileprocessor.InvoiceConverter;
import pl.coderstrust.database.model.Currency;
import pl.coderstrust.database.model.Invoice;
import pl.coderstrust.database.model.InvoiceEntry;
import pl.coderstrust.database.model.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class InvoiceConverterTest {

  private InvoiceConverter invConverter = new InvoiceConverter();
  Invoice givenInvoice;

  /**
   * Test sample Javadoc. Fills up the database with the entries and invoices respectfully.
   */

  @Test
  public void shouldConvertToJsonStringProvidedInvoice() throws Exception {
    //given
    givenInvoice = new Invoice("2", "SecondEntryTest",
        new ArrayList<>(Arrays.asList(new InvoiceEntry("Opona", 4,
            new Money(new BigDecimal(15.70).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN),
            23))));
    //   givenInvoice.setIssueDate(2017,9,18);
    String expectedString = "{\"id\":\"2\",\"description\":\"SecondEntryTest\",\"entries\""
        + ":[{\"name\":\"Opona\",\"quantity\":4,\"netPrice\":{\"amount\":15.70,\"currency\""
        + ":\"PLN\"},\"netValue\":{\"amount\":62.80,\"currency\":\"PLN\"},\"vatRate\""
        + ":23,\"vatValue\":{\"amount\":14.44,\"currency\":\"PLN\"},\"grossValue\":{\"amount\""
        + ":77.24,\"currency\":\"PLN\"}}],\"netTotalAmount\":{\"amount\":62.80,\"currency\":\""
        + "PLN\"},\"grossTotalAmount\":{\"amount\":77.24,\"currency\":\"PLN\"},\"issueDate\":{\""
        + "year\":2017,\"month\":\"SEPTEMBER\",\"era\":\"CE\",\"dayOfMonth\":18,\"dayOfWeek\":\""
        + "MONDAY\",\"dayOfYear\":261,\"leapYear\":false,\"monthValue\":9,\"chronology\""
        + ":{\"id\":\"ISO\",\"calendarType\":\"iso8601\"}}}";
    //when
    String givenString = invConverter.convertToJsonString(givenInvoice);
    //then
    Assert.assertEquals(expectedString, givenString);
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
