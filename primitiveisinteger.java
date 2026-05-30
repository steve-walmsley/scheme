package scheme;

import java.io.PrintStream;

public class primitiveisinteger extends primitiveprocedure {

  protected primitiveisinteger() {
    name = "integer?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isInteger() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
