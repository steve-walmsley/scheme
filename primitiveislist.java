package scheme;

import java.io.PrintStream;

public class primitiveislist extends primitiveprocedure {

  protected primitiveislist() {
    name = "list?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isList() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
