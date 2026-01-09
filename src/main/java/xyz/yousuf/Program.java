package xyz.yousuf;

import java.util.List;

public class Program implements Node{
    List<Statement> statements;

    @Override
    public String tokenLiteral() {
        if (statements.size() > 0){
            return statements.get(0).tokenLiteral();
        }
        return "";
    }
}
