package scheme;

import java.io.PrintStream;

public class primitivecdr extends primitiveprocedure {

  protected primitivecdr() {
    name ="cdr";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = argl.getCdar();

    return val;
  }

}
