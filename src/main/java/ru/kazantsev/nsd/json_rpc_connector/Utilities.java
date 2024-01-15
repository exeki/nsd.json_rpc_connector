package ru.kazantsev.nsd.json_rpc_connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Date;

public class Utilities {

    private static final Utilities INSTANCE = new Utilities();

    public static Utilities getInstance(){
        return INSTANCE;
    }

    public ConditionalOperator.Like like(String str) {return new ConditionalOperator.Like(str);}

    public ConditionalOperator.Eq eq(String arg){return new ConditionalOperator.Eq(arg);}
    public ConditionalOperator.Eq eq(Number arg){return new ConditionalOperator.Eq(arg);}
    public ConditionalOperator.Eq eq(Date arg){return new ConditionalOperator.Eq(arg);}
    public ConditionalOperator.Eq eq(Boolean arg){return new ConditionalOperator.Eq(arg);}

    public ConditionalOperator.Gt gt(Date arg){return new ConditionalOperator.Gt(arg);}
    public ConditionalOperator.Gt gt(Number arg){return new ConditionalOperator.Gt(arg);}

    public ConditionalOperator.Lt lt(Date arg){return new ConditionalOperator.Lt(arg);}
    public ConditionalOperator.Lt lt(Number arg){return new ConditionalOperator.Lt(arg);}

    public ConditionalOperator.In in(Number[] args){return new ConditionalOperator.In(args);}
    public ConditionalOperator.In in(String[] args){return new ConditionalOperator.In(args);}
    public ConditionalOperator.In in(Date[] args){return new ConditionalOperator.In(args);}
    public ConditionalOperator.In in(Boolean[] args){return new ConditionalOperator.In(args);}

    public ConditionalOperator.IsNotNull isNotNull(){return new ConditionalOperator.IsNotNull();}

    public ConditionalOperator.IsNull isNull(){return new ConditionalOperator.IsNull();}

    public ConditionalOperator.OrEq orEq(String[] args){return new ConditionalOperator.OrEq(args);}
    public ConditionalOperator.OrEq orEq(Date[] args){return new ConditionalOperator.OrEq(args);}
    public ConditionalOperator.OrEq orEq(Number[] args){return new ConditionalOperator.OrEq(args);}
    public ConditionalOperator.OrEq orEq(Boolean[] args){return new ConditionalOperator.OrEq(args);}

    public ConditionalOperator.Not not(String[] args){return new ConditionalOperator.Not(args);}
    public ConditionalOperator.Not not(Date[] args){return new ConditionalOperator.Not(args);}
    public ConditionalOperator.Not not(Number[] args){return new ConditionalOperator.Not(args);}
    public ConditionalOperator.Not not(Boolean[] args){return new ConditionalOperator.Not(args);}

    public ConditionalOperator.Between between(Date arg1, Date arg2){return new ConditionalOperator.Between(arg1, arg2);}
    public ConditionalOperator.Between between(Number arg1, Number arg2){return new ConditionalOperator.Between(arg1, arg2);}

}
