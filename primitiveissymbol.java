package scheme;

import java.io.PrintStream;

public class primitiveissymbol extends primitiveprocedure {

  protected primitiveissymbol() {
    name = "symbol?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    if ( argl.getCar().isSymbol() ) {
      val = schemetrue.trueObject();
    } else {
      val = schemefalse.falseObject();
    };

    return val;
  }

}
