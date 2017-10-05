package pl.coderstrust.fileprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import pl.coderstrust.model.Invoice;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InvoiceConverter {

  private final ObjectMapper mapper = objectMapper();

  /**
   * Provides correctly configured ObjectMapper.
   */

  private ObjectMapper objectMapper() {
    final ObjectMapper mapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    javaTimeModule
        .addDeserializer(LocalDate.class,
            new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    javaTimeModule
        .addSerializer(LocalDate.class,
            new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    javaTimeModule.addSerializer(LocalDateTime.class,
        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    mapper.registerModule(javaTimeModule);
    mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return mapper;
  }

  /**
   * Converts Invoice object to the formatted JSON string.
   */
  public String convertToJsonString(Invoice inputInvoice) {
    try {
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
      return mapper.readValue(jsonString, Invoice.class);
    } catch (IOException e) {
      return new Invoice();
    }
  }
}
