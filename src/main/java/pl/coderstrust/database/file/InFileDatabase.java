package pl.coderstrust.database.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.logic.FileProcessor;
import pl.coderstrust.logic.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inFileDatabase")
public class InFileDatabase implements Database {

  @Value("${pl.coderstrust.file.path}")
  private String filePath;

  @Value("${pl.coderstrust.file.path.temp}")
  private String tempFilePath;

  private final FileProcessor fileProcessor;
  private final InvoiceConverter invoiceConverter;

  public InFileDatabase() {
    this.invoiceConverter = new InvoiceConverter();
    this.fileProcessor = new FileProcessor();
  }

  @Override
  public void saveInvoice(Invoice invoice) {
    fileProcessor.appendInvoiceToFile(invoiceConverter.convertToJsonString(invoice), filePath);
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
  public boolean removeInvoice(int invoiceId) {
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
      return true;
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invoice not found!");
      return false;
    }
  }

  @Override
  public boolean replaceInvoice(int invoiceId, Invoice invoice) {
    List<Invoice> allInvoices = getInvoices();
    int index = -1;
    try {
      for (Invoice loopedInvoice : allInvoices) {
        if (loopedInvoice.getInvoiceId() == invoiceId) {
          index = allInvoices.indexOf(loopedInvoice);
          break;
        }
      }
      allInvoices.set(index, invoice);
      writeListToTheFile(allInvoices);
      return true;
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Invoice not found!");
      return false;
    }
  }

  private void writeListToTheFile(List<Invoice> inputList) {

    File beforeDeletion = new File(filePath);
    File newTempFile = new File(tempFilePath);
    for (Invoice invoice : inputList) {
      fileProcessor
          .appendInvoiceToFile(invoiceConverter.convertToJsonString(invoice), tempFilePath);
    }
    if (beforeDeletion.delete()) {

      if (newTempFile.renameTo(beforeDeletion)) {
        System.out.println("Invoice removed");
      } else {
        System.out.println("Invoice not found");
      }
    }
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
    this.tempFilePath = filePath.concat(".tmp");
  }
}
