package xyz.yousuf;

public class ReturnStatement implements Statement {
      Token token;
      Expression returnValue;

      @Override
      public String toString() {
            return "ReturnStatement{" +
                      "token=" + token +
                      ", returnValue=" + returnValue +
                      '}';
      }
}
