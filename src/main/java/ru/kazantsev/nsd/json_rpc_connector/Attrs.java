package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.HashMap;

public class Attrs {
    @JsonValue
    HashMap<String, Object> map = new HashMap<>();
    public Attrs(){}
    public Attrs put(String key, String value) {
        map.put(key, value);
        return this;
    }
    public Attrs put(String key, Number value) {
        map.put(key, value);
        return this;
    }
    public Attrs put(String key, Boolean value) {
        map.put(key, value);
        return this;
    }
    public Attrs put(String key, Date value) {
        map.put(key, value);
        return this;
    }
}
