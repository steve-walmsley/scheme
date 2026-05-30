package scheme;

import java.io.PrintStream;

public class primitiveiscomplex extends primitiveprocedure {

  protected primitiveiscomplex() {
    name = "complex?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isComplex() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
