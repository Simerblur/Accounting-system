package pl.coderstrust.database.file;

import pl.coderstrust.database.AbstractDatabaseTest;
import pl.coderstrust.database.Database;

public class InFileDatabaseTest extends AbstractDatabaseTest {


  @Override
  protected Database getDatabase() {
    return new InFileDatabase();
  }
}