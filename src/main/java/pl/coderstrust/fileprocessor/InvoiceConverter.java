package pl.coderstrust.fileprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.util.ArrayList;

public class InvoiceConverter {

  /**
   * Converts Invoice object to the formatted JSON string. Test sample Javadoc. Coverage problem,
   * can't reach catch block, any idea?
   */
  public String convertToJsonString(Invoice inputInvoice) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(inputInvoice);
    } catch (JsonProcessingException e) {
      return "Wrong Invoice provided, can't convert to JSON";
    }
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
      return new Invoice("Error", "Wrong JSON provided", new ArrayList<>());
    }
  }
}
