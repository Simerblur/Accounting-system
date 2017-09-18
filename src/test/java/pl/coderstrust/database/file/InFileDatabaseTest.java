package pl.coderstrust.database.file;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.database.model.Currency;
import pl.coderstrust.database.model.Invoice;
import pl.coderstrust.database.model.InvoiceBook;
import pl.coderstrust.database.model.InvoiceEntry;
import pl.coderstrust.database.model.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InFileDatabaseTest extends AbstractDatabaseTest {

  private Database db;
  private Invoice invoice1;
  private List<InvoiceEntry> entryList = new ArrayList<>();

  @Before
  public void fillDb() {
    final InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    entryList.add(invoiceEntry1);
    entryList.add(invoiceEntry2);
    entryList.add(invoiceEntry3);
    invoice1 = new Invoice("1", "First Inv", entryList);
  }

  @Override
  protected Database getDatabase() {
    return db;
  }

  @Test
  public void shouldSaveInvoice() {
    //given
    db = new InFileDatabase("src/main/resources/pl.coderstrust/testFileOutput.txt");
    //when
    db.saveInvoice(invoice1);
    //then
    Assert.assertNotNull(db);
    Assert.assertEquals("1", db.getInvoices().get(0).getId());
    Assert.assertEquals("First Inv", db.getInvoices().get(0).getDescription());
  }

  @Test
  public void shouldGetInvoices() {
  }
}
