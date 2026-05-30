package scheme;

import java.io.PrintStream;

public class primitivemakebytevector extends primitiveprocedure {

  protected primitivemakebytevector() {
    name = "make-bytevector";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    int l = argl.length();

    if ( l > 0 ) {
      int k = ((schemeinteger)argl.getCar()).integerValue();
      
      byte fill;
      if ( l > 1 ) {
        fill = (byte)((schemeinteger)argl.getCadr()).integerValue();
      } else {
        fill = (byte)0;
      }

      return new schemebytevector( k, fill );
    } else {
      throw new schemeexception( name + " : no arguments" );
    }
  }

}
