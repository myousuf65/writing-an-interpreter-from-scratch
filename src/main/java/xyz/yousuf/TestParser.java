package xyz.yousuf;

interface Expr{ }

enum TokType {
      NUM,
      LITERAL,
      IDENTIFIER
}

class NumberExpr implements Expr{
     int value;

     NumberExpr(int value){
           this.value = value;
     }
}

class AddExpr implements Expr{
      Expr right;
      Expr left;

      AddExpr(Expr right, Expr left){
            this.right = right;
            this.left = left;
      }
}
public class TestParser {

      static void main() {
            var input = "1 + 2";

      }
}
