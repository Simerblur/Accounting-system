package pl.coderstrust.database.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.fileprocessor.FileProcessor;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inFileDatabase")
public class InFileDatabase implements Database {

  private final String tempFilePath;
   private final FileProcessor fileProcessor = new FileProcessor();
  @Value("${file.path}")
  private String filePath;
  private List<Invoice> invoices = new ArrayList<>();
  private InvoiceConverter invoiceConverter = new InvoiceConverter();
  private FileProcessor fp = new FileProcessor();

  public InFileDatabase(String filePath) {
    this.filePath = filePath;
    this.tempFilePath = filePath.concat(".temp");
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    fileProcessor.appendInvoiceToFile(invoiceConverter.convertToJsonString(invoice), filePath);
  }

  @Override
  public void saveInvoice(String jsonString) {

    fp.appendInvoiceToFile(jsonString, filePath);
  }

  @Override
  public List<Invoice> getInvoices() {
    List<String> stringsFromFile;
    final List<Invoice> invoices = new ArrayList<>();
    stringsFromFile = fileProcessor.readInvoicesFromFile(filePath);
    for (String stringFromFile : stringsFromFile) {
      invoices.add(invoiceConverter.convertJsonToInvoice(stringFromFile));
    }
    return invoices;
  }

  @Override
  public void removeInvoice(int invoiceId) {
    List<Invoice> allInvoices = getInvoices();
    int index = -1;
    try {
      for (Invoice invoice : allInvoices) {
        if (invoice.getInvoiceId() == invoiceId) {
          index = allInvoices.indexOf(invoice);
          break;
        }
      }
      allInvoices.remove(index);
      writeListToTheFile(allInvoices);
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invoice not found!");
    }
  }

  private void writeListToTheFile(List<Invoice> inputList) {

    File beforeDeletion = new File(filePath);
    File newTempFile = new File(tempFilePath);
    for (Invoice invoice : inputList) {
      fileProcessor.appendInvoiceToFile(invoiceConverter.convertToJsonString(invoice), tempFilePath);
    }
    if (beforeDeletion.delete()) {
      if (newTempFile.renameTo(beforeDeletion)) {
        System.out.println("Invoice removed");
      } else {
        System.out.println("Invoice not found");
      }
    }
  }
}
