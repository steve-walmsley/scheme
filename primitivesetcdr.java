package scheme;

import java.io.PrintStream;

public class primitivesetcdr extends primitiveprocedure {

  protected primitivesetcdr() {
    name = "set-cdr!";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject l = argl.getCar();
    if ( l.isPair() ) {
      schemeobject o = argl.getCadr();
      ((schemepair)l).setCdr( o );
      return schemeundefined.undefinedObject();
    } else {
      throw new schemelistexception(
        name + " : argument 1 is not a pair"
      );
    }
  }

}
