package pl.coderstrust.fileprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.model.Invoice;

import java.io.IOException;

public class InvoiceConverter {

  /**
   * Converts Invoice object to the formatted JSON string. Test sample Javadoc.
   */
  public String convertToJsonString(Invoice inputInvoice) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(inputInvoice);
    } catch (JsonProcessingException e) {
      System.out.println(e.toString());
    }
    return null;
  }

  /**
   * Converts given JSON string to the Invoice object.
   */

  public Invoice convertJsonToInvoice(String jsonString) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(jsonString, Invoice.class);
    } catch (IOException e) {
      System.out.println(e.toString());
    }
    return null;
  }
}
