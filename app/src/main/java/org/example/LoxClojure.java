package org.example;

import java.util.List;

class LoxClojure implements LoxCallable {
    private final Expr.Clojure clojure;
    private final Environment closure;

    LoxClojure(Expr.Clojure clojure, Environment closure) {
        this.closure = closure;
        this.clojure = clojure;
    }

    @Override
    public int arity() {
        return clojure.params.size();
    }

    @Override
    public Object call(Interpreter interpreter,
            List<Object> arguments) {
        Environment environment = new Environment(closure);
        for (int i = 0; i < clojure.params.size(); i++) {
            environment.define(clojure.params.get(i).lexeme,
                    arguments.get(i));
        }

        try {
            interpreter.executeBlock(clojure.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        return null;
    }

    @Override
    public String toString() {
        return "<clojure >";
    }
}
