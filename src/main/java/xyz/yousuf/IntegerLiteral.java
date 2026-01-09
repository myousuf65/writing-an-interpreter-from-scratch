package xyz.yousuf;

public class IntegerLiteral implements Expression{

    Token token;
    long value;

    @Override
    public String toString() {
        return "IntegerLiteral{" +
                  "token=" + token +
                  ", value=" + value +
                  '}';
    }

}
