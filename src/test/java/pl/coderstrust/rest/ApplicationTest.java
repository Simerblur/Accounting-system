package pl.coderstrust.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderstrust.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

  @Test
  public void contextLoads() {
  }

  @Test
  public void test() {
    Application.main(new String[] {
        "--spring.main.web-environment=true"
    });
  }
}
