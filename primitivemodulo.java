package scheme;

import java.io.PrintStream;

public class primitivemodulo extends primitiveprocedure {

  protected primitivemodulo() {
    name = "modulo";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg1 = argl.getCar(); 
    schemeobject arg2 = argl.getCadr(); 
    if ( arg1.isInteger() && arg2.isInteger() ) {
      val = ((schemeinteger)arg1).modulo( (schemeinteger)arg2 );
    } else {
      throw new schemenumberexception(
        "scheme.primitiveremainder.modulo() : non-integer arguments"
      );
    };

    return val;
  }

}
