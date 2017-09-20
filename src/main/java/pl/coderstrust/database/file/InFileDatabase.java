package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.fileprocessor.FileProcessor;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileDatabase implements Database {

  private String filePath;
  private List<Invoice> invoices = new ArrayList<>();
  private InvoiceConverter invConverter = new InvoiceConverter();
  private FileProcessor fp = new FileProcessor();

  public InFileDatabase(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    try {
      fp.appendInvoiceToFile(invConverter.convertToJsonString(invoice), filePath);
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }

  @Override
  public List<Invoice> getInvoices() {
    try {
      List<String> jsonStrings;
      jsonStrings = fp.readInvoicesFromFile(filePath);
      for (String iterator : jsonStrings) {
        invoices.add(invConverter.convertJsonToInvoice(iterator));
      }
      return invoices;
    } catch (IOException e) {
      System.out.println(e.toString());
      return invoices = null;
    }

  }
}
