package scheme;

import java.io.PrintStream;

public class primitiveisnumber extends primitiveprocedure {

  protected primitiveisnumber() {
    name = "number?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isNumber() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
