package xyz.yousuf;

public class LetStatement implements Statement{
    Token token;
    Identifier name;
    Expression value;

    @Override
    public String toString() {
        return "LetStatement{" +
                  "token=" + token +
                  ", name=" + name +
                  ", value=" + value +
                  '}';
    }

    @Override
    public String tokenLiteral() {
        return "";
    }
}
