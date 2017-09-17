package pl.coderstrust.database.file;

import org.junit.Test;
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


  @Test
  public void shouldSaveInvoice() {
    // given
    Database db = getDatabase();
    System.out.println(db.toString());
  }

  @Test
  public void shouldGetInvoices() {
  }
}
