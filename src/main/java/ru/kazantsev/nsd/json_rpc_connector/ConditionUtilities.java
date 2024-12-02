package ru.kazantsev.nsd.json_rpc_connector;

import java.util.Date;
import java.util.Map;

/**
 * Утилитарый класс, содерщий набор статичесих методов для упрощения использования
 */
@SuppressWarnings("unused")
public class ConditionUtilities {

    /**
     * Заранее созданный экземпляр, что бы можно было поместить его в удобно названную переменную
     */
    private static final ConditionUtilities INSTANCE = new ConditionUtilities();

    /**
     * Получить заранее созданный экземпляр
     *
     * @return заранее созданный экземпляр
     */
    public static ConditionUtilities getInstance() {
        return INSTANCE;
    }

    ConditionUtilities() {
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

}
