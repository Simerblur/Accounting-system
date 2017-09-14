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

  @Override
  public void saveInvoice(Invoice invoice) {
    try {
      fp.appendInvoiceToFile(invConverter.convertToJsonString(invoice), filePath);
    } catch (IOException e) {
      e.printStackTrace();
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
    } catch (IOException e) {
      e.printStackTrace();
    }
    return invoices;
  }

  @Override
  public Integer getInvoiceIndex(int invoiceId) {
    invoices = getInvoices();
    int index = -1;
    for (int i = 0; i < invoices.size(); i++) {
      if (invoiceId == invoices.get(i).getId()) {
        index = i;
      }
    }
    return index;
  }
}
