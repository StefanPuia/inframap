package uk.inframap.model.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record User(UUID id, String username) {

  public static final String KEY_ID = "id";
  public static final String KEY_USERNAME = "username";

  public Map<String, Object> toProps() {
    final Map<String, Object> map = new HashMap<>();
    map.put(KEY_ID, id.toString());
    map.put(KEY_USERNAME, username);
    return map;
  }
}
