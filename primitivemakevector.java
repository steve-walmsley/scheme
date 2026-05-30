package scheme;

import java.io.PrintStream;

public class primitivemakevector extends primitiveprocedure {

  protected primitivemakevector() {
    name = "make-vector";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    int l = argl.length();

    if ( l > 0 ) {
      int k = ((schemeinteger)argl.getCar()).integerValue();
      
      schemeobject fill;
      if ( l > 1 ) {
        fill = argl.getCadr();
      } else {
        fill = schemefalse.falseObject();
      }

      return new schemevector( k, fill );
    } else {
      throw new schemeexception( name + " : no arguments" );
    }
  }

}
