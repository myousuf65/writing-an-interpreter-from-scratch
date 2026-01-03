package xyz.yousuf;

import java.util.Map;

enum TokenType {
    ILLEGAL,
    EOF,

    LT,
    GT,
    EQ,
    NOT_EQ,
    ASSIGN,
    PLUS,
    MINUS,
    BANG,
    ASTERISK,
    SLASH,

    COMMA,
    SEMICOLON,
    LPAREN,
    RPAREN,
    LBRACE,
    RBRACE,

    IDENT,
    INT,

    FUNCTION,
    LET,
    TRUE,
    FALSE,
    IF,
    ELSE,
    RETURN
}

record Token(TokenType Type, String Literal) { }


public class Main {
    public static String code;
    public static int position;
    public static int endPosition;
    public static char currentCharacter;
    public static StringBuilder currentWord = new StringBuilder();
    public static final Map<String, TokenType> keywords = Map.of(
            "fn", TokenType.FUNCTION,
            "let", TokenType.LET,
            "true", TokenType.TRUE,
            "false", TokenType.FALSE,
            "if", TokenType.IF,
            "else", TokenType.ELSE,
            "return", TokenType.RETURN
    );

    public static boolean isChar(char ch) {
        return Character.isUpperCase(ch) || Character.isLowerCase(ch);
    }

    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    public static boolean isWhiteSpace(char ch) {
        return Character.isWhitespace(ch);
    }

    public static boolean isKeyword(String word) {
        return keywords.containsKey(word);
    }

    public static String readWord() {
        var wordNotComplete = true;

        while (wordNotComplete) {
            if (isChar(code.charAt(position))) {
                currentWord.append(code.charAt(position));
                position++;
            } else {
                wordNotComplete = false;
            }
        }
        return currentWord.toString();
    }

    public static String readNumber() {
        var numberNotComplete = true;

        while (numberNotComplete) {
            if (isDigit(code.charAt(position))) {
                currentWord.append(code.charAt(position));
                position++;
            } else {
                numberNotComplete = false;
            }
        }
        return currentWord.toString();
    }

    public static void skipWhiteSpace() {
        while (Character.isWhitespace(code.charAt(position)) && position <= code.length() ) {
            position++;
        }
    }

    public static Token NextToken() {

        skipWhiteSpace();
        Token tok = null;
        var ch = code.charAt(position);


        switch (ch) {
            case '=':
                tok = new Token(TokenType.ASSIGN, String.valueOf(ch));
                break;
            case '+':
                tok = new Token(TokenType.PLUS, String.valueOf(ch));
                break;
            case '-':
                tok = new Token(TokenType.MINUS, String.valueOf(ch));
                break;
            case '!':
                tok = new Token(TokenType.BANG, String.valueOf(ch));
                break;
            case '/':
                tok = new Token(TokenType.SLASH, String.valueOf(ch));
                break;
            case '*':
                tok = new Token(TokenType.ASTERISK, String.valueOf(ch));
                break;
            case '<':
                tok = new Token(TokenType.LT, String.valueOf(ch));
                break;
            case '>':
                tok = new Token(TokenType.GT, String.valueOf(ch));
                break;
            case ';':
                tok = new Token(TokenType.SEMICOLON, String.valueOf(ch));
                break;
            case ',':
                tok = new Token(TokenType.COMMA, String.valueOf(ch));
                break;
            case '{':
                tok = new Token(TokenType.LBRACE, String.valueOf(ch));
                break;
            case '}':
                tok = new Token(TokenType.RBRACE, String.valueOf(ch));
                break;
            case '(':
                tok = new Token(TokenType.LPAREN, String.valueOf(ch));
                break;
            case ')':
                tok = new Token(TokenType.RPAREN, String.valueOf(ch));
                break;
            case 0:
                tok = new Token(TokenType.EOF, "");
                break;
            default:
                if (isChar(code.charAt(position))) {
                    var word = readWord();

                    // check if identifier
                    if (isKeyword(word)) {
                        return new Token(keywords.get(word), word);
                    } else {
                        return new Token(TokenType.IDENT, word);
                    }
                } else if (isDigit(code.charAt(position))) {
                    var number = readNumber();
                    return new Token(TokenType.INT, number);
                }else{
                    return new Token(TokenType.ILLEGAL, String.valueOf(ch));
                }
        }
        position++;
        return tok;
    }


    public static void main(String[] args) {
//        code = """
//                let five = 5;
//                let ten = 10;
//
//                let add = fn(x, y) {
//                    x + y;
//                };
//
//                let result = add(five, ten);
//                !-/*5;
//                5 < 10 > 5;
//
//                if (5 < 10) {
//                    return true;
//                } else {
//                    return false;
//                }
//
//                10 == 10;
//                10 != 9; """;


        // call nextToken until EOF token is not returned
        while (!(position == code.length())) {
            System.out.println(NextToken());
            currentWord.setLength(0);
        }
    }

}