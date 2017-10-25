package pl.coderstrust;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderstrust.logic.InvoiceBook;
import pl.coderstrust.model.Money;

@Api(value = "/taxes", description = "API to retrieve VAT and INCOME tax values",
    consumes = "application/json")
@RestController
public class TaxController {

  private InvoiceBook invoiceBook;

  @Autowired
  TaxController(InvoiceBook invoiceBook) {
    this.invoiceBook = invoiceBook;
  }

  /**
   * Calculate VAT tax due for current month.
   */
  @ApiOperation(value = "Returns VAT to be paid by month",
      notes = "Positive value means what comapny will PAY, negative value means"
          + "what company SHOULD request back form tax office",
      response = Money.class)
  @RequestMapping(value = "/taxes/VAT/{month}", method = RequestMethod.GET)
  public Money calculateVatCurrentMonth(@PathVariable int month) {
    return invoiceBook.calculateTotalVat(month);
  }

  /**
   * Calculate Income tax due for current month.
   */
  @ApiOperation(value = "Returns Income Tax value to be paid by month",
      notes = "Positive value means what comapny will PAY, negative value means"
          + "what company SHOULD request back form tax office",
      response = Money.class)
  @RequestMapping(value = "/taxes/income/{month}", method = RequestMethod.GET)
  public Money calculateIncomeTaxCurrentMonth(@PathVariable int month) {
    return invoiceBook.calculateTotalIncomeTax(month);
  }
}
