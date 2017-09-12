package pl.coderstrust.database.fileprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.coderstrust.database.model.Invoice;

import java.io.IOException;

public class InvoiceConverter {

  /**
   * Converts Invoice object to the formatted JSON string.
   */
  private ObjectMapper mapper = new ObjectMapper();

  public String convertToJsonString(Invoice inputInvoice) {
    try {
      return mapper.writeValueAsString(inputInvoice);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Invoice convertJsonToInvoice(String jsonString) {
    try {
      return mapper.readValue(jsonString, Invoice.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

/*  private String invoiceToJson(Invoice inputInvoice) throws Exception {
    String outputString;
    ObjectMapper mapper = new ObjectMapper();
    outputString = mapper.writeValueAsString(inputInvoice);
    return outputString;
  }

  private Invoice jsonToInvoice(String jsonString) throws Exception {
    Invoice outputInvoice;
    ObjectMapper mapper = new ObjectMapper();
    outputInvoice = mapper.readValue(jsonString, Invoice.class);
    return outputInvoice;
  }*/
}
