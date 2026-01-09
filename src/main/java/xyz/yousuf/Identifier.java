package xyz.yousuf;

public class Identifier implements Expression {
    Token name;
    String value;

    public Identifier(Token name, String value){
          this.name = name;
          this.value = value;
    }

      @Override
      public String toString() {
            return "Identifier{" +
                      "name=" + name +
                      ", value='" + value + '\'' +
                      '}';
      }

}
