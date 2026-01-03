package xyz.yousuf;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.printf("Hello, %s, Welcome to the monkey programming language\n", System.getProperty("user.name"));
        System.out.print("Feel free to type in commands\n>>");

        while (scanner.hasNext() ){
            var input = scanner.nextLine();
            Main.code = input;
            System.out.println("input received : " + input);
            // call nextToken until EOF token is not returned
            while (Main.position != Main.code.length()) {
                System.out.println(Main.NextToken());
                Main.currentWord.setLength(0);
            }
            System.out.print(">>");
            Main.position = 0;
        }



    }
}
