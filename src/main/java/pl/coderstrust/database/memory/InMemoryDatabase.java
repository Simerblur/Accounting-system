package pl.coderstrust.database.memory;

import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.fileprocessor.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class InMemoryDatabase implements Database {

  private List<Invoice> invoices = new ArrayList<>();
  private InvoiceConverter invoiceConverter;

  @Override
  public void saveInvoice(Invoice invoice) {
    invoices.add(invoice);
  }

  @Override
  public void saveInvoice(String jsonString) {
    invoices.add(invoiceConverter.convertJsonToInvoice(jsonString));
  }

  @Override
  public List<Invoice> getInvoices() {
    return Collections.unmodifiableList(invoices);
  }
}
