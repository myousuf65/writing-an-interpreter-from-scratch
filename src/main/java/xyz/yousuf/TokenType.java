package xyz.yousuf;

public enum TokenType {
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
    TILDE,

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
