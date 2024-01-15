package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams;
import ru.kazantsev.nsd.basic_api_connector.HttpException;

import java.io.IOException;
import java.util.List;

public class Connector extends ru.kazantsev.nsd.basic_api_connector.Connector {
    protected final String URL_PARAMS_CONST = "request,response,user";
    protected final String URL_FUNC_CONST = "modules.jsonRpc.process";

    public Connector(ConnectorParams params) {
        super(params);
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    protected <T extends ResponseDto.AbstractResult> T doRequest(RequestDto requestDto, Class<T> returnType) {
        try {
            String strRequestBody = objectMapper.writeValueAsString(requestDto);
            logger.info(strRequestBody);
            StringEntity entity = new StringEntity(strRequestBody, CHARSET);
            CloseableHttpResponse response = execPost(entity, URL_FUNC_CONST, URL_PARAMS_CONST, null);
            T body = new ObjectMapper().readValue(EntityUtils.toString(response.getEntity()), returnType);
            if(body.error != null) throw new HttpException(body.error.message, body.error.code, response);
            return body;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDto.SingleValueResult jsonRpcGet(String fqn, Query query) {
        RequestDto requestDto = new RequestDto(Method.GET, new Params.Get(fqn, query));
        return doRequest(requestDto, ResponseDto.SingleValueResult.class);
    }

    public ResponseDto.SingleValueResultWithParams jsonRpcGet(String fqn, Query query, List<String> returnAttrs) {
        RequestDto requestDto = new RequestDto(Method.GET, new Params.Get(fqn, query, returnAttrs));
        return doRequest(requestDto, ResponseDto.SingleValueResultWithParams.class);
    }

    public ResponseDto.MultipleValueResult jsonRpcFind(String fqn, Query query) {
        RequestDto requestDto = new RequestDto(Method.FIND, new Params.Find(fqn, query));
        return doRequest(requestDto, ResponseDto.MultipleValueResult.class);
    }

    public ResponseDto.MultipleValueResultWithParams jsonRpcFind(String fqn, Query query, List<String> returnAttrs) {
        RequestDto requestDto = new RequestDto(Method.FIND, new Params.Find(fqn, query, returnAttrs));
        return doRequest(requestDto, ResponseDto.MultipleValueResultWithParams.class);
    }

    public ResponseDto.SingleValueResult jsonRpcCreate(String fqn, Attrs attrs) {
        RequestDto requestDto = new RequestDto(Method.CREATE, new Params.Create(fqn, attrs));
        return doRequest(requestDto, ResponseDto.SingleValueResult.class);
    }

    public ResponseDto.SingleValueResultWithParams jsonRpcCreate(String fqn, Attrs attrs, List<String> returnAttrs) {
        RequestDto requestDto = new RequestDto(Method.CREATE, new Params.Create(fqn, attrs, returnAttrs));
        return doRequest(requestDto, ResponseDto.SingleValueResultWithParams.class);
    }

    public ResponseDto.SingleValueResult jsonRpcEdit(String uuid, Attrs attrs){
        RequestDto requestDto = new RequestDto(Method.EDIT, new Params.Edit(uuid, attrs));
        return doRequest(requestDto, ResponseDto.SingleValueResult.class);
    }

    public ResponseDto.SingleValueResultWithParams jsonRpcEdit(String uuid, Attrs attrs, List<String> returnAttrs){
        RequestDto requestDto = new RequestDto(Method.EDIT, new Params.Edit(uuid, attrs, returnAttrs));
        return doRequest(requestDto, ResponseDto.SingleValueResultWithParams.class);
    }

    public ResponseDto.SingleValueResult jsonRpcEdit(Query query, Attrs attrs){
        RequestDto requestDto = new RequestDto(Method.EDIT, new Params.Edit(query, attrs));
        return doRequest(requestDto, ResponseDto.SingleValueResult.class);
    }

    public ResponseDto.SingleValueResultWithParams jsonRpcEdit(Query query, Attrs attrs, List<String> returnAttrs){
        RequestDto requestDto = new RequestDto(Method.EDIT, new Params.Edit(query, attrs, returnAttrs));
        return doRequest(requestDto, ResponseDto.SingleValueResultWithParams.class);
    }

}
