package pl.coderstrust.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.model.Invoice;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

  @LocalServerPort
  private int port;


  @Autowired
  private TestRestTemplate restTemplate;

  /**
   * Test HTTP request.
   */

  @Test
  public void greetingShouldReturnDefaultMessage() throws Exception {
    ResponseEntity<Invoice[]> responseEntity = restTemplate
        .getForEntity("http://localhost:" + port + "/invoices",
            Invoice[].class);
    Invoice[] testInvoice = responseEntity.getBody();
    System.out.println(testInvoice.length);
  }
}