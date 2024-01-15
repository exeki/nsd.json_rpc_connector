package ru.kazantsev.nsd.json_rpc_connector;


public class RequestDto {
    String jsonrpc = "2.0";
    Method method;
    Params params;
    Long id;

    public RequestDto(String version, Method method, Params params, Long id) {
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.params = params;
        this.id = id;
    }

    public RequestDto(Method method, Params params, Long id) {
        this.method = method;
        this.params = params;
        this.id = id;
    }

    public RequestDto(Method method, Params params) {
        this.method = method;
        this.params = params;
    }

    public String getJsonrpc() {
        return jsonrpc;
    }

    public Method getMethod() {
        return method;
    }

    public Params getParams() {
        return params;
    }

    public Long getId() {
        return id;
    }
}
