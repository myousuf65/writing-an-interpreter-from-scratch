package xyz.yousuf;

import java.util.ArrayList;
import java.util.List;

public class Program implements Node {
    public List<Statement> statements = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Program:{\n");
        for (Statement stmt : statements) {
            s.append(stmt).append("\n");
        }
        s.append("}");
        return s.toString();
    }
}
