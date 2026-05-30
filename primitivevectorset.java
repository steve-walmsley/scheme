package scheme;

import java.io.PrintStream;

public class primitivevectorset extends primitiveprocedure {

  protected primitivevectorset() {
    name = "vector-set!";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject o = argl.getCar();
    if ( o.isVector() ) {
      schemevector v = (schemevector)o;
      int k = ((schemeinteger)argl.getCadr()).integerValue();
      schemeobject obj = argl.getCaddr();
      if ( ( k >= 0 ) && ( k < v.length() ) ) {
        ((schemevector)v).set( k, obj );
        return schemeundefined.undefinedObject();
      } else {
        throw new schemelistexception(
          name + " : index " + k + " out of range"
        );
      }
    } else {
      throw new schemelistexception(
        name + " : argument 1 is not a vector"
      );
    }
  }

}
