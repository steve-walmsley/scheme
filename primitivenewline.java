package scheme;

import java.io.PrintStream;

public class primitivenewline extends primitiveprocedure {

  protected primitivenewline() {
    name = "newline";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    System.out.println();
    val = schemeundefined.undefinedObject();

    return val;
  }

}
