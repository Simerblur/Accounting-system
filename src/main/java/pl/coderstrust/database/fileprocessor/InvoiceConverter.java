package pl.coderstrust.database.fileprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.database.model.Invoice;

import java.io.IOException;

public class InvoiceConverter {

  /**
   * Converts Invoice object to the formatted JSON string.
   * Test sample Javadoc.
   */
  public String convertToJsonString(Invoice inputInvoice) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(inputInvoice);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Test sample Javadoc.
   */

  public Invoice convertJsonToInvoice(String jsonString) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(jsonString, Invoice.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
