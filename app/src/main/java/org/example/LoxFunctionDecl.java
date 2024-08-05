package org.example;

import java.util.List;

class LoxFunctionDecl {
    private final Stmt.FunctionDecl declaration;

    LoxFunctionDecl(Stmt.FunctionDecl declaration) {
        this.declaration = declaration;
    }

    public String name() {
        return declaration.name.lexeme;
    }

    public List<String> params() {
        return declaration.params.stream()
                .map(param -> param.lexeme)
                .toList();
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
