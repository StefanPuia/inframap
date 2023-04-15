package uk.inframap.data.queries;

import org.neo4j.driver.Driver;
import org.neo4j.driver.TransactionWork;

public abstract class AbstractRepository {
  private final Driver driver;

  protected AbstractRepository(final Driver driver) {
    this.driver = driver;
  }

  protected <T> T read(final TransactionWork<T> run) {
    try (var session = driver.session()) {
      return session.readTransaction(run);
    }
  }

  protected <T> void write(final TransactionWork<T> run) {
    try (var session = driver.session()) {
      session.writeTransaction(run);
    }
  }
}
