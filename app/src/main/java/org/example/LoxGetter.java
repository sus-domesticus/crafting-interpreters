package org.example;

class LoxGetter {
    private final Stmt.Getter declaration;
    private final Environment closure;

    LoxGetter(Stmt.Getter declaration, Environment closure) {
        this.closure = closure;
        this.declaration = declaration;
    }

    LoxGetter bind(LoxInstance instance) {
        Environment environment = new Environment(closure);
        environment.define("this", instance);
        return new LoxGetter(declaration, environment);
    }

    public Object execute(Interpreter interpreter) {
        Environment environment = new Environment(closure);
        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            return returnValue.value;
        }
        return null;
    }

    @Override
    public String toString() {
        return "<getter " + declaration.name.lexeme + ">";
    }
}
