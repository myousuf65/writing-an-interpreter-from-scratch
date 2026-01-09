package xyz.yousuf;

import java.util.List;


public class Test {
      static void main() {

            var input = "let x = 5 + 4;\nlet b = 6 + 7";
            Parser parser = new Parser(input);
            var allstatements = parser.parseTokens();
            System.out.println(allstatements);

      }
}
