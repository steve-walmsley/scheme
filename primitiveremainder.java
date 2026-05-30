package scheme;

import java.io.PrintStream;

public class primitiveremainder extends primitiveprocedure {

  protected primitiveremainder() {
    name = "remainder";
  }

  public schemeobject apply( schemelist argl ) throws schemeexception {
    schemeobject val;

    schemeobject arg1 = argl.getCar(); 
    schemeobject arg2 = argl.getCadr(); 
    if ( arg1.isInteger() && arg2.isInteger() ) {
      val = ((schemeinteger)arg1).remainder( (schemeinteger)arg2 );
    } else {
      throw new schemenumberexception(
        "scheme.primitiveremainder.apply() : non-integer arguments"
      );
    };

    return val;
  }

}
