package scheme;

import java.io.PrintStream;

public class primitiveispair extends primitiveprocedure {

  protected primitiveispair() {
    name = "pair?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isPair() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
