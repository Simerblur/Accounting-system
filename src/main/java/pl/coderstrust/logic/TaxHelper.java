package pl.coderstrust.logic;

import pl.coderstrust.model.Invoice;
import pl.coderstrust.model.Money;
import pl.coderstrust.model.counterparts.MyCompanySell;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class TaxHelper {

  static final String myVatId = new MyCompanySell().getVatId();

  /**
   * Calucalate due VAT value for the current month.
   *
   * @param month integer value of the month.
   * @param invoices all invoices for the current year.
   * @return value of the VAT to pay, type Money.
   */
  public static Money calculateVat(int month, List<Invoice> invoices) {
    List<Invoice> result = invoices
        .stream()
        .filter(invoice -> invoice.getIssueDate().getMonthValue() == month)
        .collect(Collectors.toList());
    if (result.size() > 0) {
      Money totalVat = new Money(BigDecimal.ZERO, result.get(0).getNetTotalAmount().getCurrency());
      for (Invoice invoice : result) {
        if (invoice.getSeller().getVatId().equals(myVatId)) {
          totalVat = new Money(totalVat.getAmount().add(invoice.getGrossTotalAmount().getAmount()
              .subtract(invoice.getNetTotalAmount().getAmount())), invoice.getNetTotalAmount()
              .getCurrency());
        } else if (invoice.getBuyer().getVatId().equals(myVatId)) {
          totalVat = new Money(
              totalVat.getAmount().subtract(invoice.getGrossTotalAmount().getAmount()
                  .subtract(invoice.getNetTotalAmount().getAmount())), invoice.getNetTotalAmount()
              .getCurrency());
        }
      }
      return totalVat;
    }
    return new Money();
  }

  /**
   * * Calucalate due IncomeTax value for the current month.
   *
   * @param month integer value of the month.
   * @param invoices all invoices for the current year.
   * @return value of the VAT to pay, type Money.
   */
  public static Money calculateIncomeTax(int month, List<Invoice> invoices) {
    List<Invoice> result = invoices
        .stream()
        .filter(invoice -> invoice.getIssueDate().getMonthValue() == month)
        .collect(Collectors.toList());
    if (result.size() > 0) {
      Money incomeTaxBase = new Money(BigDecimal.ZERO,
          result.get(0).getNetTotalAmount().getCurrency());
      for (Invoice invoice : result) {
        if (invoice.getSeller().getVatId().equals(myVatId)) {
          incomeTaxBase = new Money(
              incomeTaxBase.getAmount().add(invoice.getNetTotalAmount().getAmount()),
              invoice.getNetTotalAmount()
                  .getCurrency());
        } else if (invoice.getBuyer().getVatId().equals(myVatId)) {
          incomeTaxBase = new Money(
              incomeTaxBase.getAmount().subtract(invoice.getNetTotalAmount().getAmount()),
              invoice.getNetTotalAmount()
                  .getCurrency());
        }
      }
      return new Money(incomeTaxBase.getAmount()
          .multiply(new BigDecimal(0.19).setScale(2, BigDecimal.ROUND_HALF_UP))
          .setScale(2, BigDecimal.ROUND_HALF_UP),
          incomeTaxBase.getCurrency());
    }
    return new Money();
  }
}
