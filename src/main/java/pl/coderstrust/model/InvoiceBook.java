package pl.coderstrust.model;

import org.springframework.stereotype.Service;
import pl.coderstrust.database.Database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  public void addInvoice(Invoice invoice) {
    invoice.setInvoiceId(generateInvoiceId());
    invoice.setName(generateInvoiceName(invoice));
    database.saveInvoice(invoice);
  }

  private int generateInvoiceId() {
    int newId;
    if (getInvoices().size() == 0) {
      newId = 1;
    } else {
      newId = getInvoices().get(getInvoices().size() - 1).getInvoiceId() + 1;
    }
    return newId;
  }

  private String generateInvoiceName(Invoice invoiceToRename) {
    String newName;
    String previousName;
    int current = 1;
    int currentIssueMonth = invoiceToRename.getIssueDate().getMonthValue();
    if (getInvoices().size() > 0) {
      previousName = getInvoices().get(getInvoices().size() - 1).getName();
      int previousIssueMonth = getInvoices().get(getInvoices().size() - 1).getIssueDate()
          .getMonthValue();
      currentIssueMonth = invoiceToRename.getIssueDate().getMonthValue();

      if (currentIssueMonth != previousIssueMonth) {
        newName =
            current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
      } else {
        Matcher matcher = Pattern.compile("[^0-9]*([0-9]+).*").matcher(previousName);
        if (matcher.matches()) {
          current = current + Integer.valueOf(matcher.group(1));
          newName =
              current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
        } else {
          newName =
              current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
        }
      }
    } else {
      newName =
          current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
    }
    return newName;
  }

  /**
   * Returns invoices by the range of dates.
   */

  public List<Invoice> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
    List<Invoice> invoicesFromRange = getInvoices();
    List<Invoice> resultList = new ArrayList<>();
    for (Invoice iterator : invoicesFromRange) {
      if (iterator.getIssueDate().isAfter(startDate) && iterator.getIssueDate().isBefore(endDate)) {
        resultList.add(iterator);
      }
    }
    return resultList;
  }
}
