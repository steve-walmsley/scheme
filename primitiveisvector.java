package scheme;

import java.io.PrintStream;

public class primitiveisvector extends primitiveprocedure {

  protected primitiveisvector() {
    name = "vector?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isVector() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
