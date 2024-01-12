package ru.kazantsev.nsd.json_rpc_connector;

import java.util.List;
import java.util.Map;

public abstract class RpcParams {
    static class Create extends RpcParams {
        String fqn;
        Map<String, Object> attrs;
        List<String> returnAttrs = null;
    }

    static class Get extends RpcParams {
        String fqn;
        Map<String, Object> attrs;
        List<String> returnAttrs = null;
    }

    static class Edit extends RpcParams {
        String uuid;
        Map<String, Object> query;
        Map<String, Object> attrs;
        List<String> returnAttrs = null;
    }

    static class Find extends RpcParams {
        String fqn;
        Map<String, Object> attrs;
        List<String> returnAttrs = null;
    }

}
