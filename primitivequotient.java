package scheme;

import java.io.PrintStream;

public class primitivequotient extends primitiveprocedure {

  protected primitivequotient() {
    name = "quotient";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg1 = argl.getCar(); 
    schemeobject arg2 = argl.getCadr(); 
    if ( arg1.isInteger() && arg2.isInteger() ) {
      val = ((schemeinteger)arg1).quotient( (schemeinteger)arg2 );
    } else {
      throw new schemenumberexception(
        "scheme.primitivequotient.apply() : non-integer arguments"
      );
    };

    return val;
  }

}
