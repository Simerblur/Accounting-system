package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.fileprocessor.FileProcessor;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InFileDatabase implements Database {

  private final String filePath;
  private final InvoiceConverter invConverter = new InvoiceConverter();
  private final FileProcessor fileProcessor = new FileProcessor();

  public InFileDatabase(String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    fileProcessor.appendInvoiceToFile(invConverter.convertToJsonString(invoice), filePath);
  }

  @Override
  public List<Invoice> getInvoices() {
    List<String> stringsFromFile;
    final List<Invoice> invoices = new ArrayList<>();
    stringsFromFile = fileProcessor.readInvoicesFromFile(filePath);
    for (String stringFromFile : stringsFromFile) {
      invoices.add(invConverter.convertJsonToInvoice(stringFromFile));
    }
    return invoices;
  }
}
