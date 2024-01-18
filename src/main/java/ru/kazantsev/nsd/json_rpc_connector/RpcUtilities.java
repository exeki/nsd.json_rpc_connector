package ru.kazantsev.nsd.json_rpc_connector;

import java.util.Date;

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
    public Condition.In opIn(Date[] args) {
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

    public Attrs attrs(String key, String value) {
        return new Attrs(key, value);
    }
    public Attrs attrs(String key, Number value) {
        return new Attrs(key, value);
    }
    public Attrs attrs(String key, Boolean value) {
        return new Attrs(key, value);
    }
    public Attrs attrs(String key, Date value) {
        return new Attrs(key, value);
    }

    /**
     * Создать новаый эжкземпляр query
     *
     * @return новаый эжкземпляр query
     */
    public Query query() {
        return new Query();
    }

    public Query query(String key, String value) {
        return new Query(key, value);
    }
    public Query query(String key, Number value) {
        return new Query(key, value);
    }
    public Query query(String key, Boolean value) {
        return new Query(key, value);
    }
    public Query query(String key, Condition value) {
        return new Query(key, value);
    }
    public Query query(String key, Date value) {
        return new Query(key, value);
    }

}
