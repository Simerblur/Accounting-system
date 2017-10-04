package pl.coderstrust.database.file;

import pl.coderstrust.database.Database;
import pl.coderstrust.fileprocessor.FileProcessor;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InFileDatabase implements Database {

  private final String filePath;
  private final String tempFilePath;
  private final InvoiceConverter invConverter = new InvoiceConverter();
  private final FileProcessor fileProcessor = new FileProcessor();

  public InFileDatabase(String filePath) {
    this.filePath = filePath;
    this.tempFilePath = filePath.concat(".temp");
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
//    newTempFile.deleteOnExit();
    for (Invoice invoice : inputList) {
      fileProcessor.appendInvoiceToFile(invConverter.convertToJsonString(invoice), tempFilePath);
    }
    if (beforeDeletion.delete()) {
      if (newTempFile.renameTo(beforeDeletion)) {
      } else {
        System.out.println("Invoice not found");
      }
    }
  }
}
