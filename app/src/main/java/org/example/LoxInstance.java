package org.example;

import java.util.HashMap;
import java.util.Map;

class LoxInstance {
    private LoxClass klass;
    private final Map<String, Object> fields = new HashMap<>();

    LoxInstance(LoxClass klass) {
        this.klass = klass;
    }

    Object get(Token name) {
        if (fields.containsKey(name.lexeme)) {
            return fields.get(name.lexeme);
        }

        if (klass != null) {
            LoxFunction method = klass.findMethod(name.lexeme);
            if (method != null)
                return method.bind(this);
        } else {
            throw new RuntimeError(name, "Undefined static method '" +
                    name.lexeme + "'.");
        }

        throw new RuntimeError(name,
                "Undefined property '" + name.lexeme + "'.");
    }

    void set(Token name, Object value) {
        fields.put(name.lexeme, value);
    }

    void set(String lexeme, Object value) {
        fields.put(lexeme, value);
    }

    @Override
    public String toString() {
        return klass.name + " instance";
    }
}
