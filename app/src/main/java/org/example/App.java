package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        Expr onePlusTwoGroup = new Expr.Grouping(
                new Expr.Binary(new Expr.Literal(1),
                        new Token(TokenType.PLUS, "+", null, 1),
                        new Expr.Literal(2)));
        Expr fourMinusThreeGroup = new Expr.Grouping(
                new Expr.Binary(new Expr.Literal(4),
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(3)));
        Expr expression = new Expr.Binary(
                onePlusTwoGroup,
                new Token(TokenType.STAR, "*", null, 1),
                fourMinusThreeGroup);

        System.out.println(new AstPrinter().print(expression));
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError)
            System.exit(65);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null)
                break;
            run(line);
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now, just print the tokens.
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where,
            String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
}
