package pl.coderstrust.exceptions;

import com.sun.javafx.binding.StringFormatter;

public class InvoiceNotFoundException extends RuntimeException {

  public InvoiceNotFoundException(int id) {
    super(String.format("Invoice with id %d not found", id ));
  }
}
