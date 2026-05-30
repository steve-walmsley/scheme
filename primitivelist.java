package scheme;

import java.io.PrintStream;

public class primitivelist extends primitiveprocedure {

  protected primitivelist() {
    name = "list";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = argl.copy();

    return val;
  }

}
