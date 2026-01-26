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

      public Program parseProgram() {
            Program program = new Program();

            while (currentToken.Type() != TokenType.EOF){
                  Statement statement = null;
                  switch (currentToken.Type()){
                        case LET -> {
                              statement = parseLetStatement();
                        }
                        case RETURN -> {
                              // do something
                              statement = parseReturnStatement();
                        }
                        case IF ->{
                              // do something
                        }
                        case IDENT -> {
                              // do something
                        }
                        case PLUS -> {
                              // do something
                        }
                        case MINUS -> {
                              // do something
                        }
                        case ASTERISK -> {

                        }
                        case SLASH -> {

                        }
                  }
                  if (statement != null) {
                        program.statements.add(statement);
                  }
            }
            return program;
      }

      private Statement parseReturnStatement() {
            ReturnStatement statement = new ReturnStatement();
            statement.token = currentToken;


            moveToNextToken();

            Expression expression = null;
            if (currentToken.Type() == TokenType.INT){
                  expression = parseInteger();
            }
            if (currentToken.Type() == TokenType.IDENT){
                  expression = parseIdentifier();
            }

            statement.returnValue = expression;

            // skip semicolon
            moveToNextToken();
            if (currentToken.Type() == TokenType.SEMICOLON){
                  moveToNextToken();
            }

            return statement;
      }

      public void moveToNextToken() {
            currentToken = nextToken;
            nextToken = lexer.NextToken();
      }

      private Expression parseExpression() {
            return switch (currentToken.Type()) {
                  case INT -> {
                        if (nextToken.Type() == TokenType.SEMICOLON) {
                              yield parseInteger();
                        } else {
                              yield null;
                        }
                  }
                  case LPAREN -> {
                        yield parseGroupExpression();
                  }
                  default -> {
                        yield null;
                  }
            };
      }

      private Expression parseGroupExpression() {
           return null;
      }

      private Expression parseOperatorExceprion() {
            return null;
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


            // ignore semicolon
            moveToNextToken();
            if (currentToken.Type() == TokenType.SEMICOLON){
                  moveToNextToken();
            }

            return letStatement;
      }
}
