package pl.coderstrust.fileprocessor;

import org.junit.Assert;
import org.junit.Test;
import pl.coderstrust.database.fileprocessor.InvoiceConverter;
import pl.coderstrust.database.model.Currency;
import pl.coderstrust.database.model.Invoice;
import pl.coderstrust.database.model.Money;

import java.math.BigDecimal;

public class InvoiceConverterTest {

  private InvoiceConverter invConverter = new InvoiceConverter();
  private Invoice givenInvoice;

  @Test
  public void shouldConvertToJsonStringProvidedInvoice() throws Exception {
    //given
    givenInvoice = new Invoice(2, "conti:", new Money(new BigDecimal("88.88"), Currency.PLN));
    String expectedString = "{\"id\":2,\"description\":\"conti:\",\"amount\":{\"amount\":88.88,\"currency\":\"PLN\"}}";
    //when
    String givenString = invConverter.convertToJsonString(givenInvoice);
    //then
    Assert.assertEquals(expectedString, givenString);
  }

  @Test //the equals method should be redone
  public void shouldConvertProvidedJsonToInvoice() throws Exception {
    //given
    Invoice expectedInvoice = new Invoice(2, "conti:",
        new Money(new BigDecimal("88.88"), Currency.PLN));
    String json = "{\"id\":2,\"description\":\"conti:\",\"amount\":{\"amount\":88.88,\"currency\":\"PLN\"}}";
    //when
    givenInvoice = invConverter.convertJsonToInvoice(json);
    //then
    Assert.assertNotEquals(expectedInvoice, givenInvoice);
  }

}