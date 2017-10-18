package pl.coderstrust.logic;

import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;
import pl.coderstrust.model.Invoice;

import java.time.LocalDateTime;

import java.util.List;


@Service
public class InvoiceBook {

  private final Database database;

  public InvoiceBook(Database database) {
    this.database = database;
  }

  public List<Invoice> getInvoices() {
    return database.getInvoices();
  }

  /**
   * Adds new invoice to the invoice book.
   */
  public int addInvoice(Invoice invoice) {
    InvoiceBookHelper.generateInvoiceId(invoice, getInvoices());
    InvoiceBookHelper.generateInvoiceName(invoice, getInvoices());
    database.saveInvoice(invoice);
    return invoice.getInvoiceId();
  }

  /**
   * Returns ArrayList of invoices from the given time range inclusively.
   */
  public List<Invoice> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
    return InvoiceBookHelper.getInvoicesByDateRange(startDate, endDate, getInvoices());
  }

  public void removeInvoice(int invoiceId) {
    database.removeInvoice(invoiceId);
  }

  public void replaceInvoice(int invoiceId, Invoice invoice) {
    invoice.setInvoiceId(invoiceId);
    database.replaceInvoice(invoiceId, invoice);
  }
}


