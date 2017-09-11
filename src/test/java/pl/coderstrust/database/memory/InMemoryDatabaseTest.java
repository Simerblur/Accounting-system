package pl.coderstrust.database.memory;

import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;

public class InMemoryDatabaseTest extends AbstractDatabaseTest {


  @Override
  protected Database getDatabase() {
    return new InMemoryDatabase();
  }
}