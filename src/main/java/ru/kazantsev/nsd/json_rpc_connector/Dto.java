package ru.kazantsev.nsd.json_rpc_connector;

public class RpcDto {
    String jsonrpc = "2.0";
    Method method;
    RpcParams params;
    Long id;

    public RpcDto(String version, Method method, RpcParams params, Long id) {
        this.jsonrpc = jsonrpc;
        this.method = method;
        this.params = params;
        this.id = id;
    }

    public RpcDto(Method method, RpcParams params, Long id) {
        this.method = method;
        this.params = params;
        this.id = id;
    }

    public RpcDto(Method method, RpcParams params) {
        this.method = method;
        this.params = params;
    }
}
