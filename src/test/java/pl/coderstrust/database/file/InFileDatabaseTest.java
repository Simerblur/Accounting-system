package pl.coderstrust.database.file;

import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.model.Currency;
import pl.coderstrust.database.model.Invoice;
import pl.coderstrust.database.model.InvoiceBook;
import pl.coderstrust.database.model.Money;

import java.math.BigDecimal;

public class InFileDatabaseTest extends AbstractDatabaseTest {

  private Database db = new InFileDatabase("src/main/resources/pl.coderstrust/InvoiceBook.txt");
  private InFileDatabase fileDb = new InFileDatabase(
      "src/main/resources/pl.coderstrust/InvoiceBook.txt");
  private InvoiceBook invoiceBook = new InvoiceBook(db);

  @Override
  protected Database getDatabase() {
    return fileDb;
  }

  @Override
  public void saveInvoice() throws Exception {
    Invoice first = new Invoice(1, "Starter:", new Money(new BigDecimal("234.45"), Currency.PLN));
    Invoice second = new Invoice(2, "conti:", new Money(new BigDecimal("88.88"), Currency.PLN));
    first.setAmount(new Money(new BigDecimal("555.55"), Currency.PLN));
    second.setAmount(new Money(new BigDecimal("777.55"), Currency.PLN));
    db.saveInvoice(second);
    fileDb.getInvoiceIndex(2);
  }

  @Override
  public void getInvoices() throws Exception {

    Invoice first = invoiceBook.getInvoices().get(0);
    Invoice second = invoiceBook.getInvoices().get(1);
    first.setAmount(new Money(new BigDecimal("555.55"), Currency.PLN));
    second.setAmount(new Money(new BigDecimal("777.55"), Currency.PLN));
  }
}