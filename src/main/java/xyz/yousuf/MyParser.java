package xyz.yousuf;

import java.util.List;
import java.util.ArrayList;

enum tok_type{
      Atom,
      Op,
      EOF
}

class lex{
      tok curr;
      tok next;
      int pos = 0;
      List<tok> tokens = new ArrayList<>();

      lex(List<tok> tokens){
            this.tokens = tokens;
            curr = tokens.get(pos);
      }

      tok next(){
            var tok = tokens.get(pos);
            pos++;
            return tok;
      }

      tok peek(){
            return tokens.get(pos);
      }
}

record tok(tok_type tok_type, char literal){}

record Atom(char atom) implements Expression{ }

record Operation(char op, List<Expression> args) implements Expression{}

public class MyParser {
      static Token currentToken;
      static Token nextToken;
      static int position = 0;
      static List<tok> tokens;

      static {
            tokens = new ArrayList<>();
      }


      static Expression parseExpression(lex lex, int minPrecidence) {

            var token = lex.next();

            if (!token.tok_type().equals(tok_type.Atom)) {
                  throw new RuntimeException("Expected Atom, got " + token);
            }

            Expression left = new Atom(token.literal());


            while (lex.pos < lex.tokens.size()) {
                  // check if its operator
                  var operation_token = lex.peek();
                  if (!operation_token.tok_type().equals(tok_type.Op)) {
                        break;
                  }

                  // check precidence
                  int precidence = getPrecedence(operation_token.literal());
                  if (precidence < minPrecidence) {
                        break;
                  }

                  // consume op
                  lex.next();

                  // get right side
                  Expression right = parseExpression(lex, precidence + 1);

                  left = new Operation(operation_token.literal(), List.of(left, right));
            }

            return left;
      }


      public static int getPrecedence(char op) {
            return switch (op) {
                  case '+', '-' -> {
                        yield 10;
                  }
                  case '*', '/' -> {
                        yield 20;
                  }
                  default -> {
                        yield 0;
                  }
            };
      }


      public static void main(String[] args) {


//            String input = "a * b - 1 / a";
            String input = "5 * 5 + 1";

            // Tokenize the input
            var allCh = input.trim().split(" ");
            for (String ch : allCh) {
                  if (Character.isAlphabetic(ch.charAt(0)) || Character.isDigit(ch.charAt(0))) {
                        tokens.add(new tok(tok_type.Atom, ch.charAt(0)));
                  } else {
                        tokens.add(new tok(tok_type.Op, ch.charAt(0)));
                  }
            }

            // Add EOF token
            tokens.add(new tok(tok_type.EOF, '\0'));

//            System.out.println("Tokens: " + tokens);

//            // Create lexer and parse
            lex lexer = new lex(tokens);
            Expression ast = parseExpression(lexer, 0);


            System.out.println("\nAST: " + ast);


            //           -- AST --
//           var op = new Operation('-',List.of(
//                     new Operation('*', List.of(
//                               new Atom('a'),
//                               new Atom('b')
//                     )),
//                     new Operation('/', List.of(
//                               new Atom('1'),
//                               new Atom('a')
//                     ))
//           ));


      }
}