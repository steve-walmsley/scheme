package scheme;

import java.io.PrintStream;

public class primitiveisequal extends primitiveprocedure {

  protected primitiveisequal() {
    name = "equal?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    if ( argl.getCar().isEqual( argl.getCadr() ) ) {
      return schemetrue.trueObject();
    } else {
      return schemefalse.falseObject();
    }  
  }

}
