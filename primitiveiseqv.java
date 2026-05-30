package scheme;

import java.io.PrintStream;

public class primitiveiseqv extends primitiveprocedure {

  protected primitiveiseqv() {
    name = "eqv?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    if ( argl.getCar().isEqv( argl.getCadr() ) ) {
      return schemetrue.trueObject();
    } else {
      return schemefalse.falseObject();
    }  
  }

}
