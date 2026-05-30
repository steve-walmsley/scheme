package scheme;

import java.io.PrintStream;

public class primitiveruntime extends primitiveprocedure {

  private static long t0 = System.currentTimeMillis();

  protected primitiveruntime() {
    name = "runtime";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    val = new schemereal( 
      ( System.currentTimeMillis() - t0 ) / 1000.0
    );

    return val;
  }

}
