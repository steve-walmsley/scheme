package scheme;

import java.io.PrintStream;

public class primitivelisttail extends primitiveprocedure {

  protected primitivelisttail() {
    name = "list-tail";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject l = argl.getCar();
    if ( l.isList() ) {
      schemeobject k = argl.getCadr();
      if ( k.isInteger() ) {
        return ((schemelist)l).listtail( ((schemeinteger)k).integerValue() );
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
