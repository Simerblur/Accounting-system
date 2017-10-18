package pl.coderstrust.database.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.logic.InvoiceConverter;
import pl.coderstrust.model.Invoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@ConditionalOnProperty(name = "pl.coderstrust.database", havingValue = "inMemoryDatabase")
public class InMemoryDatabase implements Database {

  private List<Invoice> invoices = new ArrayList<>();
  private InvoiceConverter invoiceConverter;

  @Override
  public void saveInvoice(Invoice invoice) {
    invoices.add(invoice);
  }

  @Override
  public List<Invoice> getInvoices() {
    return Collections.unmodifiableList(invoices);
  }

  @Override
  public boolean removeInvoice(int invoiceId) {
    int index = -1;
    try {
      for (Invoice invoice : invoices) {
        if (invoice.getInvoiceId() == invoiceId) {
          index = invoices.indexOf(invoice);
        }
      }
      invoices.remove(index);
      return true;
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }
  }

  @Override
  public boolean replaceInvoice(int invoiceId, Invoice invoice) {
    int index = -1;
    try {
      for (Invoice loopedInvoice : invoices) {
        if (loopedInvoice.getInvoiceId() == invoiceId) {
          index = invoices.indexOf(loopedInvoice);
        }
      }
      invoices.set(index, invoice);
      return true;
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    }
  }
}
