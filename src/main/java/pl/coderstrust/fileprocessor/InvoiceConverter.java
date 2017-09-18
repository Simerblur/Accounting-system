package pl.coderstrust.fileprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.model.Invoice;

import java.io.IOException;

public class InvoiceConverter {

  /**
   * Converts Invoice object to the formatted JSON string.
   */
  private ObjectMapper mapper = new ObjectMapper();

  /**
   * Test sample Javadoc.
   */
  public String convertToJsonString(Invoice inputInvoice) {
    try {
      return mapper.writeValueAsString(inputInvoice);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Converts given JSON string to the Invoice object.
   */

  public Invoice convertJsonToInvoice(String jsonString) {
    try {
      return mapper.readValue(jsonString, Invoice.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
