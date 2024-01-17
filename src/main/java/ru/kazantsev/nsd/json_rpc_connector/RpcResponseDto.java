package ru.kazantsev.nsd.json_rpc_connector;

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
