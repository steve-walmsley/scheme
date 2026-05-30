package scheme;

import java.io.PrintStream;

public class primitiveisprocedure extends primitiveprocedure {

  protected primitiveisprocedure() {
    name = "procedure?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isProcedure() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
