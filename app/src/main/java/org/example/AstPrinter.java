package org.example;

import org.example.Expr.Ternary;

class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null)
            return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(");
        for (Expr expr : exprs) {
            builder.append(expr.accept(this));
            builder.append(" ");
        }
        builder.append(name);
        builder.append(")");

        return builder.toString();
    }

    @Override
    public String visitTernaryExpr(Ternary expr) {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        builder.append(expr.left.accept(this));
        builder.append(" ");
        builder.append("if");
        builder.append(" ");
        builder.append(expr.condition.accept(this));
        builder.append(" ");
        builder.append("else");
        builder.append(" ");
        builder.append(expr.right.accept(this));
        builder.append(")");
        return builder.toString();
    }
}
