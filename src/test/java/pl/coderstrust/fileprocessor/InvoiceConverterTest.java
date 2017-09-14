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
  private Invoice expectedInvoice;
  private Invoice givenInvoice;

  @Test
  public void shouldConvertToJsonStringProvidedInvoice() throws Exception {
  }

  @Test
  public void shouldConvertProvidedJsonToInvoice() throws Exception {
    //given
    expectedInvoice = new Invoice(2, "conti:", new Money(new BigDecimal("88.88"), Currency.PLN));
    String json = "{\"id\":2,\"description\":\"conti:\",\"amount\":{\"amount\":88.88,\"currency\":\"PLN\"}}";
    //when
    givenInvoice = invConverter.convertJsonToInvoice(json);
    //then
    Assert.assertEquals(expectedInvoice,givenInvoice);
  }

}