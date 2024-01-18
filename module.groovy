package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.databind.SerializationFeature
import groovy.transform.Field;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import ru.kazantsev.nsd.basic_api_connector.ConnectorParams;
import com.fasterxml.jackson.annotation.JsonValue;

@Field String MODULE_NAME = "nsdJsonRpcConnector"

/**
 * Ассотиативный массив для редактирования/создания
 */
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

/**
 * Класс, содержащий в себе классы условных операторов,
 * используемиых в классе Query для запросов где используется поиск
 */
public abstract class Condition {
    Map<String, Object> value;
    @JsonValue
    Map<String, Object> getValue() {
        return value;
    }

    /**
     * Поиск по вхождению в строке (тексте) значения атрибута, который соответствует поисковому шаблону. Для формирования поискового шаблона допустимо использование спецсимвола '%'.
     * Ограничение: используется для атрибутов типа "Строка", "Текст", "Текст в формате RTF". Иначе генерируется исключение.
     */
    public static class Like extends Condition {
        public Like(String str) {
            this.value = Map.of("method", "like", "params", Map.of("args", List.of(str)));
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых null или пустой список.
     */
    public static class IsNull extends Condition {
        public IsNull(){
            this.value = Map.of("method", "isNull");
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых не null или не пустой список.
     */
    public static class IsNotNull extends Condition {
        public IsNotNull(){
            this.value = Map.of("method", "isNotNull");
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых совпадает хотя бы с одним из значений из списке параметров.
     * Ограничение: минимальное количество параметров: 2, иначе генерируется исключение.
     */
    public static class OrEq extends Condition {
        public OrEq(String[] args){
            this.value =  Map.of("method", "orEq", "params", Map.of("args", args));
        }
        public OrEq(Number[] args){
            this.value =  Map.of("method", "orEq", "params", Map.of("args", args));
        }
        public OrEq(Boolean[] args){
            this.value =  Map.of("method", "orEq", "params", Map.of("args", args));
        }
        public OrEq(Date[] args){
            this.value =  Map.of("method", "orEq", "params", Map.of("args", args));
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых не совпадает ни с одним из значений из списка параметров. Допустимо использование с одним параметром и с несколькими параметрами.
     */
    public static class Not extends Condition {
        public Not(String[] args){
            this.value =  Map.of("method", "not", "params", Map.of("args", args));
        }
        public Not(Number[] args){
            this.value =  Map.of("method", "not", "params", Map.of("args", args));
        }
        public Not(Boolean[] args){
            this.value =  Map.of("method", "not", "params", Map.of("args", args));
        }
        public Not(Date[] args){
            this.value =  Map.of("method", "not", "params", Map.of("args", args));
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых совпадает со значением параметра.
     */
    public static class Eq extends Condition {
        public Eq(Number arg){
            this.value =  Map.of("method", "eq", "params", Map.of("args", List.of(arg)));
        }
        public Eq(String arg){
            this.value =  Map.of("method", "eq", "params", Map.of("args", List.of(arg)));
        }
        public Eq(Boolean arg){
            this.value =  Map.of("method", "eq", "params", Map.of("args", List.of(arg)));
        }
        public Eq(Date arg){
            this.value =  Map.of("method", "eq", "params", Map.of("args", List.of(arg)));
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых содержится в списке параметров.
     * Ограничение: при передаче в op.in на Oracle более 1000 объектов, а на MS SQL более 2100 будет
     * выведено сообщение об ошибке, так как в указанных базах есть ограничение на количество параметров при использовании условия вхождения значений в список.
     */
    public static class In extends Condition {
        public In(String[] args){
            this.value =  Map.of("method", "in", "params", Map.of("args", args));
        }
        public In(Number[] args){
            this.value =  Map.of("method", "in", "params", Map.of("args", args));
        }
        public In(Boolean[] args){
            this.value =  Map.of("method", "in", "params", Map.of("args", args));
        }
        public In(Date[] args){
            this.value =  Map.of("method", "in", "params", Map.of("args", args));
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых находится в рамках между value1 и value2, включая граничные значения.
     * Ограничение: используется для атрибутов типа "Дата", "Дата/время" и числовых атрибутов, иначе генерируется исключение.
     */
    public static class Between extends Condition {
        public Between(Date arg1, Date arg2){
            this.value =  Map.of("method", "between", "params", Map.of("args", List.of(arg1, arg2)));
        }
        public Between(Number arg1, Number arg2){
            this.value =  Map.of("method", "between", "params", Map.of("args", List.of(arg1, arg2)));
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых больше значения указанного параметра:
     * Ограничение: используется для атрибутов типа "Дата", "Дата/время" и числовых атрибутов, иначе генерируется исключение.
     */
    public static class Gt extends Condition {
        public Gt(Number arg){
            this.value =  Map.of("method", "gt", "params", Map.of("args", List.of(arg)));
        }
        public Gt(Date arg){
            this.value =  Map.of("method", "gt", "params", Map.of("args", List.of(arg)));
        }
    }

    /**
     * Поиск объектов, значение целевого атрибута которых меньше значения указанного параметра.
     * Ограничение: используется для атрибутов типа "Дата", "Дата/время" и числовых атрибутов, иначе генерируется исключение.
     */
    public static class Lt extends Condition {
        public Lt(Number arg){
            this.value =  Map.of("method", "lt", "params", Map.of("args", List.of(arg)));
        }
        public Lt(Date arg){
            this.value =  Map.of("method", "lt", "params", Map.of("args", List.of(arg)));
        }
    }
}

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
            logger.info(strRequestBody);
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
            logger.info(strRequestBody);
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
     * @param fqn код метакласс искомого объекта
     * @param query ассоциативный массив для поиска объекта
     * @param view перечень кодов возвращаемых объектов, может быть null
     * @param id единтификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result будет:
     * либо строка идентификатора найденного объекта, если view был передан как null,
     * либо Map с полями объекта, которые были переданы во view,
     * либо null, если объект не был найде,
     * либо в ключе error будет описание произошедшей ошибки.
     * ВАЖНО: случится ошибка, если по переданному query было найдено несколько объектов.
     */
    public RpcResponseDto jsonRpcGet(String fqn, Query query, List<String> view, Long id){
        RpcRequestDto.Get dto = new RpcRequestDto.Get(fqn, query);
        dto.setId(id);
        dto.setView(view);
        return sendRequest(dto);
    }

    /**
     * Выполнить запрос методом find.
     * Позволяет найти объекты по ассотиативному массиву с условными операциями.
     * @param fqn код метакласса искомого объекта
     * @param query ассотиативных массив для поиска объекта
     * @param view перечень кодов возвращаемых объектов, может быть null
     * @param limit лимит возвраемыех объектов, может быть null
     * @param offset оффсет при запросе, может быть null
     * @param id идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result будет:
     * либо массив строк идентификаторов найденных объектов,
     * либо массив Map с полями объектов, коды которых были переданы во view,
     * либо null, если ничего не было найдено,
     * либо в ключе error будет описание произошедшей ошибки.
     */
    public RpcResponseDto jsonRpcFind(String fqn, Query query, List<String> view, Long limit, Long offset, Long id){
        RpcRequestDto.Find dto = new RpcRequestDto.Find(fqn, query);
        dto.setId(id);
        dto.setView(view);
        dto.setOffset(offset);
        dto.setLimit(limit);
        return sendRequest(dto);
    }

    /**
     * Выполняет запрос методом create.
     * @param fqn код метакласса создаваемого объекта
     * @param attrs ассоциативный массив, содержащий поля создаваемого объекта
     * @param view коды атрибутов созданного объекта, возвращаемый в ключе result, может быть null
     * @param id идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result может быть:
     * либо идентификатор созданного объекта, если объект был создан и view был null,
     * либо Map с полями созданного объекта, если объект был создан и view был не null.
     * либо в ключе error будет описание произошедшей ошибки.
     */
    public RpcResponseDto jsonRpcCreate(String fqn, Attrs attrs, List<String> view, Long id) {
        RpcRequestDto.Create dto = new RpcRequestDto.Create(fqn, attrs);
        dto.setId(id);
        dto.setView(view);
        return sendRequest(dto);
    }

    /**
     * Выполняет запрос методом edit.
     * Позволяет отредактировать объект.
     * @param uuid идентификатор редактируемого объекта
     * @param attrs ассоциативный массив, содержащий значения полей для редактирования
     * @param view коды атрибутов отредактированного объекта, возвращаемый в ключе result, может быть null
     * @param id идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result может быть:
     * либо идентификатор отредактированного объекта, если объект был отредактирован и view был null,
     * либо Map с полями отредактированного объекта, если объект был отредактирован и view был не null,
     * либо в ключе error будет описание произошедшей ошибки.
     */
    public RpcResponseDto jsonRpcEdit(String uuid, Attrs attrs, List<String> view, Long id){
        RpcRequestDto.Edit dto = new RpcRequestDto.Edit(uuid, attrs);
        dto.setId(id);
        dto.setView(view);
        return sendRequest(dto);
    }

    /**
     * Выполняет запрос методом edit.
     * Позволяет редактировать объект, не зная его UUID.
     * @param fqn код метокласса искомого объекта
     * @param query ассотиативный объект для поиска объекта
     * @param attrs ассоциативный массив, содержащий значения полей для редактирования
     * @param view коды атрибутов отредактированного объекта, возвращаемый в ключе result, может быть null
     * @param id идентификатор запроса, может быть null
     * @return результат выполнения запроса, в ключе result может быть:
     * либо идентификатор отредактированного объекта, если объект был отредактирован и view был null,
     * либо Map с полями отредактированного объекта, если объект был отредактирован и view был не null,
     * либо в ключе error будет описание произошедшей ошибки.
     * ВАЖНО: случится ошибка, если по переданному query было найдено несколько объектов.
     */
    public RpcResponseDto jsonRpcEdit(String fqn, Query query, Attrs attrs, List<String> view, Long id) {
        RpcRequestDto.Edit dto = new RpcRequestDto.Edit(fqn, query, attrs);
        dto.setView(view);
        dto.setId(id);
        return sendRequest(dto);
    }

}

/**
 * Ассотиативный массив для поиска
 */
public class Query {
    @JsonValue
    HashMap<String, Object> map = new HashMap<>();
    public Query(){}
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

/**
 * Создержит в себе классы, которые испольщуются в качестве DTO
 * для body запроса при обращении к модулю json rpc.
 */
@SuppressWarnings("unused")
public abstract class RpcRequestDto {

    /**
     * Абстракция, содержит повторяющиеся поля/методы
     */
    protected static abstract class Abstract {
        protected static final String JSON_RPC_VERSION_CONST = "2.0";
        protected String jsonRpcVersion;
        protected Long id;
        List<String> view = null;

        /**
         * Установить id запроса
         * @param id идентификатор
         */
        public void setId(Long id) {
            this.id = id;
        }

        /**
         * Установить возвращаемые атрибуты
         * @param attrs возвращаемые атрибуты
         */
        public void setView(List<String> attrs) {
            this.view = attrs;
        }

        /**
         * Получить используемую версию json rpc (константно 2.0)
         * @return версия json rpc
         */
        String getJsonRpcVersion() {
            return this.jsonRpcVersion;
        }

        /**
         * Получить id запрос
         * @return id запроса
         */
        Long getId() {
            return this.id;
        }

        /**
         * Получить перечень возвращаемых атрибутов
         * @return перечень возвращаемых атриубтов
         */
        List<String> getView() {
            return this.view;
        }

        /**
         * Метод для подготовки к сериализации
         * @return подготолвенная к сериализации Map
         */
        @JsonValue
        abstract public Map<String, Object> getJsonValue();
    }

    /**
     * DTO для выполнения запроса методом get
     */
    public static class Get extends Abstract {
        /**
         * Код метокласса искомого объекта
         */
        String fqn;
        /**
         * Ассоциативный массив для поиска объекта
         */
        Query query;

        /**
         * @param fqn код метокласса искомого объекта
         * @param query ассоциативный массив для поиска объекта
         */
        public Get(String fqn, Query query) {
            this.fqn = fqn;
            this.query = query;
        }

        /**
         * Получить код метокласса искомого объекта
         * @return код метокласса искомого объекта
         */
        public String getFqn() {
            return fqn;
        }

        /**
         * Получить ассоциативный массив для поиска объекта
         * @return ассоциативный массив для поиска объекта
         */
        public Query getQuery() {
            return query;
        }

        /**
         * Метод для подготовки к сериализации
         * @return подготолвенная к сериализации Map
         */
        @Override
        public Map<String, Object> getJsonValue() {
            HashMap<String, Object> params = new HashMap<>();
            params.put("fqn", fqn);
            params.put("query", query);
            params.put("view", view);
            HashMap<String, Object> dto = new HashMap<>();
            dto.put("jsonrpc", JSON_RPC_VERSION_CONST);
            dto.put("method", "get");
            dto.put("id", id);
            dto.put("params", params);
            return dto;
        }
    }

    /**
     * DTO для выполнения запроса методом find
     */
    public static class Find extends Get {
        /**
         * Лимит возвращаемых объектов
         */
        Long limit;
        /**
         * Оффсет при поиске объектов
         */
        Long offset;

        /**
         * @param fqn код метокласса искомого объекта
         * @param query ассоциативный массив для поиска объекта
         */
        public Find(String fqn, Query query) {
            super(fqn, query);
        }

        /**
         * Получить лимит возвращаемых объектов
         * @return лимит возвращаемых объектов
         */
        public Long getLimit() {
            return limit;
        }

        /**
         * Установить лимит возвращаемых объектов
         * @param limit лимит возвращаемых объектов
         */
        public void setLimit(Long limit) {
            this.limit = limit;
        }

        /**
         * Получить оффсет при поиске объектов
         * @return оффсет при поиске объектов
         */
        public Long getOffset() {
            return offset;
        }

        /**
         * Установить оффсет при поиске объектов
         * @param offset оффсет при поиске объектов
         */
        public void setOffset(Long offset) {
            this.offset = offset;
        }

        /**
         * Метод для подготовки к сериализации
         * @return подготолвенная к сериализации Map
         */
        @Override
        public Map<String, Object> getJsonValue() {
            HashMap<String, Object> params = new HashMap<>();
            params.put("fqn", fqn);
            params.put("query", query);
            params.put("view", view);
            params.put("limit", limit);
            params.put("offset", offset);
            HashMap<String, Object> dto = new HashMap<>();
            dto.put("jsonrpc", JSON_RPC_VERSION_CONST);
            dto.put("method", "find");
            dto.put("id", id);
            dto.put("params", params);
            return dto;
        }
    }

    /**
     * DTO для выполнения запроса методом create
     */
    public static class Create extends Abstract {
        /**
         * Код метакласса создаваемого объекта
         */
        String fqn;
        /**
         * Ассоциативный массив с атрибутами создаваемого объекта
         */
        Attrs attrs;

        /**
         * @param fqn код метакласса создаваемого объекта
         * @param attrs ассоциативный массив с атрибутами создаваемого объекта
         */
        public Create(String fqn, Attrs attrs) {
            this.attrs = attrs;
            this.fqn = fqn;
        }

        /**
         * Получить код метакласса создаваемого объекта
         * @return код метакласса создаваемого объекта
         */
        public String getFqn() {
            return fqn;
        }

        /**
         * Получить ассоциативный массив с атрибутами создаваемого объекта
         * @return ассоциативный массив с атрибутами создаваемого объекта
         */
        public Attrs getAttrs() {
            return attrs;
        }

        /**
         * Метод для подготовки к сериализации
         * @return подготолвенная к сериализации Map
         */
        @Override
        public Map<String, Object> getJsonValue() {
            HashMap<String, Object> params = new HashMap<>();
            params.put("fqn", fqn);
            params.put("attrs", attrs);
            params.put("view", view);
            HashMap<String, Object> dto = new HashMap<>();
            dto.put("jsonrpc", JSON_RPC_VERSION_CONST);
            dto.put("method", "create");
            dto.put("id", id);
            dto.put("params", params);
            return dto;
        }
    }

    /**
     * DTO для выполнения запроса методом edit
     */
    public static class Edit extends Abstract {
        /**
         * Код метокласса искомого для редактирования объекта
         */
        protected String fqn;
        /**
         * Ассоциативный массив содержащий параметры поиска редактируемого объекта
         */
        protected Query query;
        /**
         * Идентификатор редактируемого объекта
         */
        protected String uuid;
        /**
         * Ассоциативный массив содержащий параметры для редактирования
         */
        protected Attrs attrs;

        /**
         * @param fqn код метокласса искомого для редактирования объекта
         * @param query ассоциативный массив содержащий параметры поиска редактируемого объекта
         * @param attrs ассоциативный массив содержащий параметры для редактирования
         */
        public Edit(String fqn, Query query, Attrs attrs) {
            this.fqn = fqn;
            this.query = query;
            this.attrs = attrs;
        }

        /**
         * @param uuid идентификатор редактируемого объекта
         * @param attrs ассоциативный массив содержащий параметры для редактирования
         */
        public Edit(String uuid, Attrs attrs) {
            this.attrs = attrs;
            this.uuid = uuid;
        }

        /**
         * Метод для подготовки к сериализации
         * @return подготолвенная к сериализации Map
         */
        @Override
        public Map<String, Object> getJsonValue() {
            HashMap<String, Object> params = new HashMap<>();
            params.put("uuid", uuid);
            params.put("fqn", fqn);
            params.put("query", query);
            params.put("attrs", attrs);
            params.put("view", view);
            HashMap<String, Object> dto = new HashMap<>();
            dto.put("jsonrpc", JSON_RPC_VERSION_CONST);
            dto.put("method", "edit");
            dto.put("id", id);
            dto.put("params", params);
            return dto;
        }
    }
}

/**
 * DTO, возвращаемый в результате запроса
 */
public class RpcResponseDto {
    /**
     * Версия модуля json rpc
     */
    public String jsonrpc;
    /**
     * Идентификатор dto запроса
     */
    public Long id;
    /**
     * Сообщение об ошибке, будет null если запрос успешно выполнен
     */
    public Error error;
    /**
     * Резулльтат выполнения запроса
     * Здесь может быть:
     * строка, если запрос был выполнен одним из методом: get, edit, create и view не был передан,
     * массив строк, если запрос был выполнен методом find и view не был передан,
     * Map, если запрос был выполнен одним из методом: get, edit, create и view был передан,
     * массив Map, если запрос был выполнен методом find и view был передан,
     * null, если искомые объекты не были найден или произошла ошибка
     */
    public Object result;

    /**
     * Структура сообщения об ошибке
     */
    public static class Error{
        /**
         * Код ошибки
         */
        public Integer code;
        /**
         * Системное сообщение об ошибке
         */
        public String message;
    }
}

/**
 * Утилитарый класс, содерщий набор статичесих методов для упрощения использования
 */
@SuppressWarnings("unused")
public class RpcUtilities {

    /**
     * Заранее созданный экземпляр, что бы можно было поместить его в удобно названную переменную
     */
    private static final RpcUtilities INSTANCE = new RpcUtilities();

    /**
     * Получить заранее созданный экземпляр
     *
     * @return заранее созданный экземпляр
     */
    public static RpcUtilities getInstance() {
        return INSTANCE;
    }

    RpcUtilities() {
    }

    /**
     * Создать условный оператор like
     *
     * @param str аргумент для сравнения
     * @return условный оператор like
     */
    public Condition.Like like(String str) {
        return new Condition.Like(str);
    }

    /**
     * Создать условный оператор eq
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор eq
     */
    public Condition.Eq eq(String arg) {
        return new Condition.Eq(arg);
    }

    /**
     * Создать условный оператор eq
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор eq
     */
    public Condition.Eq eq(Number arg) {
        return new Condition.Eq(arg);
    }

    /**
     * Создать условный оператор eq
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор eq
     */
    public Condition.Eq eq(Date arg) {
        return new Condition.Eq(arg);
    }

    /**
     * Создать условный оператор eq
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор eq
     */
    public Condition.Eq eq(Boolean arg) {
        return new Condition.Eq(arg);
    }

    /**
     * Создать условный оператор gt
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор gt
     */
    public Condition.Gt gt(Date arg) {
        return new Condition.Gt(arg);
    }

    /**
     * Создать условный оператор gt
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор gt
     */
    public Condition.Gt gt(Number arg) {
        return new Condition.Gt(arg);
    }

    /**
     * Создать условный оператор lt
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор lt
     */
    public Condition.Lt lt(Date arg) {
        return new Condition.Lt(arg);
    }

    /**
     * Создать условный оператор lt
     *
     * @param arg аргумет для сравнеия
     * @return условный оператор lt
     */
    public Condition.Lt lt(Number arg) {
        return new Condition.Lt(arg);
    }

    /**
     * Создать условный оператор in
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор in
     */
    public Condition.In opIn(Number[] args) {
        return new Condition.In(args);
    }

    /**
     * Создать условный оператор in
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор in
     */
    public Condition.In opIn(String[] args) {
        return new Condition.In(args);
    }

    /**
     * Создать условный оператор in
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор in
     */
    public  Condition.In opIn(Date[] args) {
        return new Condition.In(args);
    }

    /**
     * Создать условный оператор in
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор in
     */
    public Condition.In opIn(Boolean[] args) {
        return new Condition.In(args);
    }

    /**
     * Создать условный оператор isNotNull
     *
     * @return условный оператор isNotNull
     */
    public Condition.IsNotNull isNotNull() {
        return new Condition.IsNotNull();
    }

    /**
     * Создать условный оператор isNull
     *
     * @return условный оператор isNull
     */
    public Condition.IsNull isNull() {
        return new Condition.IsNull();
    }

    /**
     * Создать условный оператор orEq
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор orEq
     */
    public Condition.OrEq orEq(String[] args) {
        return new Condition.OrEq(args);
    }

    /**
     * Создать условный оператор orEq
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор orEq
     */
    public Condition.OrEq orEq(Date[] args) {
        return new Condition.OrEq(args);
    }

    /**
     * Создать условный оператор orEq
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор orEq
     */
    public Condition.OrEq orEq(Number[] args) {
        return new Condition.OrEq(args);
    }

    /**
     * Создать условный оператор orEq
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор orEq
     */
    public Condition.OrEq orEq(Boolean[] args) {
        return new Condition.OrEq(args);
    }

    /**
     * Создать условный оператор not
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор not
     */
    public Condition.Not not(String[] args) {
        return new Condition.Not(args);
    }

    /**
     * Создать условный оператор not
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор not
     */
    public Condition.Not not(Date[] args) {
        return new Condition.Not(args);
    }

    /**
     * Создать условный оператор not
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор not
     */
    public Condition.Not not(Number[] args) {
        return new Condition.Not(args);
    }

    /**
     * Создать условный оператор not
     *
     * @param args перечень аргуметов для сравнения
     * @return условный оператор not
     */
    public Condition.Not not(Boolean[] args) {
        return new Condition.Not(args);
    }

    /**
     * Создать условный оператор between
     *
     * @param arg1 стартовый аргумет для интервала сравнеия
     * @param arg2 конечный аргумет для интервала сравнеия
     * @return условный оператор between
     */
    public Condition.Between between(Date arg1, Date arg2) {
        return new Condition.Between(arg1, arg2);
    }

    /**
     * Создать условный оператор between
     *
     * @param arg1 стартовый аргумет для интервала сравнеия
     * @param arg2 конечный аргумет для интервала сравнеия
     * @return условный оператор between
     */
    public Condition.Between between(Number arg1, Number arg2) {
        return new Condition.Between(arg1, arg2);
    }

    /**
     * Создать dto для отправки запроса методом get
     *
     * @param fqn   код метокласса искомого объекта
     * @param query ассоциативный массив для поиска объекта
     * @return dto для отправки запроса методом get
     */
    public RpcRequestDto.Get get(String fqn, Query query) {
        return new RpcRequestDto.Get(fqn, query);
    }

    /**
     * Создать dto для отправки запроса методом find
     *
     * @param fqn   код метокласса искомого объекта
     * @param query ассоциативный массив для поиска объекта
     * @return dto для отправки запроса методом find
     */
    public RpcRequestDto.Find find(String fqn, Query query) {
        return new RpcRequestDto.Find(fqn, query);
    }

    /**
     * Создать dto для отправки запроса методом create
     *
     * @param fqn   код метакласса создаваемого объекта
     * @param attrs ассоциативный массив с атрибутами создаваемого объекта
     * @return dto для отправки запроса методом create
     */
    public RpcRequestDto.Create create(String fqn, Attrs attrs) {
        return new RpcRequestDto.Create(fqn, attrs);
    }

    /**
     * Создать dto для отправки запроса методом edit
     *
     * @param uuid  идентификатор редактируемого объекта
     * @param attrs ассоциативный массив содержащий параметры для редактирования
     * @return dto для отправки запроса методом edit
     */
    public RpcRequestDto.Edit edit(String uuid, Attrs attrs) {
        return new RpcRequestDto.Edit(uuid, attrs);
    }

    /**
     * Создать dto для отправки запроса методом edit
     *
     * @param fqn   код метокласса искомого для редактирования объекта
     * @param query ассоциативный массив содержащий параметры поиска редактируемого объекта
     * @param attrs ассоциативный массив содержащий параметры для редактирования
     * @return dto для отправки запроса методом edit
     */
    public RpcRequestDto.Edit edit(String fqn, Query query, Attrs attrs) {
        return new RpcRequestDto.Edit(fqn, query, attrs);
    }

    /**
     * Создать новаый эжкземпляр attrs
     *
     * @return новаый эжкземпляр attrs
     */
    public Attrs attrs() {
        return new Attrs();
    }

    /**
     * Создать новаый эжкземпляр query
     *
     * @return новаый эжкземпляр query
     */
    public Query query() {
        return new Query();
    }
}
