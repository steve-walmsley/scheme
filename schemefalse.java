package scheme;

import java.io.PrintStream;

public class schemefalse extends schemeobject {

  private final static schemesymbol falseSymbol = 
    schemesymbol.makeSymbol( "#f" );

  private static schemefalse falseObject = null;

  public static schemesymbol falseSymbol() {
    return falseSymbol;
  }

  public static schemefalse falseObject() {
    if ( falseObject == null ) {
      falseObject = new schemefalse();
    };
    return falseObject;
  }

  private schemefalse() {
  }

  public String toString() {
    return "#f";
  }

  public void print( PrintStream out ) {
    out.print( "#f" );
  }

  public boolean isBoolean() {
    return true;
  }

  public boolean isTrue() {
    return false;
  }

  public boolean isSelfEvaluating() {
    return true;
  };

}
