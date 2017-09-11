package pl.coderstrust.database.model;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.fileprocessor.FileProcessor;
import pl.coderstrust.database.memory.InMemoryDatabase;

import java.math.BigDecimal;

public class MainForFiles {

  public static void main(String[] args) {
    Database db = new InMemoryDatabase();
    FileProcessor fp = new FileProcessor();
    InvoiceBook invoiceBook = new InvoiceBook(db);
    Invoice first = new Invoice(1,"Starter:", new Money(new BigDecimal("234.45"), Currency.PLN));
    Invoice second = new Invoice(2,"conti:", new Money(new BigDecimal("88.88"), Currency.PLN));
    invoiceBook.addInvoice(first);
    invoiceBook.addInvoice(second);
    invoiceBook.printInvoiceBook();
//    fp.writeLinesToFile(invoiceBook.getInvoices(),"src/main/resources/pl.coderstrust/InvoiceBook.txt");
  }

}
