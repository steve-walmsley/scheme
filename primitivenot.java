package scheme;

import java.io.PrintStream;

public class primitivenot extends primitiveprocedure {

  protected primitivenot() {
    name = "not";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;
    if ( argl.getCar().isTrue() ) {
      val = schemefalse.falseObject();
    } else {
      val = schemetrue.trueObject();
    };

    return val;
  }

}
