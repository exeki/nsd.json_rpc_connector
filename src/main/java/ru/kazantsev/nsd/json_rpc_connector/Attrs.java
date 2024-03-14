package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

/**
 * Ассотиативный массив для редактирования/создания
 */
public class Attrs {
    @JsonValue
    HashMap<String, Object> map = new HashMap<>();
    public Attrs() {}
    public Attrs(String key, Object value) {
        map.put(key, value);
    }
    public Attrs put(String key, Object value) {
        map.put(key, value);
        return this;
    }
}