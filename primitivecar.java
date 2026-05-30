package scheme;

import java.io.PrintStream;

public class primitivecar extends primitiveprocedure {

  protected primitivecar() {
    name = "car";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = argl.getCaar();

    return val;
  }

}
