package scheme;

import java.io.PrintStream;

public class primitivebytevectorlength extends primitiveprocedure {

  protected primitivebytevectorlength() {
    name = "bytevector-length";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject v = argl.getCar();
    if ( v.isByteVector() ) {
      return new schemeinteger( ((schemebytevector)v).length() );
    } else {
      throw new schemelistexception(
        name + " : argument is not a bytevector"
      );
    }
  }

}
