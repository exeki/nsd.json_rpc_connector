package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
