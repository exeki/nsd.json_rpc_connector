package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class ConditionalOperator {
    Map<String, Object> value;
    @JsonValue
    Map<String, Object> getValue() {
        return value;
    }
    public static class Like extends ConditionalOperator{
        public Like(String str) {
            this.value = Map.of("method", "like", "params", Map.of("args", List.of(str)));
        }
    }
    public static class IsNull extends ConditionalOperator {
        public IsNull(){
            this.value = Map.of("method", "isNull");
        }
    }
    public static class IsNotNull extends ConditionalOperator {
        public IsNotNull(){
            this.value = Map.of("method", "isNotNull");
        }
    }
    public static class OrEq extends ConditionalOperator{
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
    public static class Not extends ConditionalOperator {
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
    public static class Eq extends ConditionalOperator {
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
    public static class In extends ConditionalOperator {
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
    public static class Between extends ConditionalOperator {
        public Between(Date arg1, Date arg2){
            this.value =  Map.of("method", "between", "params", Map.of("args", List.of(arg1, arg2)));
        }
        public Between(Number arg1, Number arg2){
            this.value =  Map.of("method", "between", "params", Map.of("args", List.of(arg1, arg2)));
        }
    }
    public static class Gt extends ConditionalOperator {
        public Gt(Number arg){
            this.value =  Map.of("method", "gt", "params", Map.of("args", List.of(arg)));
        }
        public Gt(Date arg){
            this.value =  Map.of("method", "gt", "params", Map.of("args", List.of(arg)));
        }
    }
    public static class Lt extends ConditionalOperator {
        public Lt(Number arg){
            this.value =  Map.of("method", "lt", "params", Map.of("args", List.of(arg)));
        }
        public Lt(Date arg){
            this.value =  Map.of("method", "lt", "params", Map.of("args", List.of(arg)));
        }
    }
}
