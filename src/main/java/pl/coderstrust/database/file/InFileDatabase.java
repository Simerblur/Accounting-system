package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.database.fileprocessor.FileProcessor;
import pl.coderstrust.database.fileprocessor.InvoiceConverter;
import pl.coderstrust.database.model.Invoice;

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

  public String getFilePath() {
    return filePath;
  }

  @Override
  public void saveInvoice(Invoice invoice) {

  }

  @Override
  public List<Invoice> getInvoices() {
    try {
      List<String> jsonStrings;
      jsonStrings = fp.readInvoicesFromFile(filePath);
      for (String iterator : jsonStrings){
        invoices.add(invConverter.convertJsonToInvoice(iterator));
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return invoices;
  }

  @Override
  public Invoice getInvoice(int invoiceId) {
    return null;
  }
}
