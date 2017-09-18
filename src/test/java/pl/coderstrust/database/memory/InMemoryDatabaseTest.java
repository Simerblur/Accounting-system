package pl.coderstrust.database.memory;

import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Currency;
import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.InvoiceBook;
import pl.coderstrust.model.Money;

import java.math.BigDecimal;

public class InMemoryDatabaseTest extends AbstractDatabaseTest {

  private Database memDb = new InMemoryDatabase();
  private InvoiceBook invoiceBook = new InvoiceBook(memDb);


  @Override
  protected Database getDatabase() {
    return memDb;
  }

  @Override
  public void saveInvoice() throws Exception {
    Invoice first = new Invoice(1, "Starter:", new Money(new BigDecimal("234.45"), Currency.PLN));
    Invoice second = new Invoice(2, "conti:", new Money(new BigDecimal("88.88"), Currency.PLN));
    first.setAmount(new Money(new BigDecimal("555.55"), Currency.PLN));
    second.setAmount(new Money(new BigDecimal("777.55"), Currency.PLN));
  }

  @Override
  public void getInvoices() throws Exception {
   invoiceBook.getInvoices();
  }
}