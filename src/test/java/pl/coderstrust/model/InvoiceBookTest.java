package pl.coderstrust.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.coderstrust.database.Database;
import pl.coderstrust.database.file.InFileDatabase;
import pl.coderstrust.database.memory.InMemoryDatabase;
import pl.coderstrust.logic.InvoiceBook;
import pl.coderstrust.logic.InvoiceConverter;
import pl.coderstrust.model.counterparts.Buyer;
import pl.coderstrust.model.counterparts.MyCompanyBuy;
import pl.coderstrust.model.counterparts.MyCompanySell;
import pl.coderstrust.model.counterparts.Seller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class InvoiceBookTest {

  private Invoice givenInvoice;
  private Invoice givenInvoice2;
  private Invoice givenInvoice3;

  @Mock
  private Database db;

  @Before
  public void fillDb() {
    final InvoiceEntry invoiceEntry1 = new InvoiceEntry("Opona", 4,
        new Money(new BigDecimal(15.7).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry2 = new InvoiceEntry("Felga", 4,
        new Money(new BigDecimal(20).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    final InvoiceEntry invoiceEntry3 = new InvoiceEntry("Sruba", 20,
        new Money(new BigDecimal(5.3).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23);
    givenInvoice = new Invoice(new MyCompanySell(), new Buyer("Zosia", "PL9999999"));
    givenInvoice.addEntry(invoiceEntry1);
    givenInvoice.addEntry(invoiceEntry2);
    givenInvoice.addEntry(invoiceEntry3);
    //   givenInvoice.setCounterparts()
    givenInvoice2 = new Invoice(new MyCompanySell(),
        new Buyer("Jacek", "PL33333333"));
    givenInvoice2.addEntry(invoiceEntry1);
    givenInvoice2.addEntry(invoiceEntry2);
    givenInvoice2.addEntry(invoiceEntry3);
    givenInvoice3 = new Invoice(new Seller("Ania", "PL155567777"),
        new Buyer("Wacek", "PL888811111"));
    givenInvoice3.addEntry(invoiceEntry1);
    givenInvoice3.addEntry(invoiceEntry2);
    givenInvoice3.addEntry(invoiceEntry3);
  }

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

  @Test
  public void shouldReturnSingleInvoiceWhenItWasAddedToBook() {
    // given
    InvoiceBook invoiceBook = new InvoiceBook(db);
    when(db.getInvoices()).thenReturn(Collections.singletonList(givenInvoice));

    // when
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice2);
    List<Invoice> invoices = invoiceBook.getInvoices();

    //then
    assertNotNull("Invoices should not be null", invoices);
    assertEquals(1, invoices.size());
    assertEquals(givenInvoice, invoices.get(0));
  }

  @Test
  public void shouldTestEmptyInvoiceConstructor() {
    //given
    Invoice testJson = new Invoice();
    //then
    Assert.assertNotNull(testJson);
  }

  @Test
  public void shouldTestEmptyInvoiceEntryConstructor() {
    //given
    InvoiceEntry testJson = new InvoiceEntry();
    //then
    Assert.assertNotNull(testJson);
  }

  @Test
  public void shouldTestGettersAndSetters() {
    Database memDb = new InMemoryDatabase();
    InvoiceBook ib = new InvoiceBook(memDb);
    InvoiceConverter converter = new InvoiceConverter();
    final Invoice testInvoice = converter.convertJsonToInvoice(
        "{\"invoiceId\":2,\"name\":\"1/10/2017\",\"description\":\"default description"
            + "\",\"buyer\":{\"name\":\"Jacek\",\"address1\":null,\"address2\":null,\"zip\":null,"
            + "\"vatId\":\"PL33333333\",\"accountNumber\":null},\"seller\":{\"name\":"
            + "\"My Compnay name\",\"address1\":\"Building, street\",\"address2\":"
            + "\"Street, City\",\"zip\":\"00-333\",\"vatId\":\"PL9988555333\",\"accountNumber"
            + "\":\"12 0000 0000 0000 1111 1111\"},\"entries\":[{\"entryName\":\"Opona\","
            + "\"entryId\":1,\"entryQuantity\":4,\"entryNetPrice\":{\"amount\":15.70,\"currency"
            + "\":\"PLN\"},\"entryNetValue\":{\"amount\":62.80,\"currency\":\"PLN\"},"
            + "\"entryVatRate\":23,\"entryVatValue\":{\"amount\":14.44,\"currency\":\"PLN"
            + "\"},\"entryGrossValue\":{\"amount\":77.24,\"currency\":\"PLN\"}},{\"entryName"
            + "\":\"Felga\",\"entryId\":2,\"entryQuantity\":4,\"entryNetPrice\":{\"amount"
            + "\":20.00,\"currency\":\"PLN\"},\"entryNetValue\":{\"amount\":80.00,\"currency"
            + "\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{\"amount\":18.40,\"currency"
            + "\":\"PLN\"},\"entryGrossValue\":{\"amount\":98.40,\"currency\":\"PLN\"}},{"
            + "\"entryName\":\"Sruba\",\"entryId\":3,\"entryQuantity\":20,\"entryNetPrice\":{"
            + "\"amount\":5.30,\"currency\":\"PLN\"},\"entryNetValue\":{\"amount\":106.00,"
            + "\"currency\":\"PLN\"},\"entryVatRate\":23,\"entryVatValue\":{\"amount\":24.38,"
            + "\"currency\":\"PLN\"},\"entryGrossValue\":{\"amount\":130.38,\"currency\":\"PLN"
            + "\"}}],\"issueDate\":\"2017-10-17 21:49:35\",\"grossTotalAmount\":{\"amount"
            + "\":306.02,\"currency\":\"PLN\"},\"netTotalAmount\":{\"amount\":248.80,"
            + "\"currency\":\"PLN\"}}");
    ib.addInvoice(givenInvoice3);
    givenInvoice2.setName("1/10/2017");
    ib.addInvoice(givenInvoice);
    ib.addInvoice(new Invoice(new Seller("Tomek", "NL000333"), new MyCompanyBuy()));
    ib.addInvoice(new Invoice(new MyCompanySell(), new Buyer("Tomek", "NL000333")));
    for (int i = 0; i < ib.getInvoices().size(); i++) {
      System.out.println(
          ib.getInvoices().get(i).getInvoiceId() + " " + ib.getInvoices().get(i).getName());
    }
    Assert.assertEquals(givenInvoice2.getName(), testInvoice.getName());
  }

  @Test
  public void shouldReturnInvoicesFromRange() {
    InFileDatabase database = new InFileDatabase();
    database.setFilePath("src/test/resources/testFileOutputIB.txt");
    InvoiceBook invoiceBook = new InvoiceBook(database);
    List<Invoice> test = invoiceBook
        .getInvoicesByDateRange(LocalDateTime.of(2017, 9, 25, 11, 9, 36),
            LocalDateTime.of(2019, 12, 25, 11, 9, 37));
    System.out.println(test.get(0).getInvoiceId());
    for (Invoice iterator : test) {
      System.out.println(iterator.getInvoiceId() + " " + iterator.getIssueDate());
    }
    Assert.assertTrue(test.size() > 0);
  }

  @Test
  public void shouldAddInvoice() {
    //given
    db = new InMemoryDatabase();
    InvoiceBook invoiceBook = new InvoiceBook(db);
    //when
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice2);
    invoiceBook.addInvoice(givenInvoice3);
    givenInvoice2.addEntry(new InvoiceEntry("estowy wpis", 12,
        new Money(new BigDecimal(25.5).setScale(2, BigDecimal.ROUND_HALF_UP), Currency.PLN), 23));
    for (InvoiceEntry invoiceEntry : givenInvoice2.getEntries()) {
      System.out.println(
          "entry = " + invoiceEntry.getEntryId() + " entry net value " + invoiceEntry
              .getEntryNetValue().getAmount() + " entry gross amount = " + invoiceEntry
              .getEntryGrossValue().getAmount());
    }
    System.out.println(
        "Net total " + givenInvoice2.getNetTotalAmount().getAmount() + "Gross total "
            + givenInvoice2.getGrossTotalAmount().getAmount());
    LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    List<Invoice> testedRange = invoiceBook
        .getInvoicesByDateRange(LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0),
            LocalDateTime
                .of(now.getYear(), now.getMonth(), now.getMonth().maxLength(), 23, 59, 59));
    //then
    Assert.assertEquals("Default Name", testedRange.get(2).getName());
    Assert.assertEquals(4, testedRange.get(1).getEntries().get(3).getEntryId());
    givenInvoice2.removeEntry(2);
    Assert.assertEquals(3, testedRange.get(1).getEntries().get(2).getEntryId());
    System.out.println("after entry modification size = " + testedRange.get(1).getEntries().size());
    System.out.println("and last id = " + testedRange.get(1).getEntries().get(2).getEntryId());
    System.out
        .println(
            "Afetr all operations  " + " Net total " + givenInvoice2.getNetTotalAmount().getAmount()
                + " Gross total " + givenInvoice2.getGrossTotalAmount().getAmount());
  }

  @Test
  public void shouldAddInvoiceToFile() {
    //given
    InFileDatabase database = new InFileDatabase();
    database.setFilePath("src/test/resources/InvoiceBook.txt");
    InvoiceBook invoiceBook = new InvoiceBook(database);
    //when
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice2);
    invoiceBook.addInvoice(givenInvoice3);

    LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    List<Invoice> testedRange = invoiceBook
        .getInvoicesByDateRange(LocalDateTime.of(now.getYear(), now.getMonth(), 1, 0, 0, 0),
            LocalDateTime
                .of(now.getYear(), now.getMonth(), now.getMonth().maxLength(), 23, 59, 59));
    Invoice resultInvoice = invoiceBook.getInvoices().get(invoiceBook.getInvoices().size() - 1);
    //then
    Assert.assertEquals(LocalDateTime.now().getMonthValue(),
        resultInvoice.getIssueDate().getMonthValue());
    Assert.assertEquals(3, testedRange.get(1).getEntries().get(2).getEntryId());
  }

  @Test
  public void shouldRemoveInvoice() {
    //given
    Database database = new InMemoryDatabase();
    InvoiceBook invoiceBook = new InvoiceBook(database);
    invoiceBook.addInvoice(givenInvoice);
    //when
    invoiceBook.removeInvoice(1);
    //then
    Assert.assertEquals(0, invoiceBook.getInvoices().size());
  }

  @Test
  public void shouldReplaceInvoice() {
    //given
    Database database = new InMemoryDatabase();
    InvoiceBook invoiceBook = new InvoiceBook(database);
    //when
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice2);
    invoiceBook.addInvoice(givenInvoice3);
    invoiceBook.removeInvoice(2);
    invoiceBook.addInvoice(new Invoice(new MyCompanySell(), new Buyer("Franek", "PL32222255")));
    invoiceBook.addInvoice(new Invoice(new Seller("Franek", "PL333444555"), new MyCompanyBuy()));
    invoiceBook.getInvoices().get(3).setName("333/232323");
    invoiceBook.addInvoice(new Invoice(new Seller("Franek", "PL333444555"), new MyCompanyBuy()));
    invoiceBook.addInvoice(new Invoice(new MyCompanySell(), new Buyer("Franek", "PL32222255")));
    //then
    List<Invoice> testedRange = invoiceBook.getInvoices();

    for (Invoice invoice : testedRange) {
      System.out.println(
          invoice.getInvoiceId() + " " + invoice.getName() + " " + invoice.getSeller().getVatId());
    }
    System.out.println("=====================");
    testedRange = invoiceBook.getInvoices();
    invoiceBook
        .replaceInvoice(6, new Invoice(new MyCompanySell(), new Buyer("Fafik", "PL3255555")));
    for (Invoice invoice : testedRange) {
      System.out.println(
          invoice.getInvoiceId() + " " + invoice.getName() + " " + invoice.getSeller().getVatId());
    }
    Assert.assertEquals("333/232323", testedRange.get(3).getName());
    Assert.assertEquals("Default Name", testedRange.get(1).getName());
    Assert.assertEquals("Default Name", testedRange.get(4).getName());
    Assert.assertEquals(7, testedRange.get(5).getInvoiceId());
  }


  @Test
  public void shouldRemoveInvoiceFromFile() {
    //given
    InvoiceBook invoiceBook = new InvoiceBook(db);
    //when
    when(db.getInvoices()).thenReturn(Collections.singletonList(givenInvoice3));
    invoiceBook.addInvoice(givenInvoice);
    invoiceBook.addInvoice(givenInvoice2);
    invoiceBook.addInvoice(givenInvoice3);
    //then
    invoiceBook.removeInvoice(1);
    Assert.assertEquals(1, invoiceBook.getInvoices().get(0).getInvoiceId());
  }
}
