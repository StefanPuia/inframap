package uk.inframap.data;

import jakarta.inject.Singleton;
import java.util.List;
import java.util.function.Function;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.NodeValue;

@Singleton
public class RecordMapper {

  public List<String> mapStringList(final List<Record> stringValues) {
    return stringValues.stream().map(r -> r.get(0)).map(Value::asString).toList();
  }

  public <T> List<T> mapValues(final List<Record> recordList, final Function<NodeValue, T> map) {
    return recordList.stream().map(r -> (NodeValue) r.get(0)).map(map).toList();
  }
}
