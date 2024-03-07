package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.HashMap;

/**
 * Ассотиативный массив для поиска
 */
public class Query {
    @JsonValue
    HashMap<String, Object> map = new HashMap<>();
    public Query(){}
    public Query(String key, Object value) {
        map.put(key, value);
    }
    public Query put(String key, Object value) {
        map.put(key, value);
        return this;
    }

}
