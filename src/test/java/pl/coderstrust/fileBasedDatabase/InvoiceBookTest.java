package pl.coderstrust.fileBasedDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.fileBasedDatabase.Currency;
import pl.coderstrust.database.fileBasedDatabase.Invoice;
import pl.coderstrust.database.fileBasedDatabase.InvoiceBook;
import pl.coderstrust.database.fileBasedDatabase.Money;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  @Mock
  private Database db;

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldReturnEmptyListOfInvoicesIfNothingAdded() {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(db);

    // when
    List<Invoice> invoices = invoiceBook.getInvoices();

    //then
    assertNotNull("Invoices should not be null", invoices);
    assertEquals(0, invoices.size());
  }

  /**
   * Test sample Javadoc.
   */

  @Test
  public void shouldReturnSingleInvoiceWhenItWasAddedToBook() {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(db);

    Invoice invoice = new Invoice(1, "computer", new Money(BigDecimal.TEN, Currency.PLN));
    when(db.getInvoices()).thenReturn(Collections.singletonList(invoice));

    // when
    invoiceBook.addInvoice(invoice);
    List<Invoice> invoices = invoiceBook.getInvoices();

    //then
    assertNotNull("Invoices should not be null", invoices);
    assertEquals(1, invoices.size());
    assertEquals(invoice, invoices.get(0));
  }
}