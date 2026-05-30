package scheme;

import java.io.PrintStream;

public class primitivelength extends primitiveprocedure {

  protected primitivelength() {
    name = "length";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject l = argl.getCar();
    if ( l.isList() ) {
      return new schemeinteger( ((schemelist)l).length() );
    } else {
      throw new schemelistexception(
        name + " : argument is not a list"
      );
    }
  }

}
