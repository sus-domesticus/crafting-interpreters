package org.example;

import java.util.List;
import java.util.Map;

class LoxInterface {
    final String name;
    private final Map<String, LoxFunctionDecl> functionDecls;

    LoxInterface(String name, Map<String, LoxFunctionDecl> functionDecls) {
        this.name = name;
        this.functionDecls = functionDecls;
    }

    String name() {
        return name;
    }

    List<LoxFunctionDecl> getFunctionDecls() {
        return functionDecls.values()
                .stream()
                .toList();
    }

    @Override
    public String toString() {
        return name;
    }
}
