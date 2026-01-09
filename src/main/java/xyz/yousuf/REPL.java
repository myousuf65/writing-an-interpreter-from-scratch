package xyz.yousuf;

import java.util.List;
import java.util.Scanner;

public class REPL {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Hello, %s, Welcome to the monkey programming language\n", System.getProperty("user.name"));
        System.out.print("Feel free to type in commands\n>>");
        Lexer lexer = new Lexer();

        while (scanner.hasNext() ){
            var input = scanner.nextLine();
            List<Token> tokens = lexer.New(input);
            System.out.println(tokens);
            System.out.print(">>");
        }
    }
}
