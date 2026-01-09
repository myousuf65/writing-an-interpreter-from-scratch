package xyz.yousuf;

import java.util.List;


public class Test {
      static void main() {

            var input = """
                      let x = 6;
                      return 6;
                      let b = 7;
                      """;
            Parser parser = new Parser(input);
            Program allstatements = parser.parseProgram();
            System.out.println(allstatements);

      }
}
