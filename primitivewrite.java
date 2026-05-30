package scheme;

import java.io.PrintStream;

public class primitivewrite extends primitiveprocedure {

  protected primitivewrite() {
    name = "write";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    argl.getCar().print( System.out );
    val = schemeundefined.undefinedObject();

    return val;
  }

}
