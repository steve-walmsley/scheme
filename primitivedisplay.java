package scheme;

import java.io.PrintStream;

public class primitivedisplay extends primitiveprocedure {

  protected primitivedisplay() {
    name = "display";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    argl.getCar().display( System.out );
    val = schemeundefined.undefinedObject();

    return val;
  }

}
