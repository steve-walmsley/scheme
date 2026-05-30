package scheme;

import java.io.PrintStream;

public class primitivereverse extends primitiveprocedure {

  protected primitivereverse() {
    name = "reverse";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject l = argl.getCar();
    if ( l.isList() ) {
      return ((schemelist)l).reverse();
    } else {
      throw new schemelistexception(
        name + " : argument is not a list"
      );
    }
  }

}
