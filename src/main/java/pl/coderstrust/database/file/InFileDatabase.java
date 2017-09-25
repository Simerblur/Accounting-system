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

    fp.appendInvoiceToFile(invConverter.convertToJsonString(invoice), filePath);
  }

  @Override
  public void saveInvoice(String jsonString) {

    fp.appendInvoiceToFile(jsonString, filePath);
  }

  @Override
  public List<Invoice> getInvoices() {

    List<String> jsonStrings;
    jsonStrings = fp.readInvoicesFromFile(filePath);
    for (String iterator : jsonStrings) {
      invoices.add(invConverter.convertJsonToInvoice(iterator));
    }
    return invoices;
  }
}
