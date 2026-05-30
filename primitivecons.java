package scheme;

import java.io.PrintStream;

public class primitivecons extends primitiveprocedure {

  protected primitivecons() {
    name = "cons";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = new schemepair( argl.getCar(), argl.getCadr() );

    return val;
  }

}
