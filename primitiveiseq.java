package scheme;

import java.io.PrintStream;

public class primitiveiseq extends primitiveprocedure {

  protected primitiveiseq() {
    name = "eq?";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    if ( argl.getCar().isEq( argl.getCadr() ) ) {
      return schemetrue.trueObject();
    } else {
      return schemefalse.falseObject();
    }  
  }

}
