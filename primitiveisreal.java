package scheme;

import java.io.PrintStream;

public class primitiveisreal extends primitiveprocedure {

  protected primitiveisreal() {
    name = "real?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isReal() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
