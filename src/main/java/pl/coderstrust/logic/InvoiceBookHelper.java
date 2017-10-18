package pl.coderstrust.logic;

import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.counterparts.MyCompanySell;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvoiceBookHelper {

  static final String myVatId = new MyCompanySell().getVatId();

  /**
   * Generates unique invoice ID.
   */
  public static void generateInvoiceId(Invoice updatedInvoice, List<Invoice> allInvoices) {
    int newId;
    if (allInvoices.size() == 0) {
      newId = 1;
    } else {
      newId = allInvoices.get(allInvoices.size() - 1).getInvoiceId() + 1;
    }
    updatedInvoice.setInvoiceId(newId);
  }

  /**
   * generates correct invoice name.
   */
  public static void generateInvoiceName(Invoice invoiceToRename, List<Invoice> allInvoices) {
    String newName;
    int current = 1;
    int currentIssueMonth = invoiceToRename.getIssueDate().getMonthValue();
    if (invoiceToRename.getSeller().getVatId().equals(myVatId)) {
      if (allInvoices.size() > 0) {
        List<Invoice> currentMonthInvoices = currentMonthInvoices(invoiceToRename,
            allInvoices);
        if (currentMonthInvoices.size() > 0) {
          current = currentNameCreatorHelper(currentMonthInvoices);
          newName =
              current + 1 + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate()
                  .getYear();
        } else {
          newName =
              current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
        }
      } else {
        newName =
            current + "/" + currentIssueMonth + "/" + invoiceToRename.getIssueDate().getYear();
      }
      invoiceToRename.setName(newName);
    }
  }

  private static List<Invoice> currentMonthInvoices(Invoice invoice, List<Invoice> allInvoices) {
    int currentIssueMonth = invoice.getIssueDate().getMonthValue();
    int currentIssueYear = invoice.getIssueDate().getYear();
    int lastDayOfMonth = invoice.getIssueDate().getMonth().maxLength();
    return getInvoicesByDateRange(
        LocalDateTime.of(currentIssueYear, currentIssueMonth, 1, 0, 0, 0),
        LocalDateTime.of(currentIssueYear, currentIssueMonth, lastDayOfMonth, 23, 59, 59),
        allInvoices);
  }

  private static int currentNameCreatorHelper(List<Invoice> currentMonthInvoices) {
    int current = 0;
    Pattern pattern = Pattern.compile("[^0-9]*([0-9]+).*");
    for (Invoice invoice : currentMonthInvoices) {
      if (invoice.getSeller().getVatId().equals(myVatId)) {
        Matcher newMatcher = pattern.matcher(invoice.getName());
        if (newMatcher.matches()) {
          if (current <= Integer.valueOf(newMatcher.group(1))) {
            current = Integer.valueOf(newMatcher.group(1));
          }
        }
      }
    }
    return current;
  }

  /**
   * Returns ArrayList of invoices from the given time range inclusively.
   */
  public static List<Invoice> getInvoicesByDateRange(LocalDateTime startDate, LocalDateTime endDate,
      List<Invoice> allInvoices) {
    List<Invoice> resultList = new ArrayList<>();
    if (allInvoices.size() > 0) {
      for (Invoice invoice : allInvoices) {
        if (invoice.getIssueDate().isAfter(startDate.minusSeconds(1))
            && invoice.getIssueDate().isBefore(endDate.plusSeconds(1))) {
          resultList.add(invoice);
        }
      }
    }
    return resultList;
  }
}
