package scheme;

import java.io.PrintStream;

public class primitivesetcar extends primitiveprocedure {

  protected primitivesetcar() {
    name = "set-car!";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject l = argl.getCar();
    if ( l.isPair() ) {
      schemeobject o = argl.getCadr();
      ((schemepair)l).setCar( o );
      return schemeundefined.undefinedObject();
    } else {
      throw new schemelistexception(
        name + " : argument 1 is not a pair"
      );
    }
  }

}
