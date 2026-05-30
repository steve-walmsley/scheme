package scheme;

import java.io.PrintStream;

public class primitivelistref extends primitiveprocedure {

  protected primitivelistref() {
    name = "list-ref";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject l = argl.getCar();
    if ( l.isPair() ) {
      schemeobject k = argl.getCadr();
      if ( k.isInteger() ) {
        return ((schemepair)l).listref( ((schemeinteger)k).integerValue() );
      } else {
        throw new schemelistexception(
          name + " : argument 2 is not an integer"
        );
      }
    } else {
      throw new schemelistexception(
        name + " : argument 1 is not a list"
      );
    }
  }

}
