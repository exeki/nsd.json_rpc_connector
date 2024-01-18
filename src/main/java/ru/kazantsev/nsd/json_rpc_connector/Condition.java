package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.List;
import java.util.Map;

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