package scheme;

import java.io.PrintStream;

public class primitiveisrational extends primitiveprocedure {

  protected primitiveisrational() {
    name = "rational?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isRational() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
