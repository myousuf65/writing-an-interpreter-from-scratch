package xyz.yousuf;

import java.util.ArrayList;
import java.util.List;

public class Parser {
      Lexer lexer;
      Token currentToken;
      Token nextToken;
      List<Token> allTokens;
      int position;
      List<Statement> allStatements = new ArrayList<>();

      public Parser(String input) {
            this.lexer = new Lexer();
            lexer.code = input;
            moveToNextToken();
            moveToNextToken();
      }

      public List<Statement> parseTokens() {
            if (currentToken.Type() == TokenType.LET) {
                  Statement statement = parseLetStatement();
                  allStatements.add(statement);
            }
            return allStatements;
      }

      public void moveToNextToken() {
            currentToken = nextToken;
            nextToken = lexer.NextToken();
      }

      private Expression parseExpression() {
            if (currentToken.Type() == TokenType.INT){
                  return parseInteger();
            }
            return parseIdentifier();
      }

      private Expression parseIdentifier() {
            return null;
      }

      private Expression parseInteger() {
            IntegerLiteral integerLiteral = new IntegerLiteral();
            integerLiteral.token = currentToken;
            integerLiteral.value = Long.parseLong(currentToken.Literal());
            return integerLiteral;
      }

      public Statement parseLetStatement() {
            // let will not be stored anywhere
            LetStatement letStatement = new LetStatement();
            letStatement.token = currentToken;

            // check if next token is identifer
            if (nextToken.Type() != TokenType.IDENT) {
                  return null;
            }
            moveToNextToken();

            letStatement.name = new Identifier(currentToken, currentToken.Literal());

            // skip equal sign
            moveToNextToken();
            moveToNextToken();

            // parse expression
            letStatement.value = parseExpression();



            return letStatement;
      }

}
