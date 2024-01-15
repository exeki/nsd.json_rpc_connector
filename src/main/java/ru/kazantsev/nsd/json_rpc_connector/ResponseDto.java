package ru.kazantsev.nsd.json_rpc_connector;

import java.util.HashMap;
import java.util.List;

public class ResponseDto {

    public abstract static class AbstractResult {
        public String jsonrpc;
        public Long id;
        public Error error;
        public static class Error{
            public Integer code;
            public String message;
        }
    }

    public static class SingleValueResult extends AbstractResult {
        public String result;
    }

    public static class SingleValueResultWithParams extends AbstractResult {
        public HashMap<String, Object> result;
    }

    public static class MultipleValueResult extends AbstractResult {
        public List<String> result;
    }

    public static class MultipleValueResultWithParams extends AbstractResult {
        public List<HashMap<String, Object>> result;
    }
}
