package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Перечень методов, которые используются в модуле jsonrpc
 */
public enum Method {
    CREATE("create"),
    FIND("find"),
    GET("get"),
    EDIT("edit");
    @JsonValue
    final String code;
    Method(String code) {
        this.code = code;
    }
}
