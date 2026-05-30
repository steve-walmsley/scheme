package scheme;

import java.io.PrintStream;

public class primitiveisnull extends primitiveprocedure {

  protected primitiveisnull() {
    name = "null?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isNull() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
