package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Коннектор, содержащий в себе методы для обращения к
 * модулю jsonRpc, а так же к стандартным методам системы
 */
public class Connector extends ru.kazantsev.nsd.basic_api_connector.Connector {
    /**
     * Значение параметрам params в url запроса
     */
    protected final String URL_PARAMS_CONST = "request,response,user";
    /**
     * Значение параметры func в url запроса
     */
    protected final String URL_FUNC_CONST = "modules.jsonRpc.process";

    /**
     * Стандартный конструктор
     *
     * @param params параметры, содежащие адрес NSD и авторизационные данные
     */
    public Connector(ConnectorParams params) {
        super(params);
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.objectMapper.setDateFormat(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"));
    }

    /**
     * Выполнить запрос
     *
     * @param requestDto DTO запроса, которая будет преобразована в body
     * @return десериализованные в DTO боди ответа
     */
    public RpcResponseDto sendRequest(RpcRequestDto.Abstract requestDto) {
        try {
            String strRequestBody = objectMapper.writeValueAsString(requestDto);
            logDebug(strRequestBody);
            StringEntity entity = new StringEntity(strRequestBody, CHARSET);
            CloseableHttpResponse response = execPost(entity, URL_FUNC_CONST, URL_PARAMS_CONST, null);
            return objectMapper.readValue(EntityUtils.toString(response.getEntity()), RpcResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Выполнить запрос
     *
     * @param requestDtos DTO запросов, которые будут преобразованы в body
     * @return десериализованные в DTO боди ответа
     */
    public List<RpcResponseDto> sendRequest(List<RpcRequestDto.Abstract> requestDtos) {
        try {
            String strRequestBody = objectMapper.writeValueAsString(requestDtos);
            logDebug(strRequestBody);
            StringEntity entity = new StringEntity(strRequestBody, CHARSET);
            CloseableHttpResponse response = execPost(entity, URL_FUNC_CONST, URL_PARAMS_CONST, null);
            RpcResponseDto[] body = objectMapper.readValue(EntityUtils.toString(response.getEntity()), RpcResponseDto[].class);
            return Arrays.asList(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Выполнить запрос методом get.
     * Позволяет найти объект по ассотиативному массиву с условными операциями.
     *
     * @param fqn   код метакласс искомого объекта
     * @param query ассоциативный массив для поиска объекта
     * @param view  перечень кодов возвращаемых объектов, может быть null
     * @param id    единтификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result будет:
     * либо строка идентификатора найденного объекта, если view был передан как null,
     * либо Map с полями объекта, которые были переданы во view,
     * либо null, если объект не был найде,
     * либо в ключе error будет описание произошедшей ошибки.
     * ВАЖНО: случится ошибка, если по переданному query было найдено несколько объектов.
     */
    public RpcResponseDto jsonRpcGet(String fqn, Map<String, Object> query, List<String> view, Long id) {
        RpcRequestDto.Get dto = new RpcRequestDto.Get(fqn, query);
        dto.setId(id);
        dto.setView(view);
        return sendRequest(dto);
    }

    public RpcResponseDto jsonRpcGet(String fqn, Map<String, Object> query, List<String> view) {
        return jsonRpcGet(fqn, query, view, null);
    }

    public RpcResponseDto jsonRpcGet(String fqn, Map<String, Object> query) {
        return jsonRpcGet(fqn, query, null, null);
    }

    /**
     * Выполнить запрос методом find.
     * Позволяет найти объекты по ассотиативному массиву с условными операциями.
     *
     * @param fqn    код метакласса искомого объекта
     * @param query  ассотиативных массив для поиска объекта
     * @param view   перечень кодов возвращаемых объектов, может быть null
     * @param limit  лимит возвраемыех объектов, может быть null
     * @param offset оффсет при запросе, может быть null
     * @param id     идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result будет:
     * либо массив строк идентификаторов найденных объектов,
     * либо массив Map с полями объектов, коды которых были переданы во view,
     * либо null, если ничего не было найдено,
     * либо в ключе error будет описание произошедшей ошибки.
     */
    public RpcResponseDto jsonRpcFind(String fqn, Map<String, Object> query, List<String> view, Long limit, Long offset, Long id) {
        RpcRequestDto.Find dto = new RpcRequestDto.Find(fqn, query);
        dto.setId(id);
        dto.setView(view);
        dto.setOffset(offset);
        dto.setLimit(limit);
        return sendRequest(dto);
    }

    public RpcResponseDto jsonRpcFind(String fqn, Map<String, Object> query, List<String> view, Long limit, Long offset) {
        return jsonRpcFind(fqn, query, view, limit, offset, null);
    }

    public RpcResponseDto jsonRpcFind(String fqn, Map<String, Object> query, List<String> view, Long limit) {
        return jsonRpcFind(fqn, query, view, limit, null, null);
    }

    public RpcResponseDto jsonRpcFind(String fqn, Map<String, Object> query, List<String> view) {
        return jsonRpcFind(fqn, query, view, null, null, null);
    }

    public RpcResponseDto jsonRpcFind(String fqn, Map<String, Object> query) {
        return jsonRpcFind(fqn, query, null, null, null, null);
    }

    /**
     * Выполняет запрос методом create.
     *
     * @param fqn   код метакласса создаваемого объекта
     * @param attrs ассоциативный массив, содержащий поля создаваемого объекта
     * @param view  коды атрибутов созданного объекта, возвращаемый в ключе result, может быть null
     * @param id    идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result может быть:
     * либо идентификатор созданного объекта, если объект был создан и view был null,
     * либо Map с полями созданного объекта, если объект был создан и view был не null.
     * либо в ключе error будет описание произошедшей ошибки.
     */
    public RpcResponseDto jsonRpcCreate(String fqn, Map<String, Object> attrs, List<String> view, Long id) {
        RpcRequestDto.Create dto = new RpcRequestDto.Create(fqn, attrs);
        dto.setId(id);
        dto.setView(view);
        return sendRequest(dto);
    }

    public RpcResponseDto jsonRpcCreate(String fqn, Map<String, Object> attrs, List<String> view) {
        return jsonRpcCreate(fqn, attrs, view, null);
    }

    public RpcResponseDto jsonRpcCreate(String fqn, Map<String, Object> attrs) {
        return jsonRpcCreate(fqn, attrs, null, null);
    }

    /**
     * Выполняет запрос методом edit.
     * Позволяет отредактировать объект.
     *
     * @param uuid  идентификатор редактируемого объекта
     * @param attrs ассоциативный массив, содержащий значения полей для редактирования
     * @param view  коды атрибутов отредактированного объекта, возвращаемый в ключе result, может быть null
     * @param id    идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result может быть:
     * либо идентификатор отредактированного объекта, если объект был отредактирован и view был null,
     * либо Map с полями отредактированного объекта, если объект был отредактирован и view был не null,
     * либо в ключе error будет описание произошедшей ошибки.
     */
    public RpcResponseDto jsonRpcEdit(String uuid, Map<String, Object> attrs, List<String> view, Long id) {
        RpcRequestDto.Edit dto = new RpcRequestDto.Edit(uuid, attrs);
        dto.setId(id);
        dto.setView(view);
        return sendRequest(dto);
    }

    public RpcResponseDto jsonRpcEdit(String uuid, Map<String, Object> attrs, List<String> view) {
        return jsonRpcEdit(uuid, attrs, null, view);
    }

    public RpcResponseDto jsonRpcEdit(String uuid, Map<String, Object> attrs) {
        return sendRequest(new RpcRequestDto.Edit(uuid, attrs));
    }

    /**
     * Выполняет запрос методом edit.
     * Позволяет редактировать объект, не зная его UUID.
     *
     * @param fqn   код метокласса искомого объекта
     * @param query ассотиативный объект для поиска объекта
     * @param attrs ассоциативный массив, содержащий значения полей для редактирования
     * @param view  коды атрибутов отредактированного объекта, возвращаемый в ключе result, может быть null
     * @param id    идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result может быть:
     * либо идентификатор отредактированного объекта, если объект был отредактирован и view был null,
     * либо Map с полями отредактированного объекта, если объект был отредактирован и view был не null,
     * либо в ключе error будет описание произошедшей ошибки.
     * ВАЖНО: случится ошибка, если по переданному query было найдено несколько объектов.
     */
    public RpcResponseDto jsonRpcEdit(String fqn, Map<String, Object> query, Map<String, Object> attrs, List<String> view, Long id) {
        RpcRequestDto.Edit dto = new RpcRequestDto.Edit(fqn, query, attrs);
        dto.setView(view);
        dto.setId(id);
        return sendRequest(dto);
    }

    public RpcResponseDto jsonRpcEdit(String fqn, Map<String, Object> query, Map<String, Object> attrs, List<String> view) {
        return jsonRpcEdit(fqn, query, attrs, view, null);
    }

    public RpcResponseDto jsonRpcEdit(String fqn, Map<String, Object> query, Map<String, Object> attrs) {
        return jsonRpcEdit(fqn, query, attrs, null, null);
    }

}
