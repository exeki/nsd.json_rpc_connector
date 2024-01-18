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
    public Query(String key, String value) {
        map.put(key, value);
    }
    public Query(String key, Number value) {
        map.put(key, value);
    }
    public Query(String key, Boolean value) {
        map.put(key, value);
    }
    public Query(String key, Condition value) {
        map.put(key, value);
    }
    public Query(String key, Date value) {
        map.put(key, value);
    }

    public Query put(String key, String value) {
        map.put(key, value);
        return this;
    }
    public Query put(String key, Number value) {
        map.put(key, value);
        return this;
    }
    public Query put(String key, Boolean value) {
        map.put(key, value);
        return this;
    }
    public Query put(String key, Condition value) {
        map.put(key, value);
        return this;
    }
    public Query put(String key, Date value) {
        map.put(key, value);
        return this;
    }
}
