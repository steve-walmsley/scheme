package scheme;

import java.io.PrintStream;

public class schemetrue extends schemeobject {

  private final static schemesymbol trueSymbol = 
    schemesymbol.makeSymbol( "#t" );

  private static schemetrue trueObject = null;

  public static schemesymbol trueSymbol() {
    return trueSymbol;
  }

  public static schemetrue trueObject() {
    if ( trueObject == null ) {
      trueObject = new schemetrue();
    };
    return trueObject;
  }

  private schemetrue() {
  }

  public void print( PrintStream out ) {
    out.print( "#t" );
  }

  public boolean isBoolean() {
    return true;
  }

  public boolean isTrue() {
    return true;
  }

  public boolean isSelfEvaluating() {
    return true;
  };

}
